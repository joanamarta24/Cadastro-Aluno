package com.example.Cadastro_Aluno.controller;

import com.example.Cadastro_Aluno.dto.CadastroAlunoDTO;
import com.example.Cadastro_Aluno.dto.CadastroAlunoResponseDTO;
import com.example.Cadastro_Aluno.entity.CadastroAluno;
import com.example.Cadastro_Aluno.service.AlunoService;
import domain.entity.service.CadastroAlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/alunos")
@Tag(name = "Cadastro de Alunos", description = "API para gerenciamento de cadastro de alunos")
public class CadastroAlunoController {

    private final CadastroAlunoService alunoService;

    // Construtor simplificado e correto
    public CadastroAlunoController(CadastroAlunoService alunoService) {
        this.alunoService = alunoService;
        log.info("CadastroAlunoController inicializado com sucesso");
    }

    @GetMapping
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista paginada de todos os alunos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos listados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum aluno encontrado")
    })
    public ResponseEntity<Page<CadastroAlunoResponseDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        log.info("Recebida requisição para listar alunos - página: {}, tamanho: {}", page, size);

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<CadastroAlunoResponseDTO> alunos = alunoService.listarTodos(pageable);

        if (alunos.isEmpty()) {
            log.warn("Nenhum aluno encontrado na base de dados");
            return ResponseEntity.noContent().build();
        }

        log.info("Retornando {} alunos", alunos.getTotalElements());
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID", description = "Retorna os detalhes de um aluno específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<CadastroAlunoResponseDTO> buscarPorId(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id) {

        log.info("Buscando aluno com ID: {}", id);

        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    log.info("Aluno encontrado: {}", aluno.getNome());
                    return ResponseEntity.ok(aluno);
                })
                .orElseGet(() -> {
                    log.warn("Aluno com ID {} não encontrado", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/matricula/{matricula}")
    @Operation(summary = "Buscar aluno por matrícula", description = "Retorna os detalhes de um aluno pela matrícula")
    public ResponseEntity<CadastroAlunoResponseDTO> buscarPorMatricula(
            @Parameter(description = "Número da matrícula", example = "2023001")
            @PathVariable String matricula) {

        log.info("Buscando aluno com matrícula: {}", matricula);

        return alunoService.buscarPorMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Aluno com matrícula {} não encontrado", matricula);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo aluno", description = "Cria um novo registro de aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CadastroAlunoResponseDTO> cadastrar(
            @Valid @RequestBody CadastroAlunoDTO dto) {

        log.info("Recebida requisição para cadastrar novo aluno: {}", dto.getNome());

        try {
            CadastroAlunoResponseDTO alunoSalvo = alunoService.cadastrar(dto);
            log.info("Aluno cadastrado com sucesso: {} - ID: {}", alunoSalvo.getNome(), alunoSalvo.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
        } catch (Exception e) {
            log.error("Erro ao cadastrar aluno: {}", e.getMessage(), e);
            throw e; // Deixe o ExceptionHandler tratar
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar aluno", description = "Atualiza os dados de um aluno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<CadastroAlunoResponseDTO> atualizar(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody CadastroAlunoResponseDTO dto) {

        log.info("Recebida requisição para atualizar aluno ID: {}", id);

        try {
            CadastroAlunoResponseDTO alunoAtualizado = alunoService.atualizar(id, dto);
            log.info("Aluno ID {} atualizado com sucesso", id);
            return ResponseEntity.ok(alunoAtualizado);
        } catch (RuntimeException e) {
            log.warn("Aluno com ID {} não encontrado para atualização", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir aluno", description = "Remove um aluno da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do aluno", example = "1")
            @PathVariable Long id) {

        log.info("Recebida requisição para excluir aluno ID: {}", id);

        try {
            alunoService.deletar(id);
            log.info("Aluno ID {} excluído com sucesso", id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.warn("Aluno com ID {} não encontrado para exclusão", id);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Cadastrar novo aluno")
    public ResponseEntity<CadastroAlunoResponseDTO> cadastrar(
            @Valid @RequestBody CadastroAlunoRequestDTO request) {

        CadastroAlunoResponseDTO response = alunoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluno por ID")
    public ResponseEntity<CadastroAlunoResponseDTO> buscarPorId(@PathVariable Long id) {

        CadastroAlunoResponseDTO response = alunoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/curso/{curso}")
    @Operation(summary = "Listar alunos por curso", description = "Retorna alunos filtrados por curso")
    public ResponseEntity<List<CadastroAlunoResponseDTO>> listarPorCurso(
            @Parameter(description = "Nome do curso", example = "Engenharia")
            @PathVariable String curso) {

        log.info("Buscando alunos do curso: {}", curso);

        List<CadastroAlunoResponseDTO> alunos = alunoService.listarPorCurso(curso);

        if (alunos.isEmpty()) {
            log.warn("Nenhum aluno encontrado para o curso: {}", curso);
            return ResponseEntity.noContent().build();
        }

        log.info("Encontrados {} alunos do curso {}", alunos.size(), curso);
        return ResponseEntity.ok(alunos);
    }
}