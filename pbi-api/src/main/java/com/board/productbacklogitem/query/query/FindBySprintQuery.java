package com.board.productbacklogitem.query.query;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FindBySprintQuery {
    private UUID sprintId;
}
