package com.board.bug.query.controller;

import com.board.bug.dto.BugDto;
import com.board.bug.query.query.*;
import com.board.bug.utils.ServiceResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bug")
public class BugQueryController {
    private final QueryGateway gateway;

    public BugQueryController(QueryGateway gateway) {
        this.gateway = gateway;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<BugDto>>> get() {
        List<BugDto> data = gateway
                .query(new FindAllQuery(), ResponseTypes.multipleInstancesOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Bug by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<BugDto>> getById(@PathVariable UUID id) {
        BugDto data = gateway
                .query(new FindByIdQuery(id), ResponseTypes.instanceOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Bugs by Assigned User
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ServiceResponse<List<BugDto>>> getByUser(@PathVariable UUID userId) {
        List<BugDto> data = gateway
                .query(new FindByAssignedUserQuery(userId), ResponseTypes.multipleInstancesOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<ServiceResponse<List<BugDto>>> getBySprint(@PathVariable UUID sprintId) {
        List<BugDto> data = gateway
                .query(new FindBySprintQuery(sprintId), ResponseTypes.multipleInstancesOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Bugs by Tag
     */
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<ServiceResponse<List<BugDto>>> getByTag(@PathVariable UUID tagId) {
        List<BugDto> data = gateway
                .query(new FindByTagQuery(tagId), ResponseTypes.multipleInstancesOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Bugs by Feature
     */
    @GetMapping("/feature/{featureId}")
    public ResponseEntity<ServiceResponse<List<BugDto>>> getByFeature(@PathVariable UUID featureId) {
        List<BugDto> data = gateway
                .query(new FindByFeatureQuery(featureId), ResponseTypes.multipleInstancesOf(BugDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }
}
