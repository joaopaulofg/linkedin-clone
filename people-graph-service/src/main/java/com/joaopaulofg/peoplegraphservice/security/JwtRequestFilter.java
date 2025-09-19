package com.joaopaulofg.peoplegraphservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        // libera endpoints públicos
        if (path.startsWith("/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Tenta pegar o token do header ou cookie
        String jwtToken = null;

        // 1️⃣ Verifica se o token está no cookie "token"
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }
        }

        // 2️⃣ Se não tiver cookie, verifica o header Authorization
        if (jwtToken == null) {
            String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
            }
        }

        if (jwtToken != null && jwtUtil.validateToken(jwtToken)) {
            // Extrai dados do token
            String username = jwtUtil.extractUsername(jwtToken);
            Long userId = jwtUtil.extractUserId(jwtToken);

            // Cria a autenticação sem UserDetails
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
