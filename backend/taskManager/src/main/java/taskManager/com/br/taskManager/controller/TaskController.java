package taskManager.com.br.taskManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskManager.com.br.taskManager.dto.TaskDTO;
import taskManager.com.br.taskManager.enums.*;
import taskManager.com.br.taskManager.model.Task;
import taskManager.com.br.taskManager.service.TaskService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) PriorityLevel priority,
            @RequestParam(required = false) SystemUser responsible) {
        return ResponseEntity.ok(taskService.getAllTasks(status, priority, responsible));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enums")
    public ResponseEntity<Map<String, Object>> getAllEnums() {
        return ResponseEntity.ok(taskService.getAllEnums());
    }
} 