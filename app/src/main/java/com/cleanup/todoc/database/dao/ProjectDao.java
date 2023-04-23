package com.cleanup.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * <p>Dao for project.</p>
 *
 * @author Benjamin PALLO
 */
@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project")
    List<Project> getAllProjects();

    @Query("SELECT * FROM Project WHERE id = :id")
    Project getProjectById(long id);

    @Query("SELECT * FROM Project WHERE id = :id")
    List<Project> getById(long id);

    @Insert
    void insert(Project project);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    @Update
    void update(Project project);

    @Delete
    void delete(Project project);

}

