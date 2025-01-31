package com.dev2prod.manytomany.employee.service;

import com.dev2prod.manytomany.employee.dto.EmployeeDTO;
import com.dev2prod.manytomany.employee.dto.EmployeeMapper;
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
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO empObj) {
        Employee employee = EmployeeMapper.mapper.toEmployee(empObj);
        return EmployeeMapper.mapper.toEmployeeDTO(employeeRepository.save(employee));
    }

    public List<EmployeeDTO> getEmployeeDetails(Long empId) {
        List<Employee> employees;
        if (null != empId) {
            employees = employeeRepository.findAllByEmpId(empId);
        } else {
            employees = employeeRepository.findAll();
        }
        return employees.stream().map(
                EmployeeMapper.mapper::toEmployeeDTO).collect(Collectors.toList());
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

    public EmployeeDTO saveEmployeetWhitManyProjects(EmployeeDTO employeeDTO) {

        Employee employee = EmployeeMapper.mapper.toEmployee(employeeDTO);
        // Mantén los proyectos
        Set<Project> currentProjects = employee.getAssignedProjects();
        Set<Project> projects = new HashSet<>(currentProjects);
        for (Project project : employee.getAssignedProjects()) {
            projects.add(projectRepository.save(project));
        }
        employee.setAssignedProjects(projects);
        return EmployeeMapper.mapper.toEmployeeDTO(employeeRepository.save(employee));
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
