package com.leila.frontSalaoBeleza.modules.client.service;

import com.leila.frontSalaoBeleza.modules.client.dto.ProfileUserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ProfileClientService {

    public ProfileUserDTO execute(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

       try {
           var result = rt.exchange("http://localhost:8080/client/", HttpMethod.GET, request, ProfileUserDTO.class);
           System.out.println(result);
           return result.getBody();
       }catch (HttpClientErrorException.Unauthorized e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
       }
    }
}
