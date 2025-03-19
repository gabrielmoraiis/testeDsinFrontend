package com.leila.frontSalaoBeleza.modules.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private UUID id;
    private LocalDateTime appointmentDate;
    private String status;
    private BigDecimal price;
    private String name;
    private LocalDateTime createdAt;
    private ServiceDTO service;
}
