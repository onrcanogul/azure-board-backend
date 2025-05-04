package com.board.bug.query.projection;

import com.board.bug.command.event.BugCreatedEvent;
import com.board.bug.command.event.BugDeletedEvent;
import com.board.bug.command.event.BugUpdatedEvent;
import com.board.bug.dto.BugDto;
import com.board.bug.service.BugService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BugProjection {
    private final BugService service;

    public BugProjection(BugService service) {
        this.service = service;
    }

    @EventHandler
    public void on(BugCreatedEvent event) {
        BugDto dto = new BugDto();
        BeanUtils.copyProperties(event, dto);
        service.create(dto);
    }

    @EventHandler
    public void on(BugUpdatedEvent event) {
        BugDto dto = new BugDto();
        BeanUtils.copyProperties(event, dto);
        service.update(dto);
    }

    @EventHandler
    public void on(BugDeletedEvent event) {
        service.delete(event.getId());
    }
}
