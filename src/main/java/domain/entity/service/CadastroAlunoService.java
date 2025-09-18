package domain.entity.service;

import com.example.Cadastro_Aluno.CadatrarAluno;
import domain.entity.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CadastroAlunoService {
   private final AlunoRepository;
    public CadastroAlunoService(AlunoRepository){
        @Autowired


    }
}
