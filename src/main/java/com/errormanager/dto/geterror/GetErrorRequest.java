package com.errormanager.dto.geterror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
@AllArgsConstructor
@Data
public class GetErrorRequest {

    @NonNull
    @Schema(required = true, description = "Nombre del servicio en el que se genero el error.")
    private String serviceName;
    @NonNull
    @Schema(required = true, description = "Nombre del endpoint en el que se genero el error.")
    private String endpointName;
    @NonNull
    @Schema(required = true, description = "Codigo identificador del error.")
    private String errorId;
}
