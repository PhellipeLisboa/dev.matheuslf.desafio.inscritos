package dev.matheuslf.desafio.inscritos.service;

import dev.matheuslf.desafio.inscritos.dto.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dto.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dto.TaskStatusUpdateDto;
import dev.matheuslf.desafio.inscritos.entity.Priority;
import dev.matheuslf.desafio.inscritos.entity.Project;
import dev.matheuslf.desafio.inscritos.entity.Status;
import dev.matheuslf.desafio.inscritos.entity.Task;
import dev.matheuslf.desafio.inscritos.exception.ResourceNotFoundException;
import dev.matheuslf.desafio.inscritos.exception.InvalidTaskDueDateException;
import dev.matheuslf.desafio.inscritos.exception.TaskAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.mapper.TaskMapper;
import dev.matheuslf.desafio.inscritos.repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public TaskResponseDto createTask(TaskRequestDto dto) {

        Project project = projectRepository.findById(dto.projectId()).orElseThrow(
                () -> new ResourceNotFoundException("Projeto", dto.projectId())
        );

        if (taskRepository.existsByTitleAndProjectId(dto.title(), dto.projectId())) {
            throw new TaskAlreadyExistsException(dto.title());
        }

        if (dto.dueDate().before(project.getStartDate()) || dto.dueDate().after(project.getEndDate())) {
            throw new InvalidTaskDueDateException(project.getStartDate(), project.getEndDate());
        }

        Task task = TaskMapper.toEntity(dto, project);

        Task createdTask = taskRepository.save(task);

        return TaskMapper.toDto(createdTask);

    }

    public List<TaskResponseDto> listTasks(Status status, Priority priority, Long projectId) {

        return taskRepository.findByFilters(status, priority, projectId)
                .stream()
                .map(TaskMapper::toDto)
                .toList();

    }

    public TaskResponseDto updateTaskStatus(Long id, TaskStatusUpdateDto dto) {

        Task taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Tarefa", id)
        );

        taskEntity.setStatus(dto.status());

        Task updatedTask = taskRepository.save(taskEntity);

        return TaskMapper.toDto(updatedTask);

    }

    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa", id);
        }

        taskRepository.deleteById(id);

    }

}
