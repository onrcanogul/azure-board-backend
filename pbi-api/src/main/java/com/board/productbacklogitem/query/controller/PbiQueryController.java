package com.board.productbacklogitem.query.controller;

import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.query.query.*;
import com.board.productbacklogitem.utils.service.ServiceResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pbi")
public class PbiQueryController {
    private final QueryGateway queryGateway;

    public PbiQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    /**
     * Get all Product Backlog Items
     */
    @GetMapping
    public ResponseEntity<ServiceResponse<List<ProductBacklogItemDto>>> get() {
        List<ProductBacklogItemDto> data = queryGateway
                .query(new FindAllQuery(), ResponseTypes.multipleInstancesOf(ProductBacklogItemDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Product Backlog Item by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<ProductBacklogItemDto>> getById(@PathVariable UUID id) {
        ProductBacklogItemDto data = queryGateway
                .query(new FindByIdQuery(id), ResponseTypes.instanceOf(ProductBacklogItemDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Product Backlog Items by Assigned User
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ServiceResponse<List<ProductBacklogItemDto>>> getByUser(@PathVariable UUID userId) {
        List<ProductBacklogItemDto> data = queryGateway
                .query(new FindByAssignedUserQuery(userId), ResponseTypes.multipleInstancesOf(ProductBacklogItemDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Product Backlog Items by Tag
     */
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<ServiceResponse<List<ProductBacklogItemDto>>> getByTag(@PathVariable UUID tagId) {
        List<ProductBacklogItemDto> data = queryGateway
                .query(new FindByTagQuery(tagId), ResponseTypes.multipleInstancesOf(ProductBacklogItemDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }

    /**
     * Get Product Backlog Items by Feature
     */
    @GetMapping("/feature/{featureId}")
    public ResponseEntity<ServiceResponse<List<ProductBacklogItemDto>>> getByFeature(@PathVariable UUID featureId) {
        List<ProductBacklogItemDto> data = queryGateway
                .query(new FindByFeatureQuery(featureId), ResponseTypes.multipleInstancesOf(ProductBacklogItemDto.class))
                .join();

        return ResponseEntity.ok(ServiceResponse.success(data, 200));
    }
}
