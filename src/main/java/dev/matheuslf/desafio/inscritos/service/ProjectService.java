package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.dto.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.entity.Project;
import dev.matheuslf.desafio.inscritos.mapper.ProjectMapper;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectResponseDto createProject(ProjectRequestDto dto) {

        if (dto.name() == null || dto.name().isBlank()) {
            throw new IllegalArgumentException("O nome do projeto é obrigatório!");
        }

        if (dto.endDate().before(dto.startDate())) {
            throw new IllegalArgumentException("A data de encerramento do projeto não pode ser anterior à data de início.");
        }

        Project project = ProjectMapper.toEntity(dto);

        Project projectCreated = projectRepository.save(project);

        return ProjectMapper.toDto(projectCreated);

    }

    public List<ProjectResponseDto> listProjects() {

        return projectRepository.findAll()
                .stream()
                .map(ProjectMapper::toDto)
                .toList();

    }

}
