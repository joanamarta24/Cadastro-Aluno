package com.example.Cadastro_Aluno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenApi oficinaOpenAPI(){
        return new OpenAPI()
                .info(new Info())
                .title("API-Cursos Tecnicos e Livres");
                .description("Cadastro de Alunos na plataforma tanto tecnico e cursos livres");
                .version("1.0");
        .contact(new Contact()
                .name("Secretaria dos Cursos")
                .email("senai@sesi.com"));

    }
}
