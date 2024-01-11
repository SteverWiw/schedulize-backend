package com.ajsoftware.backendjwtauth.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    //TODO: se debe validar el proyecto sin esta funcionalidad
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/*").setAllowedOrigins("**");
    }

    @Bean
    public WebSocketHandler myHandler() {
        log.info("<--------------inicia configuracion de WEBSOCKET --------------->");
        return new MyHandler();
    }
}

