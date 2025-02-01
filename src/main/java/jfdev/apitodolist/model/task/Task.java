package jfdev.apitodolist.model.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jfdev.apitodolist.model.comment.Comment;
import jfdev.apitodolist.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate createdAt;
    private LocalDate finalDate;
    private boolean completed;
//    @OneToMany(mappedBy = "task")
//    private ArrayList<Comment> comment;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
