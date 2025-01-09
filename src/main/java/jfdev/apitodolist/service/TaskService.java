package jfdev.apitodolist.service;

import jfdev.apitodolist.model.task.Task;
import jfdev.apitodolist.model.task.dto.TaskRegisterRequest;
import jfdev.apitodolist.model.user.User;
import jfdev.apitodolist.repository.TaskRepository;
import jfdev.apitodolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasksByUserId(Long userId) {
        return taskRepository.findAllByUserIdOrderByFinalDateDesc(userId);
    }

    public Task saveTask(TaskRegisterRequest request) {

        Optional<User> user = userRepository.findById(request.getIdUser());
        if(user == null)
            throw new RuntimeException("User not found");

        Task task = new Task();
        task.setUser(user.get());
        task.setCompleted(false);
        task.setTitle(request.getTitle());
        task.setFinalDate(request.getFinalDate());

        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task == null)
            throw new RuntimeException("Task not found");
        return task.get();
    }

    public void deleteTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if(task == null)
            throw new RuntimeException("Task not found");
    }

    public Task updateTask(Task task) {
        deleteTask(task.getId());
        return taskRepository.save(task);
    }
}
