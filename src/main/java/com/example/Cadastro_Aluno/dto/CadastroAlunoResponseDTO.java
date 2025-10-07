package com.example.Cadastro_Aluno.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Schema(description =  "DTO de resposta para cadastro de aluno")
public record CadastroAlunoResponseDTO(
        @Schema(description =  "Id do Aluno",example = "1")
        Long id,
        @Schema(description = "Nome xi")
)  {

}