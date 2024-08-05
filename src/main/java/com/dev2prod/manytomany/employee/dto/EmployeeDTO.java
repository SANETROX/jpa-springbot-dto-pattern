package com.dev2prod.manytomany.employee.dto;

import com.dev2prod.manytomany.project.dto.ProjectDTO;

import java.util.Set;

public class EmployeeDTO {

    private String empEmail;
    private String empName;
    private Set<ProjectDTO> assignedProjects;
}
