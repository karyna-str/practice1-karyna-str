package ua.opnu.practice1_template.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.opnu.practice1_template.service.CustomUserDetailService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public JwtAuthFilter(JwtProvider jwtProvider, CustomUserDetailService customUserDetailService) {
        this.jwtProvider = jwtProvider;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if (token == null){
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtProvider.validateToken(token)){
            filterChain.doFilter(request, response);
        return;
        }

        String username = jwtProvider.getUsernameFromToken(token);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }


    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if  (bearerToken == null || !bearerToken.startsWith("Bearer "))
            return null;

        return bearerToken.substring(7);
    }
}
