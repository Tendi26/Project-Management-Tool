package com.ProjectManagementTool.PMTool.Services;

import com.ProjectManagementTool.PMTool.Repositories.ProjectRepository;
import com.ProjectManagementTool.PMTool.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        return projectRepository.save(project);
    }
}
