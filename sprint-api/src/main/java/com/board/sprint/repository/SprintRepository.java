package com.board.sprint.repository;

import com.board.sprint.entity.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SprintRepository extends JpaRepository<Sprint, UUID> {
    List<Sprint> findByTeamId(UUID teamId);
    List<Sprint> findByProjectId(UUID projectId);
}
