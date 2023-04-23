package com.cleanup.todoc.injections;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.SaveMyTaskDatabase;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <p>Factory use Database and Repository (Task and Project) for injection.</p>
 *
 * @author Benjamin PALLO
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    /**
     * Use the repository (Task) for DI
     */
    private final TaskDataRepository taskDataSource;

    /**
     * Use the repository (Project) for DI
     */
    private final ProjectDataRepository projectDataSource;

    /**
    * Creates a single-threaded pool to run background tasks.
    */
    private final Executor executor;

    /**
     * Factory is for injection of class ViewModelFactory
     */
    private static volatile ViewModelFactory factory;

    /**
     * Creates a single instance of the ViewModelFactory class and returns it.
     * This method is thread safe.
     *
     * @param context the application context to be used to create the database instance
     * @return a ViewModelFactory instance
     */
    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    /**
     * Constructs a ViewModelFactory instance with the given context.
     * It creates the database instance, the TaskDataRepository and the ProjectDataRepository.
     * It also creates an Executor instance to run background tasks.
     *
     * @param context the application context to be used to create the database instance
     */
    private ViewModelFactory(Context context) {
        SaveMyTaskDatabase database = SaveMyTaskDatabase.getInstance(context);
        this.taskDataSource = new TaskDataRepository(database.taskDao());
        this.projectDataSource = new ProjectDataRepository(database.projectDao());
        this.executor = Executors.newSingleThreadExecutor();
    }

    /**
     * Creates a ViewModel instance of the given model class with its dependencies.
     *
     * @param modelClass the model class of the ViewModel to be created
     * @param <T> the type of the ViewModel to be created
     * @return a ViewModel instance of the given model class with its dependencies
     */
    @Override
    @NotNull
    public <T extends ViewModel>  T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
