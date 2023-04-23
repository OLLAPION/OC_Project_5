package com.cleanup.todoc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.cleanup.todoc.database.SaveMyTaskDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
@RunWith(AndroidJUnit4ClassRunner.class)
public class TaskDatabaseUnitTest {

    private SaveMyTaskDatabase myDatabase;
    private TaskDao taskDao;
    private ProjectDao projectDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        myDatabase = Room.inMemoryDatabaseBuilder(context, SaveMyTaskDatabase.class).build();
        taskDao = myDatabase.taskDao();
        projectDao = myDatabase.projectDao();
    }

    @After
    public void closeDb() {
        myDatabase.close();
    }


    @Test
    public void testDeleteProjectBdd() {
        Project projectTest = new Project(5, "Projet : VARR II",0xDF6D14);
        projectDao.insert(projectTest);
        projectDao.delete(projectTest);
        assertNull(projectDao.getProjectById(projectTest.getId()));
    }

    @Test
    public void testAddProjectBdd() {
        Project projectToAdd = new Project(4, "Projet : KROSS", 0x00561B);
        projectDao.insert(projectToAdd);

        List<Project> projectList = projectDao.getAllProjects();

        boolean projectFound = false;
        for (Project project : projectList) {
            if (project.getId() == projectToAdd.getId() &&
                    project.getName().equals(projectToAdd.getName()) &&
                    project.getColor() == projectToAdd.getColor()) {
                projectFound = true;
                break;
            }
        }
        assertTrue(projectFound);
        projectDao.delete(projectToAdd);
    }

    @Test
    public void testAddTaskBdd() {
        Project projectFake = new Project(5L, "Projet : Mash", 0xFFFFFF);
        projectDao.insert(projectFake);

        Task taskToAdd = new Task(5L, "Tâche 1", 0);
        taskDao.insert(taskToAdd);

        List<Task> taskList = taskDao.getTasksList();

        boolean taskFound = false;
        for (Task task : taskList) {
            if (task.getProjectId() == taskToAdd.getProjectId() &&
                    task.getName().equals(taskToAdd.getName()) &&
                    task.getCreationTimestamp() == taskToAdd.getCreationTimestamp()) {
                taskFound = true;
                break;
            }
        }
        assertTrue(taskFound);
        taskDao.delete(taskToAdd);
        projectDao.delete(projectFake);
    }

    @Test
    public void testDeleteTaskBdd() {
        Project project = new Project(5L, "Projet : Krimho", 0xFFFFFF);
        projectDao.insert(project);

        Task taskToAdd = new Task(5L, "Tâche 1", 0);
        taskDao.insert(taskToAdd);

        // Afficher les tâches avant la suppression
        List<Task> taskListBeforeDelete = taskDao.getTasksList();
        for (Task task : taskListBeforeDelete) {
            Log.d("TAG", "task before delete: " + task.toString());
        }

        taskDao.delete(taskToAdd);

        // Ajouter un log pour vérifier si la suppression a été effectuée correctement
        Log.d("TAG", "task deleted successfully: " + taskToAdd.toString());

        // Afficher les tâches après la suppression
        List<Task> taskListAfterDelete = taskDao.getTasksList();
        for (Task task : taskListAfterDelete) {
            Log.d("TAG", "task after delete: " + task.toString());
        }

        boolean taskFound = false;
        for (Task task : taskListAfterDelete) {
            if (task.getProjectId() == taskToAdd.getProjectId() &&
                    task.getName().equals(taskToAdd.getName())) {
                taskFound = true;
                break;
            }
        }

        // Ajouter un log juste avant l'assertion pour vérifier si la tâche a été supprimée avec succès ou non
        Log.d("TAG", "task found after delete: " + taskFound);

        assertFalse(taskFound);

        projectDao.delete(project);
    }

}
