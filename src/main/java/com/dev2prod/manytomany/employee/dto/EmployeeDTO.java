package com.dev2prod.manytomany.employee.dto;

import com.dev2prod.manytomany.project.dto.ProjectDTO;

import java.util.Set;

public class EmployeeDTO {

    private Long empId;
    private String empEmail;
    private String empName;
    private Set<ProjectDTO> assignedProjects;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Set<ProjectDTO> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(Set<ProjectDTO> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }
}
