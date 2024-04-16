package github.sagubr;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

import java.util.logging.Logger;

@OpenAPIDefinition(
        info = @Info(
                title = "nfce-reading",
                version = "0.0"
        )
)
public class Application {

    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
        log.info("Swagger initialized: http://localhost:8083/swagger-ui/index.html#/");
    }
}