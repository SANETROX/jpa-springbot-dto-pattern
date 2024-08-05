package com.dev2prod.manytomany.project.dto;

import com.dev2prod.manytomany.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);

    @Mapping(source = "projectRef", target = "projectRef")
    @Mapping(source = "projectName", target = "projectName")
    ProjectDTO toProjectDTO(Project project);

    @Mapping(source = "projectRef", target = "projectRef")
    @Mapping(source = "projectName", target = "projectName")
    Project toProject(ProjectDTO projectDTO);
}
