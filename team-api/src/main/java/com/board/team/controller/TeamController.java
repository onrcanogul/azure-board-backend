package com.board.team.controller;

import com.board.team.dto.TeamDto;
import com.board.team.service.TeamService;
import com.board.team.util.NoContent;
import com.board.team.util.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<TeamDto>>> get() {
        ServiceResponse<List<TeamDto>> response = service.getAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ServiceResponse<List<TeamDto>>> getByProject(@PathVariable UUID projectId) {
        ServiceResponse<List<TeamDto>> response = service.getByProject(projectId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/isExist/{id}")
    public ServiceResponse<Boolean> isExist(@PathVariable UUID id) {
        return service.isExist(id);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<TeamDto>> create(@RequestBody TeamDto model) {
        ServiceResponse<TeamDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping
    public ResponseEntity<ServiceResponse<TeamDto>> update(@RequestBody TeamDto model) {
        ServiceResponse<TeamDto> response = service.update(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
