package taskManager.com.br.taskManager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskManager.com.br.taskManager.dto.TaskDTO;
import taskManager.com.br.taskManager.model.Task;
import taskManager.com.br.taskManager.repository.TaskRepository;
import taskManager.com.br.taskManager.enums.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getAllTasks(TaskStatus status, PriorityLevel priority, SystemUser responsible) {
        return taskRepository.findTasksByFilters(status, priority, responsible);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + id));
    }

    @Transactional
    public Task createTask(TaskDTO taskDTO) {
        validateTaskDTO(taskDTO);
        Task task = new Task();
        updateTaskFromDTO(task, taskDTO);
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, TaskDTO taskDTO) {
        validateTaskDTO(taskDTO);
        Task existingTask = getTaskById(id);
        updateTaskFromDTO(existingTask, taskDTO);
        return taskRepository.save(existingTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada com ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    public Map<String, Object> getAllEnums() {
        Map<String, Object> enums = new HashMap<>();
        enums.put("status", Arrays.stream(TaskStatus.values())
                .map(TaskStatus::getDisplayName)
                .collect(Collectors.toList()));
        enums.put("priorities", Arrays.stream(PriorityLevel.values())
                .map(PriorityLevel::getDisplayName)
                .collect(Collectors.toList()));
        enums.put("users", Arrays.stream(SystemUser.values())
                .map(SystemUser::getDisplayName)
                .collect(Collectors.toList()));
        return enums;
    }

    private void validateTaskDTO(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa é obrigatório");
        }
        if (taskDTO.getDeadline() == null) {
            throw new IllegalArgumentException("A data limite é obrigatória");
        }
        if (taskDTO.getPriority() == null) {
            throw new IllegalArgumentException("A prioridade é obrigatória");
        }
        if (taskDTO.getResponsible() == null) {
            throw new IllegalArgumentException("O responsável é obrigatório");
        }
    }

    private void updateTaskFromDTO(Task task, TaskDTO taskDTO) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus() != null ? taskDTO.getStatus() : TaskStatus.PENDENTE);
        task.setPriority(taskDTO.getPriority());
        task.setResponsible(taskDTO.getResponsible());
        task.setDeadline(taskDTO.getDeadline());
    }
} 