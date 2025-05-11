package com.board.project.service;

import com.board.project.dto.ProjectDto;
import com.board.project.util.NoContent;
import com.board.project.util.ServiceResponse;


import java.util.List;
import java.util.UUID;

public interface ProjectService {
    ServiceResponse<List<ProjectDto>> get();
    ServiceResponse<ProjectDto> getById(UUID id);
    ServiceResponse<Boolean> isExist(UUID id);
    ServiceResponse<ProjectDto> create(ProjectDto model);
    ServiceResponse<ProjectDto> update(ProjectDto model);
    ServiceResponse<NoContent> delete(UUID id);
}
