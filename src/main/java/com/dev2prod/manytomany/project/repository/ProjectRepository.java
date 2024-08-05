package com.dev2prod.manytomany.project.repository;

import com.dev2prod.manytomany.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByProjectId(long projectId);

    Optional<Project> findByProjectRef(String projectRef);
}
