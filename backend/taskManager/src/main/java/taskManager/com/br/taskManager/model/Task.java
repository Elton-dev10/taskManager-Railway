package taskManager.com.br.taskManager.model;

import jakarta.persistence.*;
import lombok.Data;
import taskManager.com.br.taskManager.enums.PriorityLevel;
import taskManager.com.br.taskManager.enums.SystemUser;
import taskManager.com.br.taskManager.enums.TaskStatus;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityLevel priority;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemUser responsible;
    
    @Column(nullable = false)
    private LocalDate deadline;
    
    private LocalDate createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        if (status == null) {
            status = TaskStatus.PENDENTE;
        }
    }
} 