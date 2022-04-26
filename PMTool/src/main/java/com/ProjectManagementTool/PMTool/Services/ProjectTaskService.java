package com.ProjectManagementTool.PMTool.Services;

import com.ProjectManagementTool.PMTool.Repositories.BacklogRepository;
import com.ProjectManagementTool.PMTool.Repositories.ProjectTaskRepository;
import com.ProjectManagementTool.PMTool.domain.Backlog;
import com.ProjectManagementTool.PMTool.domain.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
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
    }
}
