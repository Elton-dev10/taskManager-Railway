package taskManager.com.br.taskManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import taskManager.com.br.taskManager.model.Task;
import taskManager.com.br.taskManager.enums.PriorityLevel;
import taskManager.com.br.taskManager.enums.SystemUser;
import taskManager.com.br.taskManager.enums.TaskStatus;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    @Query("SELECT t FROM Task t WHERE " +
           "(:status IS NULL OR t.status = :status) AND " +
           "(:priority IS NULL OR t.priority = :priority) AND " +
           "(:responsible IS NULL OR t.responsible = :responsible)")
    List<Task> findTasksByFilters(
        @Param("status") TaskStatus status,
        @Param("priority") PriorityLevel priority,
        @Param("responsible") SystemUser responsible
    );
} 