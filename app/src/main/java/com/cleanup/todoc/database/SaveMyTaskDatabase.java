package com.cleanup.todoc.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class SaveMyTaskDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "savemytask_database";

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    private static SaveMyTaskDatabase instance;

    public static synchronized SaveMyTaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTaskDatabase.class, DATABASE_NAME)
                    .addCallback(prepopulateDatabase())
                    .build();
        }
        return instance;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadExecutor().execute(() -> instance.projectDao().createProject(
                        new Project(1L, "Projet Tartampion", 0xFFEADAD1)));
                Executors.newSingleThreadExecutor().execute(() -> instance.projectDao().createProject(
                        new Project(2L, "Projet Lucidia", 0xFFB4CDBA)));
                Executors.newSingleThreadExecutor().execute(() -> instance.projectDao().createProject(
                        new Project(3L, "Projet Circus", 0xFFA3CED2)));
            }
        };
    }
}

