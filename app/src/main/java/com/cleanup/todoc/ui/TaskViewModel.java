package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // -- REPOSITORY --
    private final TaskRepository taskSource;
    private final ProjectRepository projectSource;
    private final Executor executor;

    //-- DATA --
    @Nullable
    private LiveData<List<Project>> projects;

    public TaskViewModel(TaskRepository taskSource, ProjectRepository projectSource, Executor executor) {
        this.taskSource = taskSource;
        this.projectSource = projectSource;
        this.executor = executor;
    }

    public void init() {
        if(this.projects != null) { return; }
        this.projects = projectSource.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() { return this.projects; }

    public LiveData<List<Task>> getAllTasks() { return taskSource.getAllTasks(); }
    public void insert(Task task) { executor.execute(() -> { taskSource.insert(task); }); }
    public void delete(Task task) { executor.execute(() -> { taskSource.delete(task); }); }
}
