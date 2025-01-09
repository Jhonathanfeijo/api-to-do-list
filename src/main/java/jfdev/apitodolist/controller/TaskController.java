package jfdev.apitodolist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jfdev.apitodolist.model.task.Task;
import jfdev.apitodolist.model.task.dto.TaskRegisterRequest;
import jfdev.apitodolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/byUser")
    public ResponseEntity getAllTasksByUserId(HttpServletRequest http) {
        Long userId = Long.parseLong(http.getParameter("userId"));
        List<Task> tasks = taskService.getAllTasksByUserId(userId);
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    @PostMapping()
    public ResponseEntity saveTask(@RequestBody TaskRegisterRequest request, UriComponentsBuilder uriBuilder) {
        System.out.println(request.getIdUser());
        Task task = taskService.saveTask(request);
        URI uri = uriBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskById(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateTaskById(@RequestBody Task request){
        Task task = taskService.updateTask(request);
        return ResponseEntity.ok(task);
    }
}
