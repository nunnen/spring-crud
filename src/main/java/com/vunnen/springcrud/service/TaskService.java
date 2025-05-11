package com.vunnen.springcrud.service;

import com.vunnen.springcrud.dao.TaskDAO;
import com.vunnen.springcrud.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void create(Task task) {
        taskDAO.create(task);
    }

    public Task read(int id) {
        return taskDAO.read(id);
    }

    public List<Task> readAll(int pageNumber, int pageSize) {
        return taskDAO.readAll(pageNumber, pageSize);
    }

    public void update(int id, Task updatedTask) {
        taskDAO.update(id, updatedTask);
    }

    public void delete(int id) {
        taskDAO.delete(id);
    }

    public int getTotalTasks() {
        return taskDAO.getNumOfTasks();
    }
}
