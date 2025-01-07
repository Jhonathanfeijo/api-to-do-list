package jfdev.apitodolist.service;

import jfdev.apitodolist.model.Task;
import jfdev.apitodolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasksByUserId(int userId) {

        return taskRepository.findAllByUserId(userId);

    };

}
