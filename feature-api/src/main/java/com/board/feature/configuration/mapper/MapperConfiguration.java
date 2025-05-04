package com.board.feature.configuration.mapper;

import com.board.feature.dto.FeatureDto;
import com.board.feature.entity.Feature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<Feature, FeatureDto> postMapper() {
        return new Mapper<>(Feature.class, FeatureDto.class);
    }
}
