package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * <p>Repository for Task.</p>
 *
 * @author Benjamin PALLO
 */
public class TaskDataRepository {

        /**
         * The repository use TaskDao to separate data persistence logic from application business logic
         */
        private final TaskDao taskDao;

        /**
         * Constructor of Repository (Project)
         * @param taskDao
         */
        public TaskDataRepository(TaskDao taskDao) { this.taskDao = taskDao; }

        // --- GET ---
        public LiveData<List<Task>> getTasks() {
                return this.taskDao.getTasks();
        }

        // --- CREATE ---
        public void createTask(Task task){ taskDao.insert(task); }

        // --- DELETE ---
        public void deleteTask(Task taskId){ taskDao.delete(taskId); }

        // --- UPDATE ---
        public void updateTask(Task task){ taskDao.update(task); }


}
