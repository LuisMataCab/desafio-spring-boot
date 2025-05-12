package cl.nuevaspa.test.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gestor de Tareas para Usuarios",
                version = "1.0.0",
                description = "Servicios para crear, modificar, buscar o eliminar tareas"
        )
)
public class OpenApiConfig {
}
