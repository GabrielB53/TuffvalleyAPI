package com.itb.inf2cm.sgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors()  // ativa suporte ao CORS configurado no WebMvcConfigurer
          .and()
          .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
          .csrf().disable();

        return http.build();
    }
}