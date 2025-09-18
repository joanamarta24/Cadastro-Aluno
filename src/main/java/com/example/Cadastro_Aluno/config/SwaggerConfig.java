package com.example.Cadastro_Aluno.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI oficinaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Cursos Técnicos e Livres")
                        .description("Cadastro de Alunos na plataforma tanto técnico quanto cursos livres")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Secretaria dos Cursos")
                                .email("senai@sesi.com")));
    }
}
