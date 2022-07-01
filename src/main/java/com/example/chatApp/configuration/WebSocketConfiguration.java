package com.example.chatApp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


/**
 * WebSocketのためのconfiguration
 * 
 * @author ootomokenji
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// クライアント側から最初にWebSocketを繋ぐ際のつなぎ先（エンドポイント）を定義、SockJSを使うことも一緒に宣言
		registry.addEndpoint("/gs-guide-websocket").withSockJS();
	}

	// 
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 受け取り口の設定定義
		registry.enableSimpleBroker("/topic");
		// 送付先のプレフィックスを定義
        registry.setApplicationDestinationPrefixes("/chat");
	}
	
	
}
