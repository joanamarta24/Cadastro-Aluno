package domain.entity.service;


import domain.entity.repository.CadastraAlunoRepository;
import entity.CadastroAluno;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AlunoService {
   private final CadastraAlunoRepository;
    public AlunoService(CadastraAlunoRepository){
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
}
