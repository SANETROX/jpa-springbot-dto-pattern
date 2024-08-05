package com.dev2prod.manytomany.project.entity;

import com.dev2prod.manytomany.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    @Column(unique = true, nullable = false)
    private String projectRef;
    private String projectName;

    @JsonIgnore
    @ManyToMany(mappedBy = "assignedProjects")
    private Set<Employee> employeeSet = new HashSet<>();

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectRef() {
        return projectRef;
    }

    public void setProjectRef(String projectRef) {
        this.projectRef = projectRef;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
