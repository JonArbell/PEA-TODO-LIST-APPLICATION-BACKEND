package com.myapp.pea.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import java.util.Map;

@Slf4j
@RestController
public class PingController {

    private final WebClient webClient;

    public PingController(WebClient.Builder webClient){
        this.webClient = webClient.baseUrl("").build();
    }

    @GetMapping("/api/ping")
    public Map<String, String> getPing(){
        return Map.of("ping","Ping lng para hindi magkaroon ng pause sa hosting ng render. Panis.");
    }

    @Async
    @Scheduled(fixedRate = 300000) // 5 minutes
    public void ping() {

        webClient.get()
                .uri("/api/ping")
                .retrieve()
                .bodyToMono(Map.class)
                .subscribe(
                        result -> log.info("Ping successful, Result: {}", result),
                        error -> {
                            if (error instanceof WebClientResponseException webClientException) {
                                log.error("Ping failed with status {}: {}", webClientException.getStatusCode(), webClientException.getMessage());
                            } else {
                                log.error("Exception Error: {}", error.getMessage());
                            }
                        }
                );
    }

}
