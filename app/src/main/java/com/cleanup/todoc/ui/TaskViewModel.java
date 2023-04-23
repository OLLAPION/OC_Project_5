package com.cleanup.todoc.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * <p>ViewModel for Task, without ??? complet car je ne voulais pas toucher la main Activity.</p>
 *
 * @author Benjamin PALLO
 */
public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;


    // DATA
    @Nullable
    private LiveData<Project> currentProject;
    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    // -------------
    // FOR PROJECT
    // -------------
    public LiveData<Project> getProject() { return this.currentProject;  }

    // -------------
    // FOR TASK
    // -------------
    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    public void deleteTask(Task taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    public void updateTask(Task task) {
        executor.execute(() -> taskDataSource.updateTask(task));
    }

    public void insertTask(Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }


}
