package com.example.Cadastro_Aluno.controller;

import com.example.Cadastro_Aluno.dto.CadastroAlunoDTO;
import domain.entity.repository.CadastraAlunoRepository;
import entity.CadastroAluno;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import domain.entity.service.AlunoService;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.util.ReactiveWrapperConverters.map;
@RestController
@RequestMapping
public class CadatroAlunoController {
    private final AlunoService alunoService;

    public CadatroAlunoController
            (AlunoService cadastroAlunoService, AlunoService alunoService){
        this.alunoService = alunoService;
        AlunoService alunoService1 = this.alunoService;

    }
    @GetMapping
    public ResponseEntity<List<CadastroAluno>listar>{
        List<CadastroAluno> alunos = alunoService.listarTodos();
        if(alunos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alunos);
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
    public ResponseEntity<CadastroAlunoDTO> cadastrar(@Valid @RequestBody CadastroAlunoDTO dto) {
        CadastroAlunoDTO salvo = alunoService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
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
