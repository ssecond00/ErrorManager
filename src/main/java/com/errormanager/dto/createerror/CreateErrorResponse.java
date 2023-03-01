package com.errormanager.dto.createerror;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CreateErrorResponse {
    @NonNull
    @Schema(required = true, description = "Bandera que indica si se logro persistir el registro correctamente en la base de datos.")
    private Boolean created;
}
