package com.leila.frontSalaoBeleza.modules.client.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class NewAppointment {

    public Object execute(String token, String appoimentDate, UUID serviceId) {
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        System.out.println(appoimentDate +" " + serviceId);
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("serviceId", serviceId.toString());
        data.add("appointmentDate", appoimentDate);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

        var result = rt.postForObject("http://localhost:8080/client/appointment/new", request, Object.class);
        System.out.println(result);
        return result;
    }
}
