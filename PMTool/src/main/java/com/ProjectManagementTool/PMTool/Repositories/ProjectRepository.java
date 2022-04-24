package com.ProjectManagementTool.PMTool.Repositories;

import com.ProjectManagementTool.PMTool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
}
