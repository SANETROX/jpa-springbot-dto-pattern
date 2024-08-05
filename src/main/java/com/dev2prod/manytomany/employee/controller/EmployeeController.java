package com.dev2prod.manytomany.employee.controller;

import com.dev2prod.manytomany.employee.entity.Employee;
import com.dev2prod.manytomany.employee.service.EmployeeService;
import com.dev2prod.manytomany.project.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee empObj) {
        Employee createdEmployee =employeeService.saveEmployee(empObj);
        return ResponseEntity.ok(createdEmployee);
    }

    @GetMapping(value = {"/getEmployees", "/{empId}"})
    public List<Employee> getEmployee(@PathVariable(required = false) Long empId) {
        return employeeService.getEmployeeDetails(empId);
    }

    @DeleteMapping("delete/{empId}")
    public ResponseEntity removeEmployee(@PathVariable Long empId){
        employeeService.deleteEmployee(empId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{empId}/project/{projectId}")
    public ResponseEntity<Employee> assignProjectToEmployee(
            @PathVariable Long empId,
            @PathVariable Long projectId
    ){
        Employee employee = employeeService.assignProjectToEmployee(empId, projectId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/saveEmployeetWhitManyProjects")
    public ResponseEntity<Employee> saveEmployeetWhitManyProjects(@RequestBody Employee empObj) {
          Set<Project> projects = empObj.getAssignedProjects();
          System.out.println(projects);
        Employee createdEmployee = employeeService.saveEmployeetWhitManyProjects(empObj);
        return ResponseEntity.ok(createdEmployee);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> updateEmployeeComplete(@PathVariable Long empId, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployeeComplete(empId, employee);
        return ResponseEntity.ok(updatedEmployee);
    }
}
