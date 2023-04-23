package com.cleanup.todoc.repositories;

import com.cleanup.todoc.database.dao.ProjectDao;

/**
 * <p>Repository for project.</p>
 *
 * @author Benjamin PALLO
 */
public class ProjectDataRepository {

    /**
     * The repository use ProjectDao to separate data persistence logic from application business logic
     */
    private final ProjectDao projectDao;

    /**
     * Constructor of Repository (Project)
     * @param projectDao
     */
    public ProjectDataRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

}
