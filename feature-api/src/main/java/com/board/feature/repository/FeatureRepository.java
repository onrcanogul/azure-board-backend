package com.board.feature.repository;

import com.board.feature.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, UUID> {
    List<Feature> findByAreaId(UUID areaId);
    List<Feature> findByEpicId(UUID epicId);
    @Query("select count(f) > 0 from Feature f where f.id = :id")
    boolean isExistById(@Param("id") UUID id);
}
