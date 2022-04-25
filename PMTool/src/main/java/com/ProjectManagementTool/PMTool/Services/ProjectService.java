package com.ProjectManagementTool.PMTool.Services;

import com.ProjectManagementTool.PMTool.Repositories.BacklogRepository;
import com.ProjectManagementTool.PMTool.Repositories.ProjectRepository;
import com.ProjectManagementTool.PMTool.domain.Backlog;
import com.ProjectManagementTool.PMTool.domain.Project;
import com.ProjectManagementTool.PMTool.exceptions.UniqueKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;


@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            if(project.getId()==null){
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }else{
                Backlog backlog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase());
                project.setBacklog(backlog);
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new UniqueKeyException("Project id " + project.getProjectIdentifier() + " already exist");
        }
    }
    public Project findByIdentifier(String identifier){
        Project project = projectRepository.findByProjectIdentifier(identifier.toUpperCase());
        if(project == null){
            throw new UniqueKeyException("Project Id "+ identifier + " does not exist");
        }
        return project;
    }
    public Iterable<Project> findAllProject(){
        return projectRepository.findAll();
    }
    public Project deleteByProjectIdentifier(String identifier){
        Project project = projectRepository.findByProjectIdentifier(identifier);
        if(project == null){
            throw new UniqueKeyException("Project Id "+ identifier + " does not exist");
        }
        projectRepository.delete(project);
        return project;
    }
}
