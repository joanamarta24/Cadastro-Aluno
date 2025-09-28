package com.example.Cadastro_Aluno.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.*;

public record CadastroAlunoDTO(

        @Schema(description = "ID do aluno", example = "1")
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        @Schema(description = "Nome completo do aluno", example = "João da Silva")
        String nome,

        @NotNull(message = "Idade é obrigatória")
        @Min(value = 10, message = "Idade mínima do aluno é 10 anos")
        @Max(value = 100, message = "Idade máxima permitida é 100 anos")
        @Schema(description = "Idade do aluno", example = "20")
        Integer idade,

        @NotBlank(message = "Curso é obrigatório")
        @Schema(description = "Curso que o aluno está matriculado", example = "Engenharia de Software")
        String curso
) {}
