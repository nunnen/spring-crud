package com.vunnen.springcrud.controllers;

import com.vunnen.springcrud.model.Task;
import com.vunnen.springcrud.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", service.getPageNumbers(pageSize));

        return "index";
    }

    @PostMapping
    public String addNewTask(@ModelAttribute("task") Task task) {
        service.create(task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/tasks";
    }

    @PatchMapping(value = "/{id}", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskJson(@RequestBody Task task, @PathVariable("id") int id) {
        service.update(id, task);
    }


}
