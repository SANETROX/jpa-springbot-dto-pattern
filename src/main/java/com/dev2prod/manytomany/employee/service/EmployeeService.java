package com.dev2prod.manytomany.employee.service;

import com.dev2prod.manytomany.employee.entity.Employee;
import com.dev2prod.manytomany.employee.repository.EmployeeRepository;
import com.dev2prod.manytomany.project.entity.Project;
import com.dev2prod.manytomany.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Employee saveEmployee(Employee empObj) {
        return employeeRepository.save(empObj);
    }

    public List<Employee> getEmployeeDetails(Long empId) {
        if (null != empId) {
            List<Employee> employees = employeeRepository.findAllByEmpId(empId); //
            return employees;
        } else {
            return employeeRepository.findAll();
        }
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    public Employee assignProjectToEmployee(Long empId, Long projectId) {
        Employee employee = employeeRepository.findById(empId).get();
        Project project = projectRepository.findById(projectId).get();
        Set<Project> projectSet = employee.getAssignedProjects();
        projectSet.add(project);
        employee.setAssignedProjects(projectSet);
        return employeeRepository.save(employee);
    }

    public Employee saveEmployeetWhitManyProjects(Employee employee) {
        // Mantén los proyectos
        Set<Project> currentProjects = employee.getAssignedProjects();
        Set<Project> projects = new HashSet<>(currentProjects);
        for (Project project : employee.getAssignedProjects()) {
            projects.add(projectRepository.save(project));
        }
        employee.setAssignedProjects(projects);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeComplete(Long empId, Employee employee) {
        Optional<Employee> emp = employeeRepository.findById(empId);
        if (emp.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        Employee existingEmployee = emp.get();
        existingEmployee.setEmpName(employee.getEmpName());
        existingEmployee.setEmpEmail(employee.getEmpEmail());

        // Mantén los proyectos existentes
        Set<Project> currentProjects = existingEmployee.getAssignedProjects();
        Set<Project> projects = new HashSet<>(currentProjects);
        for (Project project : employee.getAssignedProjects()) {
            Optional<Project> proj = projectRepository.findByProjectRef(project.getProjectRef());

            if (proj.isEmpty()) {
                Project savedProject = projectRepository.save(project);
                projects.add(savedProject);
            } else {
                Project existingProject = proj.get();
                existingProject.setProjectName(project.getProjectName());
                projects.add(existingProject);
            }

        }
        existingEmployee.setAssignedProjects(projects);
        return employeeRepository.save(existingEmployee);
    }
}
