package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.ProjectRequestDto;
import dev.matheuslf.desafio.inscritos.dto.ProjectResponseDto;
import dev.matheuslf.desafio.inscritos.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody ProjectRequestDto dto) {

        ProjectResponseDto createdProject = projectService.createProject(dto);

        URI location = URI.create("/projects/" + createdProject.id());

        return ResponseEntity.created(location).body(createdProject);

    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> listProjects() {
        return ResponseEntity.ok(projectService.listProjects());
    }

}
