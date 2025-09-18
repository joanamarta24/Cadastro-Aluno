package domain.entity.repository;


import entity.CadastroAluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastraAlunoRepository extends JpaRepository<CadastroAluno, Long> {
}
