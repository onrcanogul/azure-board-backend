package com.board.productbacklogitem.query.query;

import lombok.AllArgsConstructor;
import lombok.Value;


import java.util.UUID;

@Value
@AllArgsConstructor
public class FindByAssignedUserQuery {
    private final UUID userId;
}
