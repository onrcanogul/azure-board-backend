package com.board.bug.service;

import com.board.bug.dto.BugDto;
import com.board.bug.enumeration.BugStatus;
import com.board.bug.utils.NoContent;
import com.board.bug.utils.ServiceResponse;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing Bug entities and related operations.
 */
public interface BugService {

    /**
     * Retrieves all bugs in the system.
     *
     * @return ServiceResponse containing a list of all BugDto objects and HTTP status 200
     */
    ServiceResponse<List<BugDto>> get();

    /**
     * Retrieves a bug by its unique identifier.
     *
     * @param id the ID of the bug to retrieve
     * @return ServiceResponse containing the BugDto and HTTP status 200
     */
    ServiceResponse<BugDto> getById(UUID id);

    /**
     * Retrieves all bugs assigned to a specific user.
     *
     * @param userId the ID of the assigned user
     * @return ServiceResponse containing a list of BugDto and HTTP status 200
     */
    ServiceResponse<List<BugDto>> getByUser(UUID userId);

    /**
     * Retrieves all bugs associated with a specific tag.
     *
     * @param tagId the ID of the tag
     * @return ServiceResponse containing a list of BugDto and HTTP status 200
     */
    ServiceResponse<List<BugDto>> getByTag(UUID tagId);

    /**
     * Retrieves all bugs associated with a specific feature.
     *
     * @param featureId the ID of the feature
     * @return ServiceResponse containing a list of BugDto and HTTP status 200
     */
    ServiceResponse<List<BugDto>> getByFeature(UUID featureId);

    /**
     * Creates a new bug in the system.
     *
     * @param model the BugDto containing details for the new bug
     * @return ServiceResponse containing the created BugDto and HTTP status 201
     */
    ServiceResponse<BugDto> create(BugDto model);

    /**
     * Updates an existing bug in the system.
     *
     * @param model the BugDto containing updated information
     * @return ServiceResponse containing the updated BugDto and HTTP status 200
     */
    ServiceResponse<BugDto> update(BugDto model);

    /**
     * Updates status of an existing bug in the system.
     *
     * @param id - id of the bug
     * @param state - state of the bug
     * @return ServiceResponse containing the updated BugDto and HTTP status 200
     */
    ServiceResponse<BugDto> updateState(UUID id, BugStatus state);

    /**
     * Soft-deletes a bug by marking it as deleted.
     *
     * @param id the ID of the bug to delete
     * @return ServiceResponse containing NoContent and HTTP status 204
     */
    ServiceResponse<NoContent> delete(UUID id);
}

