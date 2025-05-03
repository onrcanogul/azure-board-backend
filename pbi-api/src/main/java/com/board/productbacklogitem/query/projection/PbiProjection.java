package com.board.productbacklogitem.query.projection;

import com.board.productbacklogitem.command.event.PbiCreatedEvent;
import com.board.productbacklogitem.command.event.PbiDeletedEvent;
import com.board.productbacklogitem.command.event.PbiUpdatedEvent;
import com.board.productbacklogitem.dto.ProductBacklogItemDto;
import com.board.productbacklogitem.service.PbiService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PbiProjection {
    private final PbiService service;

    public PbiProjection(PbiService service) {
        this.service = service;
    }

    @EventHandler
    public void on(PbiCreatedEvent event) {
        ProductBacklogItemDto dto = new ProductBacklogItemDto();
        BeanUtils.copyProperties(event, dto);
        service.create(dto);
    }

    @EventHandler
    public void on(PbiUpdatedEvent event) {
        ProductBacklogItemDto dto = new ProductBacklogItemDto();
        BeanUtils.copyProperties(event, dto);
        service.update(dto);
    }

    @EventHandler
    public void on(PbiDeletedEvent event) {
        service.delete(event.getId());
    }
}
