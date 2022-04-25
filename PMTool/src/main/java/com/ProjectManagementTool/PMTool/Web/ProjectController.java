package com.ProjectManagementTool.PMTool.Web;

import com.ProjectManagementTool.PMTool.Services.ProjectService;
import com.ProjectManagementTool.PMTool.Services.ValidationService;
import com.ProjectManagementTool.PMTool.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ValidationService validationService;
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> errorMap = validationService.FieldErrorValidationService(result);
        if(errorMap != null) return errorMap;
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project1, HttpStatus.CREATED);
    }
    @GetMapping("/{identifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String identifier){
        Project project = projectService.findByIdentifier(identifier);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }
    @GetMapping("")
    public Iterable<Project> getAllProject(){
        return projectService.findAllProject();
    }
    @DeleteMapping("/{identifier}")
    public Project deleteProject(@PathVariable String identifier){
        Project project = projectService.deleteByProjectIdentifier(identifier);
        return project;
    }
}
