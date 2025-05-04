package com.board.bug.repository;

import com.board.bug.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BugRepository extends JpaRepository<Bug, UUID> {
    List<Bug> findByAssignedUserId(UUID assignedUserId);
    List<Bug> findByFeatureId(UUID featureId);
    List<Bug> findBySprintId(UUID sprintId);
    @Query("SELECT pbi FROM Bug pbi WHERE :tagId MEMBER OF pbi.tagIds")
    List<Bug> findByTagId(UUID tagId);
}
