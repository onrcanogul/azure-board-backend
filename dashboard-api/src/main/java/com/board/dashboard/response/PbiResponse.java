package com.board.dashboard.response;

import com.board.dashboard.dto.PbiDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PbiResponse {
    private List<PbiDto> data = new ArrayList<>();
    private Object errors;
    private int statusCode;
    private boolean successful;
}
