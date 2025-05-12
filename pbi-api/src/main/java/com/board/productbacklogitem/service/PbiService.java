package com.board.productbacklogitem.service;

import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.enumeration.PbiState;
import com.board.productbacklogitem.utils.service.NoContent;
import com.board.productbacklogitem.utils.service.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface PbiService {
    /**
     *
     * @return Get all product backlog items
     */
    ServiceResponse<List<ProductBacklogItemDto>> get();
    /**
     *
     * @param userId - Assigned User Id
     * @return Get all product backlog items by user id
     */
    ServiceResponse<List<ProductBacklogItemDto>> getByUser(UUID userId);
    /**
     *
     * @param tagId - Tag Id
     * @return Get all product backlog items by tag id
     */
    ServiceResponse<List<ProductBacklogItemDto>> getByTag(UUID tagId);
    /**
     *
     * @param featureId - Feature Id
     * @return Get all product backlog items by feature id
     */
    ServiceResponse<List<ProductBacklogItemDto>> getByFeature(UUID featureId);
    /**
     *
     * @param id - Id
     * @return Get product backlog item by feature id
     */
    ServiceResponse<ProductBacklogItemDto> getById(UUID id);
    /**
     *
     * @param model - Product Backlog Item Dto
     * @return Create product backlog item
     */
    ServiceResponse<ProductBacklogItemDto> create(ProductBacklogItemDto model);

    /**
     *
     * @param id - Product Backlog Item id
     * @param state - Product Backlog Item state
     * @return Update product backlog item
     */
    ServiceResponse<ProductBacklogItemDto> updateStatus(UUID id, PbiState state);
    /**
     *
     * @param model - Product Backlog Item Dto
     * @return Update product backlog item
     */
    ServiceResponse<ProductBacklogItemDto> update(ProductBacklogItemDto model);
    /**
     *
     * @param id - Id
     * @return Delete product backlog item by id
     */
    ServiceResponse<NoContent> delete(UUID id);
}
