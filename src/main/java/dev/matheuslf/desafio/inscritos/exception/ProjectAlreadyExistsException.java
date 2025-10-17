package dev.matheuslf.desafio.inscritos.exception;

public class ProjectAlreadyExistsException extends RuntimeException{

    public ProjectAlreadyExistsException(String projectName) {
        super(String.format("Já existe um projeto com o nome '%s'", projectName));
    }

}
