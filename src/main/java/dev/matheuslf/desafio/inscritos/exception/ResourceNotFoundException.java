package dev.matheuslf.desafio.inscritos.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s com id %d não encontrado.", resourceName, id));
    }

}
