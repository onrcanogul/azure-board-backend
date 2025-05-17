package com.board.epic.service.impl;

import com.board.epic.client.TeamClient;
import com.board.epic.configuration.mapper.Mapper;
import com.board.epic.dto.EpicDto;
import com.board.epic.entity.Epic;
import com.board.epic.repository.EpicRepository;
import com.board.epic.service.EpicService;
import com.board.epic.utils.NoContent;
import com.board.epic.utils.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EpicServiceImpl implements EpicService {
    private final EpicRepository repository;
    private final Mapper<Epic, EpicDto> mapper;
    private final TeamClient teamClient;

    public EpicServiceImpl(EpicRepository repository, Mapper<Epic, EpicDto> mapper, @Qualifier("com.board.epic.client.TeamClient") TeamClient teamClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.teamClient = teamClient;
    }

    @Override
    public ServiceResponse<List<EpicDto>> getByTeam(UUID teamId) {
        return ServiceResponse
                .success(repository.findByTeamId(teamId).stream().map(mapper::toDto).toList(), 200);
    }

    @Override
    public ServiceResponse<List<EpicDto>> getByArea(UUID areaId) {
        return ServiceResponse
                .success(repository.findByAreaId(areaId).stream().map(mapper::toDto).toList(), 200);
    }

    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.success(repository.isExistById(id), 200);
    }

    @Override
    public ServiceResponse<EpicDto> create(EpicDto model) {
        //validations(model);
        Epic epic = mapper.toEntity(model);
        Epic createdEpic = repository.save(epic);
        return ServiceResponse.success(mapper.toDto(createdEpic), 201);
    }

    @Override
    public ServiceResponse<EpicDto> update(EpicDto model) {
        //validations(model);
        Epic epic = repository.findById(model.getId()).orElseThrow();
        epic.setPriority(model.getPriority());
        epic.setTitle(model.getTitle());
        epic.setDescription(model.getDescription());
        Epic updatedEpic = repository.save(epic);
        return ServiceResponse.success(mapper.toDto(updatedEpic), 200);
    }

    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        Epic epic = repository.findById(id).orElseThrow();
        epic.setDeleted(true);
        repository.save(epic);
        return ServiceResponse.success(204);
    }

    private void validations(EpicDto model) {
        ServiceResponse<Boolean> isTeamExist = teamClient.isExist(model.getTeamId());
        if(!isTeamExist.getData()) {
            if(isTeamExist.isSuccessful()) {
                throw new EntityNotFoundException("Team does not exist");
            }
            throw new EntityNotFoundException("Team service is not available");
        }
    }
}
