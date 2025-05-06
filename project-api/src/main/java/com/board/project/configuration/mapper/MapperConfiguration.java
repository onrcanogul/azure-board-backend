package com.board.project.configuration.mapper;

import com.board.project.dto.ProjectDto;
import com.board.project.entity.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<Project, ProjectDto> postMapper() {
        return new Mapper<>(Project.class, ProjectDto.class);
    }


}
