package taskManager.com.br.taskManager.dto;

import lombok.Data;
import taskManager.com.br.taskManager.enums.PriorityLevel;
import taskManager.com.br.taskManager.enums.SystemUser;
import taskManager.com.br.taskManager.enums.TaskStatus;
import java.time.LocalDate;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private PriorityLevel priority;
    private SystemUser responsible;
    private LocalDate deadline;
} 