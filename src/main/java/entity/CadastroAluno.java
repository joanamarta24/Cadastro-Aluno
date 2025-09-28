package entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public  class CadastroAluno {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Matricula é obrigatória")
    @Size(min = 3, max = 20, message = "Matrícula deve ter entre 3 e 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private  String matricula;

    @NotBlank (message = "Curso é obrigatório")
    @Size(min = 2, max = 50, message = "Curso deve ter entre 2 e 50 caracteres")
    @Column(nullable = false, length = 50)
    private String curso;

    @NotNull(message = "Idade é obrigatória")
    @Min(value = 16, message = "Idade mínima é 16 anos")
    @Max(value = 100, message = "Idade máxima é 100 anos")
    @Column(nullable = false)
    private Integer idade;


    public boolean booleanisMAiorIdade(){
        return this.idade >= 18;
    }
    public String getSituacaoIdade(){
        return this.idade >= 18? "Maior de idade" : "Menor de idade";
    }
    public static CadastroAlunoBuilder builder(){
        return new CadastroAlunoBuilder();
    }
    public static  class  CadastroAlunoBuilder{
        private Long id;
        private String nome;
        private String matricula;
        private String curso;
        private Integer idade;
    }
    public CadastroAluno id(Long id){
        this.id = id;
        return this;
    }
    public CadastroAluno nome(String nome) {
        this.nome = nome;
        return this;
    }
    public CadastroAluno matricula(String matricula) {
        this.matricula = matricula;
        return this;
    }
    public CadastroAluno curso(String curso) {
        this.curso = curso;
        return this;
    }
    public CadastroAluno idade(Integer idade) {
        this.idade = idade;
        return this;
    }
    public CadastroAluno build() {
        return new CadastroAluno(id, nome, matricula, curso, idade);
    }

    @Override
    public String toString() {
        return "CadastroAluno{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", curso='" + curso + '\'' +
                ", idade=" + idade +
                '}';
    }
    public boolean isValid() {
        return nome != null && !nome.trim().isEmpty() &&
                matricula != null && !matricula.trim().isEmpty() &&
                curso != null && !curso.trim().isEmpty() &&
                idade != null && idade >= 16 && idade <= 100;
    }
}