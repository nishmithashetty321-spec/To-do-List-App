package com.todo.To_do_App.service;

import com.todo.To_do_App.model.Task;
import com.todo.To_do_App.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskrepo;

    public List<Task> getAllTasks() {
        return taskrepo.findAll();
    }

    public void addTask(String title) {
        Task task=new Task();
        task.setTitle(title);
        taskrepo.save(task);
    }

    public ResponseEntity<Task> getTaskById(Long id) {
        Task task = taskrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    public void toggleCompleted(Long id) {
        Task task=getTaskById(id).getBody();
        task.setCompleted(!task.isCompleted());
        taskrepo.save(task);
    }

    public void updateTitle(Long id, String title) {
        Task task=getTaskById(id).getBody();
        task.setTitle(title);
        taskrepo.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id).getBody();
        taskrepo.deleteById(id);
    }
}
