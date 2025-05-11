package com.vunnen.springcrud.dao;

import com.vunnen.springcrud.model.Task;
import com.vunnen.springcrud.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@Transactional(readOnly = true)
public class TaskDAO {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskDAO(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public void create(Task task) {
        log.info("Creating task: {}", task.getDescription());
        taskRepository.save(task);
    }

    public Task read(int id) {
        log.info("Reading task-{}", id);
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public List<Task> readAll() {
        log.info("Reading all tasks");
        return taskRepository.findAll();
    }

    public List<Task> readAll(int pageNumber, int pageSize) {
        log.info("Reading all tasks with pageable");
        return taskRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Transactional
    public void update(int id, Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            log.info("Updating task-{}", id);
            Task task = optionalTask.get();
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            taskRepository.save(task);
        }
        log.warn("No task found with id {}", id);
    }

    @Transactional
    public void delete(int id) {
        log.info("Deleting task-{}", id);
        taskRepository.deleteById(id);
    }

    public int getNumOfTasks() {
        log.info("Getting number of tasks");
        return taskRepository.findAll().size();
    }
}
