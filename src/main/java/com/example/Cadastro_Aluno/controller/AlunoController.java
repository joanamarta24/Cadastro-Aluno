package com.example.Cadastro_Aluno.controller;

import com.example.Cadastro_Aluno.Aluno;
import entity.CadastroAluno;
import org.springframework.web.bind.annotation.*;
import domain.entity.service.CadastroAlunoService;

import java.util.List;

public class AlunoController {
    private final CadastroAlunoService alunoService;
    public AlunoController
            (CadastroAlunoService cadastroAlunoService, CadastroAlunoService alunoService){
        this.alunoService = alunoService;
        CadastroAlunoService alunoService1 = this.alunoService;

    }
    @GetMapping
    public List<CadastroAluno> listar(){
        return alunoService.listarTodos();
    }
    @GetMapping("/{id}")
    public CadastroAluno buscar(@PathVariable Long id){
        return  alunoService.buscarPorId(id);
    }
    @PostMapping
    public CadastroAluno cadastrar(@RequestBody Aluno aluno){
        return alunoService.salvar(aluno);
    }
    @PutMapping
    public CadastroAlunoatualizar(@PathVariable Long id, @RequestBody Aluno novoAluno){
        CadastroAluno alunoExistente = alunoService.buscarPorId(id);
        if(alunoExistente != null){
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setIdade(novoAluno.getIdade());
            alunoExistente.setCurso(novoAluno.getCurso());
            return  alunoService.salvar(alunoExistente);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        alunoService.deletar(id);
    }
}
