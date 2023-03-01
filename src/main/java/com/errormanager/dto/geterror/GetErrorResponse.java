package com.errormanager.dto.geterror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class GetErrorResponse {

    @NonNull
    @Schema(required = true, description = "Mensaje de respuesta frente al error.")
    private String errorMessage;

    @NonNull
    @Schema(required = true, description = "Codigo de estado http asociado al error.")
    private String errorCode;
}
