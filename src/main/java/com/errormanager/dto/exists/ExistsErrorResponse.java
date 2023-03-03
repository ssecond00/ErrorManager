package com.errormanager.dto.exists;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Builder
@AllArgsConstructor
@Data
public class ExistsErrorResponse {
    @Schema(required = true, description = "Flag que indica si el mensaje solicitado existe.")
    private Boolean exists;

}
