package com.leila.frontSalaoBeleza.modules.client.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class CancelAppointment {

    public void execute(String token, UUID appointmentId) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<UUID> request = new HttpEntity<>(appointmentId, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/client/cancel")
                .queryParam("appointmentId", appointmentId);

         rt.postForObject(builder.toUriString(), request, String.class);
        return;
    }
}
