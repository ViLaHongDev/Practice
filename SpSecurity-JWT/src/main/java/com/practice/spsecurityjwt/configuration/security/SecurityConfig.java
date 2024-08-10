package com.practice.spsecurityjwt.configuration.security;

import com.practice.spsecurityjwt.configuration.jwt.JWTAuthenticationFilter;
import com.practice.spsecurityjwt.configuration.jwt.JwtAuthEntryPoint;
import jakarta.servlet.FilterChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailService userDetailService;

    private final JwtAuthEntryPoint authEntryPoint;

    public SecurityConfig(CustomUserDetailService userDetailService,JwtAuthEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
        this.userDetailService = userDetailService;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        request -> {
                            request.requestMatchers("/api/articles").permitAll();
                            request.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll();
                            request.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }
    @Bean
    public JwtAuthEntryPoint authEntryPoint(){
        return new JwtAuthEntryPoint();
    }
}
