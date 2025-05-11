package com.board.project.service.impl;

import com.board.project.configuration.mapper.Mapper;
import com.board.project.dto.ProjectDto;
import com.board.project.entity.Project;
import com.board.project.repository.ProjectRepository;
import com.board.project.service.ProjectService;
import com.board.project.util.NoContent;
import com.board.project.util.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final Mapper<Project, ProjectDto> mapper;

    public ProjectServiceImpl(ProjectRepository repository, Mapper<Project, ProjectDto> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ServiceResponse<List<ProjectDto>> get() {
        return ServiceResponse.success(
                repository.findAll().stream().map(mapper::toDto).toList(), 200
        );
    }

    @Override
    public ServiceResponse<ProjectDto> getById(UUID id) {
        return ServiceResponse.success(
                mapper.toDto(repository.findById(id).orElseThrow()), 200
        );
    }

    @Override
    public ServiceResponse<Boolean> isExist(UUID id) {
        return ServiceResponse.success(repository.isExistById(id), 200);
    }

    @Override
    public ServiceResponse<ProjectDto> create(ProjectDto model) {
        Project project = mapper.toEntity(model);
        project.setId(null);
        Project createdProject = repository.save(project);
        return ServiceResponse.success(mapper.toDto(createdProject), 201);
    }

    @Override
    public ServiceResponse<ProjectDto> update(ProjectDto model) {
        Project project = repository.findById(model.getId()).orElseThrow();
        project.setName(model.getName());
        project.setDescription(model.getDescription());
        Project updatedProject = repository.save(project);
        return ServiceResponse.success(mapper.toDto(updatedProject), 200);
    }

    @Override
    public ServiceResponse<NoContent> delete(UUID id) {
        Project project = repository.findById(id).orElseThrow();
        project.setDeleted(true);
        repository.save(project);
        return ServiceResponse.success(204);
    }
}
