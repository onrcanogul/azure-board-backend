package com.board.bug.command.aggregate;

import com.board.bug.command.command.BugCreatedCommand;
import com.board.bug.command.command.BugDeletedCommand;
import com.board.bug.command.command.BugUpdatedCommand;
import com.board.bug.command.event.BugCreatedEvent;
import com.board.bug.command.event.BugDeletedEvent;
import com.board.bug.command.event.BugUpdatedEvent;
import com.board.bug.enumeration.BugStatus;
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
public class BugAggregate {
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
    private BugStatus status;
    private int storyPoint;
    private int businessValue;
    private LocalDateTime dueDate;
    private LocalDateTime startedDate;
    private LocalDateTime completedDate;
    private boolean isNoBug;
    private boolean isDeleted;
    private Set<UUID> tagIds = new HashSet<>();

    public BugAggregate() {
    }
    /**
     * Command handler constructor used to create a new Product Backlog Item (PBI) aggregate.
     *
     * This constructor is triggered when a {@link BugCreatedCommand} is received **and no existing aggregate**
     * with the same ID is found in the Event Store.
     *
     * In Axon Framework, constructor-based command handlers are used **only for creating new aggregates**.
     * This is where you "initialize" your aggregate by applying a creation event.
     *
     * @param createCommand the command carrying the initial state of the aggregate to be created
     */
    @CommandHandler
    public BugAggregate(BugCreatedCommand createCommand) {
        BugCreatedEvent createdEvent = new BugCreatedEvent();
        BeanUtils.copyProperties(createCommand, createdEvent);
        AggregateLifecycle.apply(createdEvent);
    }

    @EventSourcingHandler
    public void on(BugCreatedEvent event) {
        this.id = event.getId();
        this.sprintId = event.getSprintId();
        this.areaId = event.getAreaId();
        this.featureId = event.getFeatureId();
        this.assignedUserId = event.getAssignedUserId();
        this.description = event.getDescription();
        this.functionalDescription = event.getFunctionalDescription();
        this.technicalDescription = event.getTechnicalDescription();
        this.priority = event.getPriority();
        this.status = event.getStatus();
        this.storyPoint = event.getStoryPoint();
        this.businessValue = event.getBusinessValue();
        this.dueDate = event.getDueDate();
        this.startedDate = event.getStartedDate();
        this.completedDate = event.getCompletedDate();
        this.tagIds = event.getTagIds();
        this.isDeleted = event.isDeleted();
        this.isNoBug = event.isNoBug();
    }

    /**
     *
     * @param updateCommand - Update Command Model
     * @return Create BugUpdatedEvent and send
     */
    @CommandHandler
    public void handle(BugUpdatedCommand updateCommand) {
        BugUpdatedEvent updatedEvent = new BugUpdatedEvent();
        BeanUtils.copyProperties(updateCommand, updatedEvent);
        AggregateLifecycle.apply(updatedEvent);
    }

    @EventSourcingHandler
    public void on(BugUpdatedEvent event) {
        this.id = event.getId();
        this.sprintId = event.getSprintId();
        this.areaId = event.getAreaId();
        this.featureId = event.getFeatureId();
        this.assignedUserId = event.getAssignedUserId();
        this.description = event.getDescription();
        this.functionalDescription = event.getFunctionalDescription();
        this.technicalDescription = event.getTechnicalDescription();
        this.priority = event.getPriority();
        this.status = event.getStatus();
        this.storyPoint = event.getStoryPoint();
        this.businessValue = event.getBusinessValue();
        this.dueDate = event.getDueDate();
        this.startedDate = event.getStartedDate();
        this.completedDate = event.getCompletedDate();
        this.tagIds = event.getTagIds();
        this.isDeleted = event.isDeleted();
        this.isNoBug = event.isNoBug();
    }

    /**
     *
     * @param deleteCommand - Delete Command Model
     * @return Create BugDeletedEvent and send
     */
    @CommandHandler
    public void handle(BugDeletedCommand deleteCommand) {
        BugDeletedEvent pbiDeletedEvent = new BugDeletedEvent();
        BeanUtils.copyProperties(deleteCommand, pbiDeletedEvent);
        AggregateLifecycle.apply(pbiDeletedEvent);
    }

    @EventSourcingHandler
    public void on(BugDeletedEvent pbiDeletedEvent) {
        this.id = pbiDeletedEvent.getId();
    }
}
