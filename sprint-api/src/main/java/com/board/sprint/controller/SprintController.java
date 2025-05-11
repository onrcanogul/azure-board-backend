package com.board.sprint.controller;

import com.board.sprint.dto.SprintDto;
import com.board.sprint.service.SprintService;
import com.board.sprint.utils.NoContent;
import com.board.sprint.utils.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    private final SprintService service;

    public SprintController(SprintService service) {
        this.service = service;
    }
    /**
     * Retrieves a sprint associated with the given id.
     *
     * @param id the ID of the Sprint
     * @return a sprint under the specified id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<SprintDto>> getById(@PathVariable UUID id) {
        ServiceResponse<SprintDto> response = service.getById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    /**
     * Retrieves all sprints assigned to a specific team.
     *
     * @param id the UUID of the team
     * @return list of sprints associated with the team
     */
    @GetMapping("/team/{id}")
    public ResponseEntity<ServiceResponse<List<SprintDto>>> getByTeam(@PathVariable UUID id) {
        ServiceResponse<List<SprintDto>> response = service.getByTeam(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Retrieves all sprints under a specific project.
     *
     * @param id the UUID of the project
     * @return list of sprints associated with the project
     */
    @GetMapping("/project/{id}")
    public ResponseEntity<ServiceResponse<List<SprintDto>>> getByProject(@PathVariable UUID id) {
        ServiceResponse<List<SprintDto>> response = service.getByProject(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Retrieves all sprints under a specific project.
     *
     * @param id the UUID of the team
     * @return isExist
     */
    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id) {
        return service.isExist(id);
    }

    /**
     * Creates a new sprint.
     *
     * @param model the sprint data to create
     * @return the created sprint
     */
    @PostMapping
    public ResponseEntity<ServiceResponse<SprintDto>> create(@RequestBody SprintDto model) {
        ServiceResponse<SprintDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Updates an existing sprint.
     *
     * @param model the sprint data to update
     * @return the updated sprint
     * @throws Exception if the sprint is not found
     */
    @PutMapping
    public ResponseEntity<ServiceResponse<SprintDto>> update(@RequestBody SprintDto model) throws Exception {
        ServiceResponse<SprintDto> response = service.update(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    /**
     * Marks a sprint as deleted based on its ID.
     *
     * @param id the UUID of the sprint to delete
     * @return confirmation of deletion
     * @throws Exception if the sprint is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) throws Exception {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
