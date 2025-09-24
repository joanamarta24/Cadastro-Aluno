package com.example.Cadastro_Aluno.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record cadastroAlunodto(
 @Schema(description = "ID do aluno",example = "5") Long id,

 @NotBlank(message = "Nome é obrigatório")
 @Size(min =  3, max = 120, message = )
)
{}