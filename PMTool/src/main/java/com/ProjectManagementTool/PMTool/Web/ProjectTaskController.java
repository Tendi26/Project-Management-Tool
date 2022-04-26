package com.ProjectManagementTool.PMTool.Web;

import com.ProjectManagementTool.PMTool.Services.ProjectTaskService;
import com.ProjectManagementTool.PMTool.Services.ValidationService;
import com.ProjectManagementTool.PMTool.domain.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class ProjectTaskController {
    @Autowired
    private ProjectTaskService projectTaskService;
    @Autowired
    private ValidationService validationService;

    @PostMapping("/{projectIdentifier}")
    public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result, @PathVariable String projectIdentifier){
        ResponseEntity<?> errorMap = validationService.FieldErrorValidationService(result);
        if(errorMap != null) return errorMap;
        ProjectTask projectTask1 = projectTaskService.addProjectTask(projectIdentifier, projectTask);
        return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
    }
}
