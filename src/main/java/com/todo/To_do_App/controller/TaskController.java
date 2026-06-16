package com.todo.To_do_App.controller;

import org.springframework.ui.Model;
import com.todo.To_do_App.model.Task;
import com.todo.To_do_App.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("tasks")
@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllTasks(Model model)
    {
        List<Task> tasks= taskService.getAllTasks();
        model.addAttribute("tasks",tasks);
        return "/tasks";
    }

    @PostMapping
    public String addTask(@RequestParam String title)
    {
        taskService.addTask(title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleCompleted(@PathVariable Long id)
    {
        taskService.toggleCompleted(id);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/update")
    public String updateTitle(@PathVariable Long id,@RequestParam String title)
    {
        taskService.updateTitle(id,title);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
