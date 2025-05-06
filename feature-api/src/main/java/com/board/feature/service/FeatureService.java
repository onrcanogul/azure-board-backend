package com.board.feature.service;

import com.board.feature.dto.FeatureDto;
import com.board.feature.utils.NoContent;
import com.board.feature.utils.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface FeatureService {
    ServiceResponse<FeatureDto> getById(UUID id);
    ServiceResponse<List<FeatureDto>> getByArea(UUID areaId);
    ServiceResponse<List<FeatureDto>> getByEpic(UUID epicId);
    ServiceResponse<FeatureDto> create(FeatureDto model);
    ServiceResponse<FeatureDto> update(FeatureDto model);
    ServiceResponse<NoContent> complete(UUID featureId) throws JsonProcessingException;
    ServiceResponse<NoContent> delete(UUID id);
}
