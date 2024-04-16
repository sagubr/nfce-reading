package github.sagubr.controllers;

import github.sagubr.entities.core.Product;
import github.sagubr.services.ProductService;
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

import java.util.List;

@Tag(name= "PRODUCTS")
@Controller("/products")
@Serdeable
@Secured(SecurityRule.IS_AUTHENTICATED)
public class ProductController {

    @Inject
    ProductService productService;

    @Operation(summary = "Obtém todos os produtos do banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Produtos obtidos com sucesso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @Get
    @Consumes(MediaType.APPLICATION_JSON)
    @Status(HttpStatus.ACCEPTED)
    public List<Product> get() {
        return productService.findAll();
    }
}
