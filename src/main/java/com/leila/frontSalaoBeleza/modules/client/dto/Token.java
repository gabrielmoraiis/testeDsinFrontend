package com.leila.frontSalaoBeleza.modules.client.dto;


import lombok.Data;

import java.util.List;

@Data
public class Token {

    private String accessToken;
    private List<String> roles;
    private Long expires_in;
}
