package jfdev.apitodolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDate finalDate;
    private boolean completed;
    @OneToMany(mappedBy = "task")
    private ArrayList<Comment> comment;
    @ManyToOne
    @JoinColumn
    private User user;
}
