package com.leila.frontSalaoBeleza.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity()
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/client/login").permitAll()
                            .requestMatchers("/client/signIn").permitAll();
            auth.anyRequest().authenticated();
        })
        .formLogin(form -> form.loginPage("/client/login"));
        return http.build();
    }
}
