package com.errormanager.dto.createerror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateErrorRequest {
    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(required = true, description = "Nombre del servicio en el que se genero el error.")
    private String serviceName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(required = true, description = "Nombre del endpoint en el que se genero el error.")
    private String endpointName;
    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(required = true, description = "Codigo identificador del error.")
    private String errorId;
    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(required = true, description = "Mensaje de respuesta frente al error.")
    private String errorMessage;

    @NotNull
    @NotEmpty
    @NotBlank
    @Schema(required = true, description = "Codigo de estado http asociado al error.")
    private String errorCode;
}
