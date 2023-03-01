package com.errormanager.dto.createerror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CreateErrorRequest {
    @NonNull
    @Schema(required = true, description = "Nombre del servicio en el que se genero el error.")
    private String serviceName;
    @NonNull
    @Schema(required = true, description = "Nombre del endpoint en el que se genero el error.")
    private String endpointName;
    @NonNull
    @Schema(required = true, description = "Codigo identificador del error.")
    private String errorId;
    @NonNull
    @Schema(required = true, description = "Mensaje de respuesta frente al error.")
    private String errorMessage;

    @NonNull
    @Schema(required = true, description = "Codigo de estado http asociado al error.")
    private String errorCode;
}
