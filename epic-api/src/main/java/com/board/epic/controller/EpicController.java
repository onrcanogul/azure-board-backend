package com.board.epic.controller;

import com.board.epic.dto.EpicDto;
import com.board.epic.service.EpicService;
import com.board.epic.utils.NoContent;
import com.board.epic.utils.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/epic")
public class EpicController {
    private final EpicService service;

    public EpicController(EpicService service) {
        this.service = service;
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<ServiceResponse<List<EpicDto>>> getByTeam(@PathVariable UUID teamId) {
        ServiceResponse<List<EpicDto>> response = service.getByTeam(teamId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<ServiceResponse<List<EpicDto>>> getByArea(@PathVariable UUID areaId) {
        ServiceResponse<List<EpicDto>> response = service.getByArea(areaId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<EpicDto>> create(@RequestBody EpicDto model) {
        ServiceResponse<EpicDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping
    public ResponseEntity<ServiceResponse<EpicDto>> update(@RequestBody EpicDto model) {
        ServiceResponse<EpicDto> response = service.update(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
