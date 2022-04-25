package com.ProjectManagementTool.PMTool.exceptions;

public class UniqueKeyExceptionResponse {
    private String projectIdentifier;

    public UniqueKeyExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
