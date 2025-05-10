package com.vunnen.springcrud.controllers;

import com.vunnen.springcrud.dao.TaskDAO;
import com.vunnen.springcrud.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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
    public String getTasks(@RequestParam(name = "page", defaultValue = "0", required = false) Optional<Integer> Page,
                           @RequestParam(name = "size", defaultValue = "5", required = false) Optional<Integer> PageSize,
                           Model model) {

        List<Task> tasks;
        if (Page.isPresent() && PageSize.isPresent()) {
            log.info("Getting pageable tasks");
            tasks = taskDAO.readAll(Page.get(), PageSize.get());
        }
        else tasks = taskDAO.readAll();

        model.addAttribute("tasks", tasks);
        return "index";
    }
}
