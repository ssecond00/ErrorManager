package com.errormanager.controller;

import com.errormanager.dto.createerror.CreateErrorRequest;
import com.errormanager.dto.createerror.CreateErrorResponse;
import com.errormanager.dto.exists.ExistsErrorResponse;
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

import javax.validation.Valid;

@Tag(name = "Error Manager", description = "Microservicio para la gestion de los errores")
@Validated
@RestController
@RequestMapping(ErrorController.URI)
public class ErrorController {

    public static final String URI = "/error";
    public static final String GET_ERROR = "/get-error";
    public static final String CREATE_ERROR = "/create-error";

    public static final String EXISTS_ERROR = "/exists-error/{service}/{endpoint}/{id}";


    ErrorService errorService;

    public ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @Operation(summary = "Recupera un error a partir de su Id.", description = "Recupera un error a partir de su Id.", tags = {
            "Error Manager"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
    })
    @CrossOrigin
    @PostMapping(path = GET_ERROR, produces = "application/json")
    public GetErrorResponse getError(@Valid @RequestBody GetErrorRequest getErrorRequest){
        return errorService.getError(getErrorRequest);
    }

    @Operation(summary = "Crea un error.", description =  "Crea un error.", tags = {
            "Error Manager"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
    })
    @CrossOrigin
    @PostMapping(path = CREATE_ERROR, produces = "application/json")
    public CreateErrorResponse createError(@Valid @RequestBody CreateErrorRequest createErrorRequest){
        return errorService.createError(createErrorRequest);
    }
    @Operation(summary = "Consulta si un error ya existe en la base de datos.", description =  "Consulta si un error ya existe en la base de datos." , tags = {
            "Error Manager"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GetErrorResponse.class))),
    })
    @CrossOrigin
    @GetMapping(path = EXISTS_ERROR, produces =  "application/json")
    public ExistsErrorResponse existsError(@Valid @PathVariable String service, @Valid @PathVariable String endpoint, @Valid @PathVariable String id){
        return errorService.existsError(service, endpoint, id);
    }
}
