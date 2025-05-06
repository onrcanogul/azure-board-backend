package com.board.team.configuration.mapper;

import com.board.team.dto.TeamDto;
import com.board.team.entity.Team;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {
    @Bean
    public Mapper<Team, TeamDto> postMapper() {
        return new Mapper<>(Team.class, TeamDto.class);
    }
}
