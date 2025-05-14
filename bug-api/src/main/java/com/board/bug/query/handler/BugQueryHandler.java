package com.board.bug.query.handler;

import com.board.bug.dto.BugDto;
import com.board.bug.query.query.*;
import com.board.bug.service.BugService;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BugQueryHandler {
    private final BugService service;

    public BugQueryHandler(BugService service) {
        this.service = service;
    }

    /**
     *
     * @return Get All Bugs
     */
    @QueryHandler
    public List<BugDto> get(FindAllQuery query) {
        return service.get().getData();
    }

    /**
     *
     * @return Get Bugs by Sprint
     */
    @QueryHandler
    public List<BugDto> getBySprint(FindBySprintQuery query) {
        return service.getBySprint(query.getSprintId()).getData();
    }

    /**
     *
     * @return Get Bugs by User
     */
    @QueryHandler
    public List<BugDto> getByUser(FindByAssignedUserQuery query) {
        return service.getByUser(query.getUserId()).getData();
    }

    /**
     *
     * @return Get Bugs by Feature
     */
    @QueryHandler
    public List<BugDto> getByFeature(FindByFeatureQuery query) {
        return service.getByUser(query.getFeatureId()).getData();
    }

    /**
     *
     * @return Get Bugs by Tag
     */
    @QueryHandler
    public List<BugDto> getByTag(FindByTagQuery query) {
        return service.getByUser(query.getTagId()).getData();
    }

    /**
     *
     * @return Get Bug by id
     */
    @QueryHandler
    public BugDto getById(FindByIdQuery query) {
        return service.getById(query.getId()).getData();
    }
}
