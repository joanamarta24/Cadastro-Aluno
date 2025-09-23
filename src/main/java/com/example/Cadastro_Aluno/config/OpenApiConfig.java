package com.example.Cadastro_Aluno.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI api(){
        return  new OpenAPI().info()
                .title("Api Escola(DDD + Security + Soft Delete)")
                .version("1.0.0")
                .description("Exemplo educacional com autenticação JWT,regras de negócios e documentos");
    }
}
