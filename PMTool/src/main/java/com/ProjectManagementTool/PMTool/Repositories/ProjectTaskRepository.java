package com.ProjectManagementTool.PMTool.Repositories;

import com.ProjectManagementTool.PMTool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
