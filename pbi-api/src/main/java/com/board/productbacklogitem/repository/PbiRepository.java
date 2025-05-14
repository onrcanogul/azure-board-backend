package com.board.productbacklogitem.repository;

import com.board.productbacklogitem.entity.ProductBacklogItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PbiRepository extends JpaRepository<ProductBacklogItem, UUID> {
    List<ProductBacklogItem> findByFeatureId(UUID featureId);
    List<ProductBacklogItem> findByAssignedUserId(UUID assignedUserId);
    List<ProductBacklogItem> findBySprintId(UUID sprintId);
    @Query("SELECT pbi FROM ProductBacklogItem pbi WHERE :tagId MEMBER OF pbi.tagIds")
    List<ProductBacklogItem> findByTagId(UUID tagId);
}
