package dev.matheuslf.desafio.inscritos.mapper;

import dev.matheuslf.desafio.inscritos.dto.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.entity.Project;

public class ProjectMapper {

    public static Project toEntity(ProjectRequestDto dto) {

        Project project = new Project(
                dto.name(),
                dto.description(),
                dto.startDate(),
                dto.endDate()

        );

        project.setTasks(
                dto.tasks()
                .stream()
                .map(taskDto -> TaskMapper.toEntity(taskDto, project))
                .toList()
        );

        return project;

    }

    public static ProjectResponseDto toDto(Project project) {

        return new ProjectResponseDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTasks()
                        .stream()
                        .map(TaskMapper::toDto)
                        .toList()
        );

    }

}
