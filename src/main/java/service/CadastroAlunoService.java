package service;

import com.example.Cadastro_Aluno.CadatrarAluno;
import com.example.Cadastro_Aluno.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class CadastroAlunoService {
   private final AlunoRepository;
    public CadastroAlunoService(AlunoRepository){
        @Autowired


    }
}
