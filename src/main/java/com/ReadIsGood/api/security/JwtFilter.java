package com.ReadIsGood.api.security;

import com.sun.security.auth.UserPrincipal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {
    private String email;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (jwt != null && isTokenValid(jwt)){
            Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("customer"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(new UserPrincipal(email),null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        filterChain.doFilter(request,response);
    }

    private Boolean isTokenValid(String jwt){
        try{
            boolean bool = Jwts.parser().isSigned(jwt);
            String key = "xKq2RC5+PmwDY8VK6j+xjI1QSgJy3iO8G/PYffQmLiE=";
            Object parsedToken = Jwts.parser().setSigningKey(key).parse(jwt).getBody();
            email = ((DefaultClaims) parsedToken).getSubject();
            Date expirationDate = ((DefaultClaims) parsedToken).getExpiration();
            Date now = new Date();
            bool = bool && now.compareTo(expirationDate)<0;
            return bool;
        }catch (Exception e){
            return false;
        }
    }

    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        if ("GET".equals(request.getMethod())){
            bearerToken = request.getParameter("auth");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
                return bearerToken.substring(7);
            }
        }
        return null;
    }
}
