package com.board.epic.repository;

import com.board.epic.entity.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EpicRepository extends JpaRepository<Epic, UUID> {
    List<Epic> findByTeamId(UUID teamId);
    List<Epic> findByAreaId(UUID areaId);
    @Query("select count(f) > 0 from Epic f where f.id = :id")
    boolean isExistById(@Param("id") UUID id);
}
