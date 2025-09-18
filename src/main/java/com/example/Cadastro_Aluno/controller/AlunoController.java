package com.example.Cadastro_Aluno.controller;

import com.example.Cadastro_Aluno.CadatrarAluno;
import org.springframework.web.bind.annotation.*;
import service.CadastroAlunoService;

import java.util.List;

public class AlunoController {
    private final CadastroAlunoService alunoService;
    public AlunoController(CadastroAlunoService cadastroAlunoService, CadastroAlunoService alunoService){
        this.alunoService = alunoService;
        CadastroAlunoService alunoService1 = this.alunoService;

    }
    @GetMapping
    public List<CadatrarAluno> listar(){
        return alunoService.listarTodos();
    }
    @GetMapping("/{id}")
    public CadatrarAluno buscar(@PathVariable Long id){
        return  alunoService.buscarPorId(id);
    }
    @PostMapping
    public CadatrarAluno cadastrar(@RequestBody CadatrarAluno cadatrarAluno){
        return alunoService.salvar(cadatrarAluno);
    }
    @PutMapping
    public CadatrarAluno atualizar(@PathVariable Long id, @RequestBody CadatrarAluno novoAluno){
        CadatrarAluno alunoExistente = alunoService.buscarPorId(id);
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
