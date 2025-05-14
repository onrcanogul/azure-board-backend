package com.board.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DashboardDto {
    private List<PbiDto> productBacklogItems = new ArrayList<>();
    private List<BugDto> bugs = new ArrayList<>();
}
