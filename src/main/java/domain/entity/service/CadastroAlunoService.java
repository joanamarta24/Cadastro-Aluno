package domain.entity.service;


import com.example.Cadastro_Aluno.dto.CadastroAlunoResponseDTO;
import domain.entity.repository.CadastraAlunoRepository;
import entity.CadastroAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class CadastroAlunoService {
   private final CadastraAlunoRepository;

    public CadastroAlunoServicea(CadastraAlunoRepository){

        @Autowired
     private CadastraAlunoRepository alunoRepository;
        return CadastraAlunoRepository.findAll();
    }

    public List<CadastraAlunoRepository> findAll(){
        return CadastraAlunoRepository.finById(id);
    }

    public  Optional<CadastroAluno> finById(Long id){
        return CadastraAlunoRepository.findById(id);
    }

    public CadastroAluno save(CadastroAluno cadastroAluno){
        return CadastraAlunoRepository.save(cadastroAluno);
    }

    public CadastroAluno update(Long id, CadastroAluno cadastroAlunoDetails){
        Optional<CadastroAluno> optionalCadastroAluno= CadastraAlunoRepository.findById(id);
        if (optionalCadastroAluno.isPresent()){
            CadastroAluno existingAluno = optionalCadastroAluno.get();
            existingAluno.setMatricula(cadastroAlunoDetails.getNome());
            existingAluno.setMatricula(cadastroAlunoDetails.getMatricula());
            existingAluno.setCurso(cadastroAlunoDetails.getCurso());
            existingAluno.setIdade(cadastroAlunoDetails.getIdade());
            return CadastraAlunoRepository.save(existingAluno);
        }else{
            return null;
        }
    }

    public boolean delete(Long id){
        if (CadastraAlunoRepository.existsById(id)){
            CadastraAlunoRepository.deleteById(id);
            return true
        }
        return false;
    }

    public Page<CadastroAlunoResponseDTO> listarTodos(Pageable pageable) {
        return null;
    }

    public Optional<Object> buscarPorMatricula(String matricula) {
        return null;
    }
}
