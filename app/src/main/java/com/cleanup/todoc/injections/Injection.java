package com.cleanup.todoc.injections;

import android.content.Context;

import com.cleanup.todoc.database.CleanUpDatabase;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskRepository provideTaskDataSource(Context context) {
        CleanUpDatabase database = CleanUpDatabase.getInstance(context);
        return new TaskRepository(database.taskDao());
    }

    public static ProjectRepository provideProjectDataSource(Context context) {
        CleanUpDatabase database = CleanUpDatabase.getInstance(context);
        return new ProjectRepository(database.projectDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskRepository dataSourceItem = provideTaskDataSource(context);
        ProjectRepository dataSourceUser = provideProjectDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceItem, dataSourceUser, executor);
    }
}
