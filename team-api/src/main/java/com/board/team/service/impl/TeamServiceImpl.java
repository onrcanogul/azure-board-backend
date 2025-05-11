package com.board.team.service.impl;

import com.board.team.client.ProjectClient;
import com.board.team.configuration.mapper.Mapper;
import com.board.team.dto.TeamDto;
import com.board.team.entity.Team;
import com.board.team.repository.TeamRepository;
import com.board.team.service.TeamService;
import com.board.team.util.NoContent;
import com.board.team.util.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final Mapper<Team, TeamDto> mapper;
    private final ProjectClient projectClient;

    public TeamServiceImpl(TeamRepository repository, Mapper<Team, TeamDto> mapper, @Qualifier("com.board.team.client.ProjectClient") ProjectClient projectClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.projectClient = projectClient;
    }

    @Override
    public ServiceResponse<List<TeamDto>> getAll() {
        return ServiceResponse
                .success(repository.findAll().stream().map(mapper::toDto).toList()
                        , 200);
    }

    @Override
    public ServiceResponse<List<TeamDto>> getByProject(UUID projectId) {
        return ServiceResponse
                .success(repository.findByProjectId(projectId).stream().map(mapper::toDto).toList(),
                        200);
    }

    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.success(repository.isExistById(id), 200);
    }

    @Override
    public ServiceResponse<TeamDto> create(TeamDto model) {
        validations(model);
        Team team = mapper.toEntity(model);
        team.setId(null);
        Team createdTeam = repository.save(team);
        return ServiceResponse.success(mapper.toDto(team), 200);
    }

    @Override
    public ServiceResponse<TeamDto> update(TeamDto model) {
        validations(model);
        Team team = repository.findById(model.getId()).orElseThrow();
        team.setName(model.getName());
        team.setDescription(model.getDescription());
        Team updatedTeam = repository.save(team);
        return ServiceResponse.success(mapper.toDto(updatedTeam), 200);
    }

    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        Team team = repository.findById(id).orElseThrow();
        team.setDeleted(true);
        repository.save(team);
        return ServiceResponse.success(204);
    }

    private void validations(TeamDto model) {
        ServiceResponse<Boolean> response = projectClient.isExist(model.getProjectId());
        if(!response.isSuccessful()) {
            throw new EntityNotFoundException("Project not found");
        }
    }
}
