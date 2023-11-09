package fico.crm.configuration.security;

import fico.crm.configuration.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class JwtApiSecurityConfig {
    private final NotAuthEntryPoint notAuthEntryPoint;
    private final JwtUtil jwtUtil;
    public JwtApiSecurityConfig (NotAuthEntryPoint notAuthEntryPoint, JwtUtil jwtUtil){
        this.notAuthEntryPoint = notAuthEntryPoint;
        this.jwtUtil = jwtUtil;
    }
    @Bean
    public JwtAuthFilter authJwtTokenFilter() {
        return new JwtAuthFilter(jwtUtil);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.disable())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(notAuthEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/openapi/**").permitAll()
                        //.requestMatchers("/process/**").permitAll()
                        //.requestMatchers("").permitAll()
                        .anyRequest().authenticated()
                );

        http.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
