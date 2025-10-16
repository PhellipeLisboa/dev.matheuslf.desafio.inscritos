package dev.matheuslf.desafio.inscritos.dto;

import dev.matheuslf.desafio.inscritos.entity.Priority;
import dev.matheuslf.desafio.inscritos.entity.Status;

import java.util.Date;

public record TaskResponseDto(

        Long id,
        String title,
        String description,
        Status status,
        Priority priority,
        Date dueDate,
        String projectName,
        Long projectId

) {
}
