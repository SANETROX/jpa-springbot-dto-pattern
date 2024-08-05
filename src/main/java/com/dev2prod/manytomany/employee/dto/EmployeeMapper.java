package com.dev2prod.manytomany.employee.dto;

import com.dev2prod.manytomany.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "empEmail", target = "empEmail")
    @Mapping(source = "empName", target = "empName")
    @Mapping(source = "assignedProjects", target = "assignedProjects")
    EmployeeDTO toEmployeeDTO(Employee employee);

    @Mapping(source = "empEmail", target = "empEmail")
    @Mapping(source = "empName", target = "empName")
    @Mapping(source = "assignedProjects", target = "assignedProjects")
    Employee toEmployee(EmployeeDTO employeeDTO);
}
