package com.leila.frontSalaoBeleza.modules.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUserDTO {

    private String email;
    private UUID id;
    private String name;
    private String cellPhoneNumber;
}
