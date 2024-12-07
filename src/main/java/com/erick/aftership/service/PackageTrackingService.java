package com.erick.aftership.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PackageTrackingService {

    @Value("${postal.ninja.api.key}") // Asegúrate de añadir tu clave en application.properties
    private String apiKey;

    private final RestTemplate restTemplate;

    public PackageTrackingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String trackPackage(String trackingNumber) {
        String url = "https://api.postal.ninja/v1/track?api_key=" + apiKey + "&tracking_number=" + trackingNumber;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
