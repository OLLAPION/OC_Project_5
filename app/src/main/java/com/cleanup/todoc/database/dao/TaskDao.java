package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * <p>Dao for Task.</p>
 *
 * @author Benjamin PALLO
 */
@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM Task")
    List<Task> getTasksList();

    @Query("SELECT * FROM task WHERE projectId = :projectId")
    List<Task> getByProjectId(long projectId);

    @Query("SELECT * FROM task WHERE id = :id")
    List<Task> getById(long id);

    @Query("SELECT * FROM task WHERE id = :id")
    Task getTaskById(long id);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

}
