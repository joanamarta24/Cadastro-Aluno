package com.example.Cadastro_Aluno.controller;

import domain.entity.repository.CadastraAlunoRepository;
import entity.CadastroAluno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entity.service.CadastroAlunoService;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.util.ReactiveWrapperConverters.map;

public class CadatroAlunoController {
    private final CadastroAlunoService alunoService;

    public CadatroAlunoController
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
        .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    public Optional<CadastroAluno> buscarPorId(Long id){
        return CadastraAlunoRepository.findById(id)
                .map(cadastroAluno -> new CadastroAluno(
                        alunoService.getId(),
                        alunoService.getNome(),
                        alunoService.getIdade(),
                        alunoService.getCurso()
                ));
    }
    @PostMapping
    public CadastroAluno cadastrar(@RequestBody Aluno aluno){
        return alunoService.salvar(aluno);
    }
    @PutMapping
    public CadastroAluno atualizar(@PathVariable Long id, @RequestBody Aluno novoAluno){
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
