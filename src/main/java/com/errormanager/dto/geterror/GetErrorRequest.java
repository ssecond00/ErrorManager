package com.errormanager.dto.geterror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class GetErrorRequest {

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
}
