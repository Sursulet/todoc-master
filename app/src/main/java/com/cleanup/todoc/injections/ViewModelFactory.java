package com.cleanup.todoc.injections;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    // -- REPOSITORY --
    private final TaskRepository taskSource;
    private final ProjectRepository projectSource;
    private final Executor executor;

    public ViewModelFactory(TaskRepository taskSource, ProjectRepository projectSource, Executor executor) {
        this.taskSource = taskSource;
        this.projectSource = projectSource;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskSource, projectSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
