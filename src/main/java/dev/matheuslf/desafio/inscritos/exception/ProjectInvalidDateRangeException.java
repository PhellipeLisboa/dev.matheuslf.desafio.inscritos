package dev.matheuslf.desafio.inscritos.exception;

public class ProjectInvalidDateRangeException extends RuntimeException{

    public ProjectInvalidDateRangeException() {
        super("A data de encerramento do projeto não pode ser anterior à data de início.");
    }

}
