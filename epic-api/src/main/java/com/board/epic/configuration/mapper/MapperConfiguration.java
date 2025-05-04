package com.board.epic.configuration.mapper;

import com.board.epic.dto.EpicDto;
import com.board.epic.entity.Epic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<Epic, EpicDto> postMapper() {
        return new Mapper<>(Epic.class, EpicDto.class);
    }
}
