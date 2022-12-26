package com.ReadIsGood.api.security;

import com.ReadIsGood.api.model.enity.customer.CustomerLogin;
import com.ReadIsGood.api.service.customer.CustomerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    @Autowired
    private final CustomerService customerService;

    public JwtUtils(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String generateToken(LoginRequest loginRequest){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+86400000);
        Optional<CustomerLogin> customerLoginOptional = customerService.getCustomerLogin(loginRequest.getEmail());
        if (customerLoginOptional.isEmpty()) throw new IllegalArgumentException("incorrect email or password");
        Claims claims = Jwts.claims().setSubject(loginRequest.getEmail()).setIssuedAt(now).setExpiration(expiryDate);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"xKq2RC5+PmwDY8VK6j+xjI1QSgJy3iO8G/PYffQmLiE=")
                .compact();
    }
}
