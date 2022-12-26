package com.ReadIsGood.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests().requestMatchers("/api/customer/createCustomer").permitAll()
                .and().authorizeHttpRequests().requestMatchers("/api/login").permitAll()
                .and().authorizeHttpRequests().anyRequest().authenticated()
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.httpBasic();
        return httpSecurity.build();
    }
}
