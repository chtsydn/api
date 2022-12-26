package com.ReadIsGood.api.service.customer;

import com.ReadIsGood.api.security.JwtUtils;
import com.ReadIsGood.api.security.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private final JwtUtils jwtUtils;
    @Autowired
    private final CustomerService customerService;

    public LoginService(JwtUtils jwtUtils, CustomerService customerService) {
        this.jwtUtils = jwtUtils;
        this.customerService = customerService;
    }

    public String login(LoginRequest loginRequest){
        Boolean bool = customerService.login(loginRequest);
        if (bool){
            return jwtUtils.generateToken(loginRequest);
        }else {
            throw new IllegalArgumentException("incorrect email or password");
        }
    }
}
