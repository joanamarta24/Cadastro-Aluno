package com.example.Cadastro_Aluno.repository;

import com.example.Cadastro_Aluno.CadatrarAluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<CadatrarAluno, Long> {
}
