package com.example.library.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegistryClient {

    private final RestTemplate restTemplate;

    public RegistryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String fetchCover(Long bookId) {
        return restTemplate.getForObject(
                "https://mock-registry/books/" + bookId + "/cover",
                String.class
        );
    }
}
