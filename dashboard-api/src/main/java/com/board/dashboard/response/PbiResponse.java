package com.board.dashboard.response;

import com.board.dashboard.dto.PbiDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PbiResponse {
    private List<PbiDto> pbis = new ArrayList<>();
}
