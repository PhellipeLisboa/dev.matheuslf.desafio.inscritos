package dev.matheuslf.desafio.inscritos.exception.handler;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp,
        String path,
        List<String> errors
) {
}
