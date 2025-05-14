package com.board.productbacklogitem.service.impl;

import com.board.productbacklogitem.configuration.mapper.Mapper;
import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.entity.ProductBacklogItem;
import com.board.productbacklogitem.enumeration.PbiState;
import com.board.productbacklogitem.repository.PbiRepository;
import com.board.productbacklogitem.service.PbiService;
import com.board.productbacklogitem.client.FeatureClient;
import com.board.productbacklogitem.client.SprintClient;
import com.board.productbacklogitem.utils.service.NoContent;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PbiServiceImpl implements PbiService {

    private final PbiRepository repository;
    private final Mapper<ProductBacklogItem, ProductBacklogItemDto> mapper;
    private final SprintClient sprintClient;
    private final FeatureClient featureClient;

    public PbiServiceImpl(PbiRepository repository, Mapper<ProductBacklogItem, ProductBacklogItemDto> mapper, @Qualifier("com.board.productbacklogitem.client.SprintClient") SprintClient sprintClient, @Qualifier("com.board.productbacklogitem.client.FeatureClient") FeatureClient featureClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.sprintClient = sprintClient;
        this.featureClient = featureClient;
    }
    /**
     *
     * @return Get all product backlog items
     */
    @Override
    public ServiceResponse<List<ProductBacklogItemDto>> get() {
        return ServiceResponse
                .success(repository.findAll().stream().map(mapper::toDto).toList(), 200);
    }

    @Override
    public ServiceResponse<List<ProductBacklogItemDto>> getBySprint(UUID sprintId) {
        return ServiceResponse
                .success(repository.findBySprintId(sprintId).stream().map(mapper::toDto).toList(), 200);
    }

    /**
     *
     * @param userId - Assigned User Id
     * @return Get all product backlog items by user id
     */
    @Override
    public ServiceResponse<List<ProductBacklogItemDto>> getByUser(UUID userId) {
        return ServiceResponse
                .success(repository.findByAssignedUserId(userId).stream().map(mapper::toDto).toList(), 200);
    }
    /**
     *
     * @param tagId - Tag Id
     * @return Get all product backlog items by tag id
     */
    @Override
    public ServiceResponse<List<ProductBacklogItemDto>> getByTag(UUID tagId) {
        return ServiceResponse
                .success(repository.findByTagId(tagId).stream().map(mapper::toDto).toList(), 200);
    }
    /**
     *
     * @param featureId - Feature Id
     * @return Get all product backlog items by feature id
     */
    @Override
    public ServiceResponse<List<ProductBacklogItemDto>> getByFeature(UUID featureId) {
        return ServiceResponse
                .success(repository.findByFeatureId(featureId).stream().map(mapper::toDto).toList(), 200);
    }
    /**
     *
     * @param id - Id
     * @return Get product backlog item by feature id
     */
    @Override
    public ServiceResponse<ProductBacklogItemDto> getById(UUID id) {
        return ServiceResponse.success(mapper.toDto(repository.findById(id).orElseThrow()), 200);
    }
    /**
     *
     * @param model - Product Backlog Item Dto
     * @return Create product backlog item
     */
    @Override
    public ServiceResponse<ProductBacklogItemDto> create(ProductBacklogItemDto model) {
//        validations(model);
        ProductBacklogItem productBacklogItem = mapper.toEntity(model);
        ProductBacklogItem createdProductBacklogItem = repository.save(productBacklogItem);
        return ServiceResponse.success(mapper.toDto(createdProductBacklogItem), 201);

    }

    @Override
    public ServiceResponse<ProductBacklogItemDto> updateStatus(UUID id, PbiState state) {
        ProductBacklogItem pbi = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        pbi.setState(state);
        ProductBacklogItem savedPbi = repository.save(pbi);
        return ServiceResponse.success(mapper.toDto(savedPbi), 200);
    }

    /**
     *
     * @param model - Product Backlog Item Dto
     * @return Update product backlog item
     */
    @Override
    public ServiceResponse<ProductBacklogItemDto> update(ProductBacklogItemDto model) {
//        validations(model);
        Optional<ProductBacklogItem> optional = repository.findById(model.getId());
        if (optional.isEmpty()) {
            return ServiceResponse.failure("Product Backlog Item not found with id: " + model.getId(), 404);
        }
        ProductBacklogItem productBacklogItem = optional.get();
        updateMembers(productBacklogItem, model);
        ProductBacklogItemDto responseDto = mapper.toDto(repository.save(productBacklogItem));
        return ServiceResponse.success(responseDto, 200);
    }

    /**
     *
     * @param id - Id
     * @return Delete product backlog item by id
     */
    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        ProductBacklogItem productBacklogItem = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        productBacklogItem.setIsDeleted(true);
        repository.save(productBacklogItem);
        return ServiceResponse.success(204);
    }
    /**
     *
     * @param entity
     * @param model
     * @return Update members
     */
    private void updateMembers(ProductBacklogItem entity, ProductBacklogItemDto model) {
        entity.setSprintId(model.getSprintId());
        entity.setAreaId(model.getAreaId());
        entity.setFeatureId(model.getFeatureId());
        entity.setAssignedUserId(model.getAssignedUserId());
        entity.setDescription(model.getDescription());
        entity.setFunctionalDescription(model.getFunctionalDescription());
        entity.setTechnicalDescription(model.getTechnicalDescription());
        entity.setPriority(model.getPriority());
        entity.setState(model.getState());
        entity.setStoryPoint(model.getStoryPoint());
        entity.setBusinessValue(model.getBusinessValue());
        entity.setDueDate(model.getDueDate());
        entity.setStartedDate(model.getStartedDate());
        entity.setCompletedDate(model.getCompletedDate());
        entity.setTagIds(new HashSet<>(model.getTagIds()));
        entity.setIsDeleted(model.isDeleted());
    }

    private void validations(ProductBacklogItemDto model) {
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
