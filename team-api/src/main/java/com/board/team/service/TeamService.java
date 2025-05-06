package com.board.team.service;

import com.board.team.dto.TeamDto;
import com.board.team.util.NoContent;
import com.board.team.util.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    ServiceResponse<List<TeamDto>> getAll();
    ServiceResponse<List<TeamDto>> getByProject(UUID projectId);
    ServiceResponse<TeamDto> create(TeamDto model);
    ServiceResponse<TeamDto> update(TeamDto model);
    ServiceResponse<NoContent> delete(UUID id);
}
