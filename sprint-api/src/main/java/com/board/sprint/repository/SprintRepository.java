package com.board.sprint.repository;

import com.board.sprint.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, UUID> {
    List<Sprint> findByTeamId(UUID teamId);
    List<Sprint> findByProjectId(UUID projectId);
    @Query("select count(f) > 0 from Sprint f where f.id = :id")
    boolean isExistById(@Param("id") UUID id);
}
