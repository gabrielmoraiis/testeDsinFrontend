package com.leila.frontSalaoBeleza.modules.client.service;

import com.leila.frontSalaoBeleza.modules.client.dto.AppointmentDTO;
import com.leila.frontSalaoBeleza.modules.client.dto.ProfileUserDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class FindAppointmentsByPeriodService {

    public List<AppointmentDTO> execute(String token, String startDate, String endDate) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/client/appointment/period")
                .queryParam("startDate", startDate).queryParam("endDate", endDate);
        ParameterizedTypeReference<List<AppointmentDTO>> responseType = new ParameterizedTypeReference<List<AppointmentDTO>>() {};

        try {
            var result = rt.exchange(builder.toUriString(), HttpMethod.GET, request, responseType);
            System.out.println(result);
            return result.getBody();
        }catch (HttpClientErrorException.Unauthorized e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

    }
}
