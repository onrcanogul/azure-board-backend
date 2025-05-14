package com.board.dashboard.service;

import com.board.dashboard.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final WebClient.Builder webClientBuilder;
    @Value("${pbi-service.url}")
    private String pbiServiceUrl;

    @Value("${bug-service.url}")
    private String bugServiceUrl;

    public Mono<DashboardDto> getDashboardBySprint(UUID sprintId) {
        WebClient webClient = WebClient.builder().build();

        Mono<List<PbiDto>> pbItemsMono = webClient.get()
                .uri(pbiServiceUrl + "/sprint/" + sprintId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PbiDto>>() {});

        Mono<List<BugDto>> bugsMono = webClient.get()
                .uri(bugServiceUrl + "/sprint/" + sprintId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BugDto>>(){});

        return Mono.zip(pbItemsMono, bugsMono)
                .map(tuple -> new DashboardDto(tuple.getT1(), tuple.getT2()));

    }

    public Mono<DashboardDto> getDashboardByUser(UUID userId) {
        WebClient webClient = WebClient.builder().build();

        Mono<List<PbiDto>> pbItemsMono = webClient.get()
                .uri(pbiServiceUrl + "/user/" + userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PbiDto>>() {});

        Mono<List<BugDto>> bugsMono = webClient.get()
                .uri(bugServiceUrl + "/user/" + userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<BugDto>>(){});

        return Mono.zip(pbItemsMono, bugsMono)
                .map(tuple -> new DashboardDto(tuple.getT1(), tuple.getT2()));
    }

}
