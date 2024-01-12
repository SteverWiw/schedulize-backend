package com.ajsoftware.backendjwtauth.config;

import com.ajsoftware.backendjwtauth.jwt.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Value("${application.request.mappings}")
    private String url_base;
    @Value("${application.request.origin}")
    private String origin;
    //TODO: se deja en comentario luego se probara esta funcionalidad sin usar la clase independiente
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        log.info("<--------------inicia configuracion de CORS --------------->");
        CorsConfiguration cc = new CorsConfiguration();
        cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type","Access-Control-Request-Method", "Access-Control-Request-Headers","Authorization"));
        cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        cc.setAllowedOrigins(Arrays.asList("/*"));
        cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT","PATCH"));
        cc.addAllowedOrigin(origin);
        cc.setMaxAge(Duration.ZERO);
        cc.setAllowCredentials(Boolean.TRUE);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cc);
        return source;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("<--------------inicia configuracion de SECURITY --------------->");
        return http.cors(withDefaults()).
                csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorize -> authorize
                .requestMatchers(url_base + "/v1/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated())
                .sessionManagement(SessionManager ->
                        SessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
