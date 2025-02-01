package jfdev.apitodolist.model.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRegisterRequest {
    private String title;
    private LocalDate createDate;
    private LocalDate finalDate;
    private Long idUser;
}
