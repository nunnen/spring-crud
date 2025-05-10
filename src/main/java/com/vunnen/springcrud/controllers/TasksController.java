package com.vunnen.springcrud.controllers;

import com.vunnen.springcrud.dao.TaskDAO;
import com.vunnen.springcrud.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private final TaskDAO taskDAO;

    @Autowired
    public TasksController(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskDAO.readAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }
}
