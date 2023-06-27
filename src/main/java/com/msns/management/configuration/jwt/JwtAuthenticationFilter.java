package com.msns.management.configuration.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.msns.management.utils.UserDetailService;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailService userDetails;

    @Autowired
    private JwtUtil jwtUtil;

    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String RequestTokenHeader = request.getHeader("Authorization");

        log.info("Request Type: {}, Request URI: {}", request.getMethod(), request.getRequestURI());

        String email = null;
        String jwttoken = null;

        if (RequestTokenHeader != null && RequestTokenHeader.startsWith("Bearer ")) {
            jwttoken = RequestTokenHeader.substring(7);
            try {
                email = this.jwtUtil.extractUsername(jwttoken);

            } catch (Exception e) {
                log.error("Cannot extract username from token or expired token.");

            }

        } else {
            log.error("Invalid token, not start with bearer string");
        }

        // * validate token
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetail = this.userDetails.loadUserByUsername(email);

            if (this.jwtUtil.validateToken(jwttoken, userDetail)) {

                UsernamePasswordAuthenticationToken uPAT = new UsernamePasswordAuthenticationToken(userDetail, null,
                        userDetail.getAuthorities());
                uPAT.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(uPAT);
            }
        } else {
            log.error("Token validation error");
        }

        filterChain.doFilter(request, response);

    }

}
