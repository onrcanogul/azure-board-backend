package com.board.epic.service;

import com.board.epic.dto.EpicDto;
import com.board.epic.utils.NoContent;
import com.board.epic.utils.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface EpicService {
    ServiceResponse<List<EpicDto>> getByTeam(UUID teamId);
    ServiceResponse<List<EpicDto>> getByArea(UUID areaId);
    ServiceResponse<Boolean> isExist(UUID id);
    ServiceResponse<EpicDto> create(EpicDto model);
    ServiceResponse<EpicDto> update(EpicDto model);
    ServiceResponse<NoContent> delete(UUID id);
}
