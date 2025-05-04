package com.board.bug.configuration.mapper;

import com.board.bug.command.command.BugCreatedCommand;
import com.board.bug.command.command.BugUpdatedCommand;
import com.board.bug.dto.BugDto;
import com.board.bug.entity.Bug;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {
    @Bean
    public Mapper<Bug, BugDto> postMapper() {
        return new Mapper<>(Bug.class, BugDto.class);
    }

    @Bean
    public Mapper<BugCreatedCommand, BugDto> createdCommandMapper() {
        return new Mapper<>(BugCreatedCommand.class, BugDto.class);
    }

    @Bean
    public Mapper<BugUpdatedCommand, BugDto> updatedCommandMapper() {
        return new Mapper<>(BugUpdatedCommand.class, BugDto.class);
    }
}
