package com.board.productbacklogitem.command.aggregate;

import com.board.productbacklogitem.command.commands.PbiCreatedCommand;
import com.board.productbacklogitem.command.commands.PbiDeleteCommand;
import com.board.productbacklogitem.command.commands.PbiUpdateCommand;
import com.board.productbacklogitem.command.event.PbiCreatedEvent;
import com.board.productbacklogitem.command.event.PbiDeletedEvent;
import com.board.productbacklogitem.command.event.PbiUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Aggregate
public class PbiAggregate {
    @AggregateIdentifier
    private UUID id;
    private UUID sprintId;
    private UUID areaId;
    private UUID featureId;
    private UUID assignedUserId;
    private String description;
    private String functionalDescription;
    private String technicalDescription;
    private int priority;
    private String state;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private Set<UUID> tagIds = new HashSet<>();
    private boolean isDeleted;


    public PbiAggregate() {

    }

    @CommandHandler
    public void handle(PbiCreatedCommand createCommand) {
        PbiCreatedEvent createdEvent = new PbiCreatedEvent();
        BeanUtils.copyProperties(createCommand, createdEvent);
        AggregateLifecycle.apply(createdEvent);
    }

    @EventSourcingHandler
    public void on(PbiCreatedEvent event) {
        this.id = event.getId();
        this.sprintId = event.getSprintId();
        this.areaId = event.getAreaId();
        this.featureId = event.getFeatureId();
        this.assignedUserId = event.getAssignedUserId();
        this.description = event.getDescription();
        this.functionalDescription = event.getFunctionalDescription();
        this.technicalDescription = event.getTechnicalDescription();
        this.priority = event.getPriority();
        this.state = event.getState();
        this.storyPoint = event.getStoryPoint();
        this.businessValue = event.getBusinessValue();
        this.dueDate = event.getDueDate();
        this.startedDate = event.getStartedDate();
        this.completedDate = event.getCompletedDate();
        this.tagIds = event.getTagIds();
        this.isDeleted = event.isDeleted();
    }


    @CommandHandler
    public void handle(PbiUpdateCommand updateCommand) {
        PbiUpdatedEvent updatedEvent = new PbiUpdatedEvent();
        BeanUtils.copyProperties(updateCommand, updatedEvent);
        AggregateLifecycle.apply(updatedEvent);
    }

    @EventSourcingHandler
    public void on(PbiUpdatedEvent event) {
        this.id = event.getId();
        this.sprintId = event.getSprintId();
        this.areaId = event.getAreaId();
        this.featureId = event.getFeatureId();
        this.assignedUserId = event.getAssignedUserId();
        this.description = event.getDescription();
        this.functionalDescription = event.getFunctionalDescription();
        this.technicalDescription = event.getTechnicalDescription();
        this.priority = event.getPriority();
        this.state = event.getState();
        this.storyPoint = event.getStoryPoint();
        this.businessValue = event.getBusinessValue();
        this.dueDate = event.getDueDate();
        this.startedDate = event.getStartedDate();
        this.completedDate = event.getCompletedDate();
        this.tagIds = event.getTagIds();
        this.isDeleted = event.isDeleted();
    }


    @CommandHandler
    public void handle(PbiDeleteCommand deleteCommand) {
        PbiDeletedEvent pbiDeletedEvent = new PbiDeletedEvent();
        BeanUtils.copyProperties(deleteCommand, pbiDeletedEvent);
        AggregateLifecycle.apply(pbiDeletedEvent);
    }

    @EventSourcingHandler
    public void on(PbiDeletedEvent pbiDeletedEvent) {
        this.id = pbiDeletedEvent.getId();
    }


}
