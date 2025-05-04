package com.board.bug.query.query;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class FindByIdQuery {
    private final UUID id;
}
