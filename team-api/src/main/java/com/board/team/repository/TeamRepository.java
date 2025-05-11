package com.board.team.repository;

import com.board.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    List<Team> findByProjectId(UUID projectId);
    @Query("select count(f) > 0 from Team f where f.id = :id")
    boolean isExistById(@Param("id") UUID id);
}
