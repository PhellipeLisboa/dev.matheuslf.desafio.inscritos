package dev.matheuslf.desafio.inscritos.exception;

public class TaskAlreadyExistsException extends RuntimeException{

    public TaskAlreadyExistsException(String title) {
        super(String.format("Já existe uma tarefa com o título '%s' neste projeto.", title));
    }

}
