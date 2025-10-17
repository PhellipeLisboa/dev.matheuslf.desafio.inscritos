package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.entity.Status;
import jakarta.validation.constraints.NotNull;

public record TaskStatusUpdateDto(

        @NotNull(message = "O novo status da tarefa é obrigatório.")
        Status status

) {
}
