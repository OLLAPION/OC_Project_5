package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

        private final TaskDao taskDao;

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
