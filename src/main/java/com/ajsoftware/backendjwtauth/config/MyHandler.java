package com.ajsoftware.backendjwtauth.config;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyHandler extends TextWebSocketHandler {
    //TODO: se debe validar el proyecto sin esta funcionalidad
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
    }
}
