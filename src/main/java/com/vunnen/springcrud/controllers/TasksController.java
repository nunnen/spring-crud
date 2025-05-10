package com.vunnen.springcrud.controllers;

import com.vunnen.springcrud.dao.TaskDAO;
import com.vunnen.springcrud.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@Slf4j
public class TasksController {
    private final TaskDAO taskDAO;

    @Autowired
    public TasksController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping
    public String getTasks(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                           @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
                           Model model,
                           @ModelAttribute("task") Task task) {

        List<Task> tasks = taskDAO.readAll(page, pageSize);
        model.addAttribute("tasks", tasks);

        return "index";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable(name = "id") int id, Model model) {
        Task task = taskDAO.read(id);
        model.addAttribute("task", task);
        return "task";
    }

    @PostMapping
    public String addNewTask(@ModelAttribute("task") Task task) {
        taskDAO.create(task);
        return "redirect:/tasks?pageSize=50";
    }

    @GetMapping("/{id}/edit")
    public String updateTaskView(Model model,
                                 @PathVariable(name = "id") int id) {
        Task task = taskDAO.read(id);
        model.addAttribute("task", task);
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateTask(@ModelAttribute("task") Task task,
                             @PathVariable(name = "id") int id) {
        taskDAO.update(id, task);
        return "redirect:/tasks/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        taskDAO.delete(id);
        return "redirect:/tasks";
    }
}
