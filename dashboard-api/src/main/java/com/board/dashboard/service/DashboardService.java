package com.board.dashboard.service;

import com.board.dashboard.dto.*;
import com.board.dashboard.response.BugResponse;
import com.board.dashboard.response.PbiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
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

        Mono<PbiResponse> pbItemsMono = webClient.get()
                .uri(pbiServiceUrl + "/sprint/" + sprintId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PbiResponse.class)
                .doOnNext(data -> System.out.println("📦 Gelen veri: " + data))
                .doOnError(e -> System.out.println("❌ Hata: " + e.getMessage()));;

        Mono<BugResponse> bugsMono = webClient.get()
                .uri(bugServiceUrl + "/sprint/" + sprintId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(BugResponse.class)
                .doOnNext(data -> System.out.println("📦 Gelen veri: " + data))
                .doOnError(e -> System.out.println("❌ Hata: " + e.getMessage()));;

        return Mono.zip(pbItemsMono, bugsMono)
                .map(tuple -> new DashboardDto(tuple.getT1().getData(), tuple.getT2().getData()));

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
