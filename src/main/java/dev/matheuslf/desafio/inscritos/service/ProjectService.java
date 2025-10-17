package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.dto.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.entity.Project;
import dev.matheuslf.desafio.inscritos.exception.ProjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.exception.ProjectInvalidDateRangeException;
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

        if (dto.endDate().before(dto.startDate())) {
            throw new ProjectInvalidDateRangeException();
        }

        if (projectRepository.existsByName(dto.name())) {
            throw new ProjectAlreadyExistsException(dto.name());
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
