package com.errormanager.controller;

import com.errormanager.dto.createerror.CreateErrorRequest;
import com.errormanager.dto.createerror.CreateErrorResponse;
import com.errormanager.dto.geterror.GetErrorRequest;
import com.errormanager.dto.geterror.GetErrorResponse;
import com.errormanager.service.ErrorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Error Manager", description = "Microservicio para la gestion de los errores")
@Validated
@RestController
@RequestMapping(ErrorController.URI)
public class ErrorController {

    public static final String URI = "/error";
    public static final String GET_ERROR = "/get-error";
    public static final String CREATE_ERROR = "/create-error";


    ErrorService errorService;

    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Operation(summary = "Recupera un error a partir de su Id.", description = "Recupera un error a partir de su Id.", tags = {
            "Error Manager"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
    })
    @CrossOrigin
    @PostMapping(path = GET_ERROR, produces = "application/json")
    public GetErrorResponse getError(@RequestBody GetErrorRequest getErrorRequest){
        return errorService.getError(getErrorRequest);
    }

    @Operation(summary = "Crea un error.", description =  "Crea un error.", tags = {
            "Error Manager"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateErrorResponse.class))),
    })
    @CrossOrigin
    @PostMapping(path = CREATE_ERROR, produces = "application/json")
    public CreateErrorResponse createError(@RequestBody CreateErrorRequest createErrorRequest){
        return errorService.createError(createErrorRequest);
    }

}
