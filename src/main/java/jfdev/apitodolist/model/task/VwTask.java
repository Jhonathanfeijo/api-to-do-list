package jfdev.apitodolist.model.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "vw_task")
public class VwTask {
    @Id
    private Long taskId;
    private String title;
    private String description;
    private String status;
    private LocalDate createdAt;
    private LocalDate finalDate;
    private String userName;
    private String userId;
}
