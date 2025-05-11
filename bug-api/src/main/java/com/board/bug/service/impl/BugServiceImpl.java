package com.board.bug.service.impl;

import com.board.bug.client.FeatureClient;
import com.board.bug.client.SprintClient;
import com.board.bug.configuration.mapper.Mapper;
import com.board.bug.dto.BugDto;
import com.board.bug.entity.Bug;
import com.board.bug.repository.BugRepository;
import com.board.bug.service.BugService;
import com.board.bug.utils.NoContent;
import com.board.bug.utils.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class BugServiceImpl implements BugService {
    private final BugRepository repository;
    private final Mapper<Bug, BugDto> mapper;
    private final FeatureClient featureClient;
    private final SprintClient sprintClient;

    public BugServiceImpl(BugRepository repository, Mapper<Bug, BugDto> mapper, @Qualifier("com.board.bug.client.FeatureClient") FeatureClient featureClient, SprintClient sprintClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.featureClient = featureClient;
        this.sprintClient = sprintClient;
    }

    /**
     * Retrieves all bugs in the system.
     *
     * @return ServiceResponse containing a list of BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<List<BugDto>> get() {
        return ServiceResponse
                .success(repository.findAll().stream().map(mapper::toDto).toList(), 200);
    }

    /**
     * Retrieves a bug by its ID.
     *
     * @param id the unique identifier of the bug
     * @return ServiceResponse containing the BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<BugDto> getById(UUID id) {
        return ServiceResponse
                .success(mapper.toDto(repository.findById(id).orElseThrow()), 200);
    }

    /**
     * Retrieves all bugs assigned to a specific user.
     *
     * @param userId the unique identifier of the user
     * @return ServiceResponse with list of BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<List<BugDto>> getByUser(UUID userId) {
        return ServiceResponse
                .success(repository.findByAssignedUserId(userId).stream().map(mapper::toDto).toList(), 200);
    }

    /**
     * Retrieves all bugs that have a specific tag.
     *
     * @param tagId the unique identifier of the tag
     * @return ServiceResponse with list of BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<List<BugDto>> getByTag(UUID tagId) {
        return ServiceResponse
                .success(repository.findByTagId(tagId).stream().map(mapper::toDto).toList(), 200);
    }

    /**
     * Retrieves all bugs associated with a feature.
     *
     * @param featureId the unique identifier of the feature
     * @return ServiceResponse with list of BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<List<BugDto>> getByFeature(UUID featureId) {
        return ServiceResponse
                .success(repository.findByFeatureId(featureId).stream().map(mapper::toDto).toList(), 200);
    }

    /**
     * Creates a new bug.
     *
     * @param model the BugDto containing information about the new bug
     * @return ServiceResponse with created BugDto and HTTP status code 201
     */
    @Override
    public ServiceResponse<BugDto> create(BugDto model) {
        validations(model);
        Bug bug = mapper.toEntity(model);
        Bug createdBug = repository.save(bug);
        return ServiceResponse.success(mapper.toDto(createdBug), 201);
    }

    /**
     * Updates an existing bug.
     *
     * @param model the BugDto containing updated data
     * @return ServiceResponse with updated BugDto and HTTP status code 200
     */
    @Override
    public ServiceResponse<BugDto> update(BugDto model) {
        validations(model);
        Bug bug = repository.findById(model.getId()).orElseThrow();
        updateMembers(bug, model);
        Bug updatedBug = repository.save(bug);
        return ServiceResponse.success(mapper.toDto(updatedBug), 200);
    }

    /**
     * Soft-deletes a bug by marking it as deleted.
     *
     * @param id the unique identifier of the bug to be deleted
     * @return ServiceResponse with HTTP status code 204 (No Content)
     */
    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        Bug bug = repository.findById(id).orElseThrow();
        bug.setDeleted(true);
        repository.save(bug);
        return ServiceResponse.success(204);
    }

    /**
     * Updates all relevant fields of a bug entity with data from the given DTO.
     *
     * @param entity the existing bug entity to update
     * @param model the DTO containing updated values
     */
    private void updateMembers(Bug entity, BugDto model) {
        entity.setSprintId(model.getSprintId());
        entity.setAreaId(model.getAreaId());
        entity.setFeatureId(model.getFeatureId());
        entity.setAssignedUserId(model.getAssignedUserId());
        entity.setDescription(model.getDescription());
        entity.setFunctionalDescription(model.getFunctionalDescription());
        entity.setTechnicalDescription(model.getTechnicalDescription());
        entity.setPriority(model.getPriority());
        entity.setStatus(model.getStatus());
        entity.setStoryPoint(model.getStoryPoint());
        entity.setBusinessValue(model.getBusinessValue());
        entity.setDueDate(model.getDueDate());
        entity.setStartedDate(model.getStartedDate());
        entity.setCompletedDate(model.getCompletedDate());
        entity.setTagIds(new HashSet<>(model.getTagIds()));
    }


    private void validations(BugDto model) {
        ServiceResponse<Boolean> isFeatureExist = featureClient.isExist(model.getFeatureId());
        if(!isFeatureExist.getData()) {
            if(isFeatureExist.isSuccessful()) {
                throw new EntityNotFoundException("Feature does not exist");
            }
            throw new EntityNotFoundException("Feature service is not available");
        }
        ServiceResponse<Boolean> isSprintExist = sprintClient.isExist(model.getSprintId());
        if(!isSprintExist.getData()) {
            if(isSprintExist.isSuccessful()) {
                throw new EntityNotFoundException("Sprint does not exist");
            }
            throw new EntityNotFoundException("Sprint service is not available");
        }
    }
}
