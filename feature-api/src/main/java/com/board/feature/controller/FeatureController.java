package com.board.feature.controller;

import com.board.feature.dto.FeatureDto;
import com.board.feature.service.FeatureService;
import com.board.feature.utils.NoContent;
import com.board.feature.utils.ServiceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FeatureController {
    private final FeatureService service;

    public FeatureController(FeatureService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse<FeatureDto>> getById(@PathVariable UUID id) {
        ServiceResponse<FeatureDto> response = service.getById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/epic/{epicId}")
    public ResponseEntity<ServiceResponse<List<FeatureDto>>> getByEpic(@PathVariable UUID epicId) {
        ServiceResponse<List<FeatureDto>> response = service.getByEpic(epicId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/area/{areaId}")
    public ResponseEntity<ServiceResponse<List<FeatureDto>>> getByArea(@PathVariable UUID areaId) {
        ServiceResponse<List<FeatureDto>> response = service.getByArea(areaId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<FeatureDto>> create(@RequestBody FeatureDto model) {
        ServiceResponse<FeatureDto> response = service.create(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/complete/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> complete(@PathVariable UUID id) throws JsonProcessingException {
        ServiceResponse<NoContent> response = service.complete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping
    public ResponseEntity<ServiceResponse<FeatureDto>> update(@RequestBody FeatureDto model) {
        ServiceResponse<FeatureDto> response = service.update(model);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        ServiceResponse<NoContent> response = service.delete(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
