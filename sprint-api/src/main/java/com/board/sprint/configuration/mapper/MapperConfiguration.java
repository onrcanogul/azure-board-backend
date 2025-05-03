package com.board.sprint.configuration.mapper;

import com.board.sprint.dto.SprintDto;
import com.board.sprint.entity.Sprint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<Sprint, SprintDto> postMapper() {
        return new Mapper<>(Sprint.class, SprintDto.class);
    }


}
