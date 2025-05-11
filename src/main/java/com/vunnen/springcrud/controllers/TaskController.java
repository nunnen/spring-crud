package com.vunnen.springcrud.controllers;

import com.vunnen.springcrud.model.Task;
import com.vunnen.springcrud.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@Slf4j
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String getTasks(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                           @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize,
                           Model model,
                           @ModelAttribute("task") Task task) {

        List<Task> tasks = service.readAll(page, pageSize);
        model.addAttribute("tasks", tasks);
        log.info("page nums = {}", service.getPageNumbers(pageSize));

        return "index";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable(name = "id") int id, Model model) {
        Task task = service.read(id);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping
    public String addNewTask(@ModelAttribute("task") Task task) {
        service.create(task);
        return "redirect:/tasks?pageSize=50";
    }

    @GetMapping("/{id}/edit")
    public String updateTaskView(Model model,
                                 @PathVariable(name = "id") int id) {
        Task task = service.read(id);
        model.addAttribute("task", task);
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateTask(@ModelAttribute("task") Task task,
                             @PathVariable(name = "id") int id) {
        service.update(id, task);
        return "redirect:/tasks/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/tasks";
    }
}
