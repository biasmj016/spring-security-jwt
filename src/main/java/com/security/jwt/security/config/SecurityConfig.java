package com.security.jwt.security.config;

import com.security.jwt.security.filter.JwtTokenFilter;
import com.security.jwt.security.service.AccessUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AccessUserService userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/register").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtTokenFilter(userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                );
        return http.build();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter(AccessUserService userDetailsService) {
        return new JwtTokenFilter(jwtTokenProvider, userDetailsService);
    }

}