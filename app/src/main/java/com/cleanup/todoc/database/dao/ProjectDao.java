package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project")
    List<Project> getAllProjects();

    @Query("SELECT * FROM Project WHERE id = :id")
    Project getProjectById(long id);

    @Insert
    void insert(Project project);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProject(Project project);

    @Update
    void update(Project project);

    @Delete
    void delete(Project project);

}

