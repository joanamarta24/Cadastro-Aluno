package com.example.Cadastro_Aluno.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de resposta para cadastro de aluno")
public record CadastroAlunoResponseDTO() {
        @Schema(description = "Id do aluno," example $anchor = "1")
        private static Long id;

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        @Schema(description = "Nome completo do aluno", example = "João da Silva")
        private static String nome;

        @Schema(description = "Número da matrícula", example = "2023001")
        private static String matricula;

        @NotBlank(message = "Curso é obrigatório")
        @Schema(description = "Curso que o aluno está matriculado", example = "Engenharia de Software")
        private static String curso;

        @NotNull(message = "Idade é obrigatória")
        @Min(value = 10, message = "Idade mínima do aluno é 10 anos")
        @Max(value = 100, message = "Idade máxima permitida é 100 anos")
        @Schema(description = "Idade do aluno", example = "20")
        private static Integer idade;

        @Schema(description = "Data e hora do cadastro", example = "2023-10-01T10:30:00")
        private static LocalDateTime dataCadastro;

        @Schema(description = "Situação de idade do aluno", example = "Maior de idade")
        private static String situacaoIdade;
}