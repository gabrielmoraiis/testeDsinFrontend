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
public class AppointmentResponseDTO {
    private UUID id;
    private UUID clientId;
    private String clientName;
    private String clientEmail;
    private String clientCellPhoneNumber;
    private LocalDateTime clientCreatedAt;

    private UUID serviceId;
    private String serviceName;
    private String serviceDescription;
    private BigDecimal servicePrice;
    private String serviceDuration;

    private LocalDateTime appointmentDate;
    private String status;
    private LocalDateTime createdAt;
}