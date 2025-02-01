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

        User user = userRepository.findById(request.getIdUser()).orElseThrow(() -> new RuntimeException(("User not found")));

        Task task = new Task();
        task.setUser(user);
        task.setCompleted(false);
        task.setCreatedAt(request.getCreateDate());
        task.setTitle(request.getTitle());
        task.setFinalDate(request.getFinalDate());

        return taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException(("Task not found")));
        return task;
    }

    public void deleteTask(Long taskId) {
        if(!taskRepository.existsById(taskId))
            throw new RuntimeException("Task not found");
        taskRepository.deleteById(taskId);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
