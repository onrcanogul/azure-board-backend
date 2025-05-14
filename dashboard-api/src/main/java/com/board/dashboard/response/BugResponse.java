package com.board.dashboard.response;

import com.board.dashboard.dto.BugDto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BugResponse {
    private List<BugDto> data = new ArrayList<>();
    private Object errors;
    private int statusCode;
    private boolean successful;
}
