package com.board.project.controller;

import com.board.project.dto.ProjectDto;
import com.board.project.service.ProjectService;
import com.board.project.util.NoContent;
import com.board.project.util.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProjectController {
    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<ProjectDto>>> get() {
        ServiceResponse<List<ProjectDto>> response = service.get();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ServiceResponse<ProjectDto>> getByProject(@PathVariable UUID projectId) {
        ServiceResponse<ProjectDto> response = service.getById(projectId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<ProjectDto>> create(@RequestBody ProjectDto model) {
        ServiceResponse<ProjectDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping
    public ResponseEntity<ServiceResponse<ProjectDto>> update(@RequestBody ProjectDto model) {
        ServiceResponse<ProjectDto> response = service.update(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
