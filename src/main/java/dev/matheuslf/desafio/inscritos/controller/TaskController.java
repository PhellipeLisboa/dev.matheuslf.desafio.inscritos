package dev.matheuslf.desafio.inscritos.controller;

import dev.matheuslf.desafio.inscritos.dto.TaskRequestDto;
import dev.matheuslf.desafio.inscritos.dto.TaskResponseDto;
import dev.matheuslf.desafio.inscritos.dto.TaskStatusUpdateDto;
import dev.matheuslf.desafio.inscritos.entity.Priority;
import dev.matheuslf.desafio.inscritos.entity.Status;
import dev.matheuslf.desafio.inscritos.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto dto) {

        TaskResponseDto createdTask = taskService.createTask(dto);

        URI location = URI.create("/tasks/" + createdTask.id());

        return ResponseEntity.created(location).body(createdTask);

    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> listTasks(@RequestParam(required = false) Status status,
                                                           @RequestParam(required = false) Priority priority,
                                                           @RequestParam(required = false) @Positive(message = "O id " +
                                                                   "do projeto deve ser maior que zero.") Long projectId
    ) {

        List<TaskResponseDto> result = taskService.listTasks(status, priority, projectId);

        return ResponseEntity.ok(result);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(@PathVariable @Positive(message = "O id da tarefa deve " +
            "ser maior que zero.") Long id, @Valid @RequestBody TaskStatusUpdateDto dto) {

        TaskResponseDto updatedTask = taskService.updateTaskStatus(id, dto);

        return ResponseEntity.ok(updatedTask);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable @Positive(message = "O id da tarefa deve ser maior que zero.")
                                               Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
