    package com.leila.frontSalaoBeleza.modules.client.service;

    import com.leila.frontSalaoBeleza.modules.client.dto.AppointmentDTO;
    import com.leila.frontSalaoBeleza.modules.client.dto.AppointmentResponseDTO;
    import com.leila.frontSalaoBeleza.modules.client.dto.ServiceDTO;
    import com.leila.frontSalaoBeleza.modules.client.dto.Token;
    import org.springframework.http.*;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.HttpClientErrorException;
    import org.springframework.web.client.RestTemplate;

    import java.util.Arrays;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Service
    public class ClientService {

        public Token login(String email, String password){
            RestTemplate rt = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> data = new HashMap<>();
            data.put("email", email);
            data.put("password", password);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

            var result = rt.postForObject("http://localhost:8080/client/auth", request, Token.class);
            return result;
        }

        public List<ServiceDTO> getServices(String token) {
            RestTemplate rt = new RestTemplate();

            String url = "http://localhost:8080/client/services";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<?> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<ServiceDTO[]> response = rt.exchange(url, HttpMethod.GET, requestEntity, ServiceDTO[].class);

            return Arrays.asList(response.getBody());
        }

        public AppointmentResponseDTO scheduleAppointment(String token, AppointmentDTO appointmentDTO) {
            RestTemplate rt = new RestTemplate();
            String url = "http://localhost:8080/client/appointment/new";

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AppointmentDTO> requestEntity = new HttpEntity<>(appointmentDTO, headers);

            try {
                ResponseEntity<AppointmentResponseDTO> response = rt.exchange(url, HttpMethod.POST, requestEntity, AppointmentResponseDTO.class);

                if (response.getStatusCode().is2xxSuccessful()) {
                    AppointmentResponseDTO appointmentResponse = response.getBody();
                    System.out.println("Agendamento realizado com sucesso: " + appointmentResponse);
                    return appointmentResponse;
                } else {
                    System.out.println("Falha ao agendar serviço: " + response.getStatusCode());
                    return null;
                }
            } catch (HttpClientErrorException.Unauthorized e) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao agendar o serviço: " + e.getMessage());
            }
        }

    }
