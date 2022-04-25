package com.ProjectManagementTool.PMTool.Services;

import com.ProjectManagementTool.PMTool.Repositories.ProjectRepository;
import com.ProjectManagementTool.PMTool.domain.Project;
import com.ProjectManagementTool.PMTool.exceptions.UniqueKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new UniqueKeyException("Project id " + project.getProjectIdentifier() + " already exist");
        }
    }
}
