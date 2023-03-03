package com.errormanager.service.implementation;

import com.errormanager.dto.createerror.CreateErrorRequest;
import com.errormanager.dto.createerror.CreateErrorResponse;
import com.errormanager.dto.exists.ExistsErrorResponse;
import com.errormanager.dto.geterror.GetErrorRequest;
import com.errormanager.dto.geterror.GetErrorResponse;
import com.errormanager.model.Errors;
import com.errormanager.repository.ErrorRepository;
import com.errormanager.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ErrorServiceImpl implements ErrorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorServiceImpl.class);

    ErrorRepository errorRepository;

    public ErrorServiceImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Override
    public GetErrorResponse getError(GetErrorRequest getErrorRequest) {
        LOGGER.info("Comienza la ejecucion del metodo getError. Request: {}", getErrorRequest);
        Optional<Errors> errorResponse = null;
        String id = getErrorRequest.getServiceName() +"."+ getErrorRequest.getEndpointName() +"."+ getErrorRequest.getErrorId();
        try{
            errorResponse = errorRepository.findById(id);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error en la consulta a la base de datos. {}", e);
        }
        if (errorResponse.isEmpty()){
            LOGGER.info("No se encontro el ErrorResponse solicitado.");
            GetErrorResponse resp = GetErrorResponse.builder()
                    .errorCode("404")
                    .errorMessage("No se encontró el mensaje de error solicitado.")
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", resp);
            return resp;
        }else{
            LOGGER.info("Se recupero el error de la Base de datos.");
            GetErrorResponse resp = GetErrorResponse.builder()
                    .errorCode(errorResponse.get().getErrorCode())
                    .errorMessage(errorResponse.get().getErrorMessage())
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", resp);
            return resp;
        }
    }

    @Override
    public CreateErrorResponse createError(CreateErrorRequest createErrorRequest) {
        LOGGER.info("Comienza la ejecucion del metodo createError. Request: {}", createErrorRequest);
        String id = createErrorRequest.getServiceName() +"."+ createErrorRequest.getEndpointName() +"."+ createErrorRequest.getErrorId();
        LOGGER.info("Se generó el Id {}", id);
        CreateErrorResponse createErrorResponse;
        if (this.existsError(createErrorRequest.getServiceName(), createErrorRequest.getEndpointName(), createErrorRequest.getErrorId()).getExists()){
            LOGGER.error("El id {} ya esta registrado en la Base de datos", id);
            createErrorResponse = CreateErrorResponse.builder()
                    .created(false)
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", createErrorResponse);
            return createErrorResponse;
        }else{
            Errors error = Errors.builder()
                    .id(id)
                    .errorCode(createErrorRequest.getErrorCode())
                    .errorMessage(createErrorRequest.getErrorMessage())
                    .build();
            LOGGER.info("Se generó el siguiente error ", error);
            try{
                errorRepository.save(error);
            }catch (Exception e){
                LOGGER.error("Ocurrio un error al persistir el error en base de datos.", e);
                throw new RuntimeException("Ocurrio un error al persistir el error en base de datos.");
            }
            LOGGER.error("Se persistio correctamente el error.");
            createErrorResponse = CreateErrorResponse.builder()
                    .created(true)
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", createErrorResponse);
            return createErrorResponse;
        }

    }

    @Override
    public ExistsErrorResponse existsError(String service, String endpoint, String id) {
        LOGGER.info("Comienza la ejecucion del metodo existsError. Request: service - {} endpoint - {} id - {}", service, endpoint,  id);
        String existsId = service +"."+ endpoint +"."+ id;
        LOGGER.info("Se generó el Id {}", existsId);
        Boolean flagExists = null;
        try{
            flagExists = errorRepository.existsById(existsId);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error en la consulta a la base de datos. {}", e);
            throw new RuntimeException("Ocurrio un error al persistir el error en base de datos.");
        }
        if (flagExists) {
            LOGGER.error("El id {} ya esta registrado en la Base de datos", id);
            ExistsErrorResponse existsErrorResponse = ExistsErrorResponse.builder()
                    .exists(Boolean.TRUE)
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", existsErrorResponse);
            return existsErrorResponse;
        }else{
            LOGGER.error("El id {} no se encuentra registrado en la Base de datos", id);
            ExistsErrorResponse existsErrorResponse = ExistsErrorResponse.builder()
                    .exists(Boolean.FALSE)
                    .build();
            LOGGER.info("Finaliza la ejecucion. Response: {}", existsErrorResponse);
            return existsErrorResponse;
        }
    }
}
