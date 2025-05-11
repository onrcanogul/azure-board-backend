package com.board.project.repository;

import com.board.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    @Query("select count(f) > 0 from Project f where f.id = :id")
    boolean isExistById(@Param("id") UUID id);
}
