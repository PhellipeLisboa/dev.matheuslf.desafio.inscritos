package dev.matheuslf.desafio.inscritos.exception.handler;

import dev.matheuslf.desafio.inscritos.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        return new ErrorResponse(
                message,
                status.value(),
                LocalDateTime.now(),
                getPath(request),
                null
        );
    }

    private ErrorResponse buildValidationErrorResponse(List<String> errors, HttpStatus status, WebRequest request) {
        return new ErrorResponse(
                "Erro de validação",
                status.value(),
                LocalDateTime.now(),
                getPath(request),
                errors
        );
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private ResponseEntity<ErrorResponse> buildResponse(Exception ex, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).body(buildErrorResponse(ex.getMessage(), status, request));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ProjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProjectAlreadyExists(ProjectAlreadyExistsException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(TaskAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleTaskAlreadyExists(TaskAlreadyExistsException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ProjectInvalidDateRangeException.class)
    public ResponseEntity<ErrorResponse> handleProjectInvalidDateRange(ProjectInvalidDateRangeException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidTaskDueDateException.class)
    public ResponseEntity<ErrorResponse> handleTaskDateOutOfProjectRange(InvalidTaskDueDateException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethoArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildValidationErrorResponse(errors, HttpStatus.BAD_REQUEST, request));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String fieldName = ex.getName();
        String providedType = ex.getValue().toString();
        String requiredType = ex.getRequiredType().getSimpleName();

        String message = String.format("O parâmetro '%s' deve ser do tipo %s. (Tipo recebido: %s)", fieldName, requiredType, providedType);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrorResponse(message, HttpStatus.BAD_REQUEST, request));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
