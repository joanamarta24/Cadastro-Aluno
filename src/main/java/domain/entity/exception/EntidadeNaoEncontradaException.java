package domain.entity.exception;

public class EntidadeNaoEncontradaException extends  RuntimeException{
    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
