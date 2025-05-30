package com.board.productbacklogitem.command.aggregate;

import com.board.productbacklogitem.command.command.PbiCreatedCommand;
import com.board.productbacklogitem.command.command.PbiDeleteCommand;
import com.board.productbacklogitem.command.command.PbiUpdateCommand;
import com.board.productbacklogitem.command.command.PbiUpdateStateCommand;
import com.board.productbacklogitem.command.event.PbiCreatedEvent;
import com.board.productbacklogitem.command.event.PbiDeletedEvent;
import com.board.productbacklogitem.command.event.PbiStateUpdatedEvent;
import com.board.productbacklogitem.command.event.PbiUpdatedEvent;
import com.board.productbacklogitem.enumeration.PbiState;
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
    private PbiState state;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private Set<UUID> tagIds = new HashSet<>();
    private boolean isDeleted;


    public PbiAggregate() {

    }
    /**
     * Command handler constructor used to create a new Product Backlog Item (PBI) aggregate.
     *
     * This constructor is triggered when a {@link PbiCreatedCommand} is received **and no existing aggregate**
     * with the same ID is found in the Event Store.
     *
     * In Axon Framework, constructor-based command handlers are used **only for creating new aggregates**.
     * This is where you "initialize" your aggregate by applying a creation event.
     *
     * @param createCommand the command carrying the initial state of the aggregate to be created
     */
    @CommandHandler
    public PbiAggregate(PbiCreatedCommand createCommand) {
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

    /**
     *
     * @param updateCommand - Update Command Model
     * @return Create UpdatePbiEvent and send
     */
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

    @EventSourcingHandler
    public void on(PbiStateUpdatedEvent event) {
        this.id = event.getId();
        this.state = event.getState();
    }
    /**
     *
     * @param stateUpdateCommand - State update infos
     * @return Pbi state update
     */
    @CommandHandler
    public void handle(PbiUpdateStateCommand stateUpdateCommand) {
        PbiStateUpdatedEvent stateUpdatedEvent = new PbiStateUpdatedEvent();
        BeanUtils.copyProperties(stateUpdateCommand, stateUpdatedEvent);
        AggregateLifecycle.apply(stateUpdatedEvent);
    }


    /**
     *
     * @param deleteCommand - Delete Command Model
     * @return Create DeletePbiEvent and send
     */
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
