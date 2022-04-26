package com.ProjectManagementTool.PMTool.Services;

import com.ProjectManagementTool.PMTool.Repositories.BacklogRepository;
import com.ProjectManagementTool.PMTool.Repositories.ProjectRepository;
import com.ProjectManagementTool.PMTool.Repositories.ProjectTaskRepository;
import com.ProjectManagementTool.PMTool.domain.Backlog;
import com.ProjectManagementTool.PMTool.domain.Project;
import com.ProjectManagementTool.PMTool.domain.ProjectTask;
import com.ProjectManagementTool.PMTool.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try{
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);
            projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);
            if(projectTask.getPriority()==null){
                projectTask.setPriority(3);
            }
            if(projectTask.getStatus()==null){
                projectTask.setStatus("To_Do");
            }
            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project '"+projectIdentifier+"' projectIdentifier does not exist");
        }
    }

    public List<ProjectTask> findProjectTaskByProjectIdentifier(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
        if(project==null){
            throw new ProjectNotFoundException("Project with '"+projectIdentifier+"'  projectIdentifier does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);

    }
}
