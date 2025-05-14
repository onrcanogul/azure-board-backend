package com.board.productbacklogitem.query.handler;

import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.query.query.*;
import com.board.productbacklogitem.service.PbiService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PbiQueryHandler {
    private final PbiService service;

    public PbiQueryHandler(PbiService service) {
        this.service = service;
    }

    /**
     *
     * @return Get All Product Backlog Items
     */
    @QueryHandler
    public List<ProductBacklogItemDto> get(FindAllQuery query) {
        return service.get().getData();
    }

    @QueryHandler
    public List<ProductBacklogItemDto> getBySprint(FindBySprintQuery query) {
        return service.get().getData();
    }

    /**
     *
     * @return Get Product Backlog Items by User
     */
    @QueryHandler
    public List<ProductBacklogItemDto> getByUser(FindByAssignedUserQuery query) {
        return service.getByUser(query.getUserId()).getData();
    }

    /**
     *
     * @return Get Product Backlog Items by Feature
     */
    @QueryHandler
    public List<ProductBacklogItemDto> getByFeature(FindByFeatureQuery query) {
        return service.getByUser(query.getFeatureId()).getData();
    }

    /**
     *
     * @return Get Product Backlog Items by Tag
     */
    @QueryHandler
    public List<ProductBacklogItemDto> getByTag(FindByTagQuery query) {
        return service.getByUser(query.getTagId()).getData();
    }

    /**
     *
     * @return Get Product Backlog Item by id
     */
    @QueryHandler
    public ProductBacklogItemDto getById(FindByIdQuery query) {
        return service.getById(query.getId()).getData();
    }
}
