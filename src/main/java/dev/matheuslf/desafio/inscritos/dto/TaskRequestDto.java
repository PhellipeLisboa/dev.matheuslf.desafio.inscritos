package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.entity.Priority;
import dev.matheuslf.desafio.inscritos.entity.Status;
import jakarta.validation.constraints.*;

import java.util.Date;

public record TaskRequestDto(

        @NotBlank(message = "O título da tarefa é obrigatório.")
        @Size(min = 5, max = 150, message = "O título deve ter entre 5 e 150 caracteres.")
        String title,
        String description,
        @NotNull(message = "O status da tarefa é obrigatório.")
        Status status,
        @NotNull(message = "A prioridade da tarefa é obrigatória.")
        Priority priority,
        @NotNull(message = "A data limite da tarefa é obrigatória.")
        @FutureOrPresent(message = "A data limite da tarefa não pode ser no passado.")
        Date dueDate,
        @NotNull(message = "O Id do projeto é obrigatório.")
        @Positive(message = "O Id do projeto deve ser maior que zero.")
        Long projectId

) {
}
