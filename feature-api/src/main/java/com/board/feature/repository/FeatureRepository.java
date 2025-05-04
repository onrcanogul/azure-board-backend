package com.board.feature.repository;

import com.board.feature.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {
    List<Feature> findByAreaId(UUID areaId);
    List<Feature> findByEpicId(UUID epicId);
}
