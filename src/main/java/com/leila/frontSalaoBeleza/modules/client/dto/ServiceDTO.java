package com.leila.frontSalaoBeleza.modules.client.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ServiceDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;
    private String duration;
}