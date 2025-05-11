package com.board.sprint.service;

import com.board.sprint.dto.SprintDto;
import com.board.sprint.utils.NoContent;
import com.board.sprint.utils.ServiceResponse;

import java.util.List;
import java.util.UUID;

public interface SprintService {

    /**
     * Retrieves a sprint associated with the given id.
     *
     * @param id the ID of the Sprint
     * @return a sprint under the specified id
     */
    ServiceResponse<SprintDto> getById(UUID id);
    /**
     * Retrieves all sprints associated with the given project.
     *
     * @param projectId the ID of the project
     * @return list of sprints under the specified project
     */
    ServiceResponse<List<SprintDto>> getByProject(UUID projectId);

    /**
     * Retrieves all sprints associated with the given team.
     *
     * @param teamId the ID of the team
     * @return list of sprints under the specified team
     */
    ServiceResponse<List<SprintDto>> getByTeam(UUID teamId);

    /**
     * Retrieves all sprints associated with the given team.
     *
     * @param id the ID of the team
     * @return IsExist
     */
    ServiceResponse<Boolean> isExist(UUID id);

    /**
     * Creates a new sprint with the provided data.
     *
     * @param model the sprint details to be created
     * @return the created sprint data
     */
    ServiceResponse<SprintDto> create(SprintDto model);

    /**
     * Updates an existing sprint based on the given data.
     *
     * @param model the sprint data to update
     * @return the updated sprint data
     * @throws Exception if the sprint cannot be found or updated
     */
    ServiceResponse<SprintDto> update(SprintDto model) throws Exception;

    /**
     * Deletes the sprint identified by the given ID.
     *
     * @param id the ID of the sprint to delete
     * @return success status if the deletion is completed
     * @throws Exception if the sprint cannot be found or deleted
     */
    ServiceResponse<NoContent> delete(UUID id) throws Exception;
}
