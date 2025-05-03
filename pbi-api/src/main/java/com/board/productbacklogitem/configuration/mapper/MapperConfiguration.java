package com.board.productbacklogitem.configuration.mapper;

import com.board.productbacklogitem.command.commands.PbiCreatedCommand;
import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.entity.ProductBacklogItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configure Mapping Relations
@Configuration
public class MapperConfiguration {

    @Bean
    public Mapper<ProductBacklogItem, ProductBacklogItemDto> postMapper() {
        return new Mapper<>(ProductBacklogItem.class, ProductBacklogItemDto.class);
    }

    @Bean
    public Mapper<PbiCreatedCommand, ProductBacklogItemDto> commandMapper() {
        return new Mapper<>(PbiCreatedCommand.class, ProductBacklogItemDto.class);
    }
}
