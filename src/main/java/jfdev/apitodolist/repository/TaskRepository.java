package jfdev.apitodolist.repository;

import jfdev.apitodolist.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(int userId);

    List<Task> findAllByUserIdOrderByFinalDateDesc(Long userId);
}
