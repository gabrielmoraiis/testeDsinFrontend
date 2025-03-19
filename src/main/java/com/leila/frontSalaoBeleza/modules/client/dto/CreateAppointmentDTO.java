package com.leila.frontSalaoBeleza.modules.client.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CreateAppointmentDTO {
    private LocalDateTime appointmentDate;
    private UUID serviceId;
    private UUID clientId;
    private String status;
    private UUID id;
    private LocalDateTime createdAt;
    private ServiceDTO service;
}
