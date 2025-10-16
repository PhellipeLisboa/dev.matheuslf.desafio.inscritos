package dev.matheuslf.desafio.inscritos.mapper;

import dev.matheuslf.desafio.inscritos.dto.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.dto.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dto.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.entity.Project;
import dev.matheuslf.desafio.inscritos.entity.Task;

public class TaskMapper {

    public static Task toEntity(TaskRequestDto dto, Project project) {

        return new Task(
                dto.title(),
                dto.description(),
                dto.status(),
                dto.priority(),
                dto.dueDate(),
                project
        );

    }

    public static TaskResponseDto toDto(Task task) {

        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getProject().getName(),
                task.getProject().getId()

        );

    }

}
