package github.sagubr.controllers;

import github.sagubr.entities.Address;
import github.sagubr.services.InspectorService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

import java.util.List;

@Tag(name= "INSPECTOR")
@Controller("/inspector")
@Serdeable
@Secured(SecurityRule.IS_AUTHENTICATED)
public class InspectorController {

    @Inject
    InspectorService inspectorService;

    @Operation(summary = "Processa a url do QRCODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações da NF-c foram persistidas no Banco de Dados"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @Post(value = "/process-url")
    @Consumes(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.CREATED)
    public void processUrl(@Body @Valid Address address) {
        inspectorService.processUrl(address);
    }

}
