package com.example.chatApp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


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
		registry.addEndpoint("/websocket").withSockJS();
	}

	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 受信エンドポイントの設定（受け取り口の設定定義）
		registry.enableSimpleBroker("/topics");
		// 送信エンドポイントの設定（送付先のプレフィックスを定義）
        registry.setApplicationDestinationPrefixes("/send");
	}
	
	@Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(1024 * 1024); // default : 64 * 1024
        registration.setSendTimeLimit(20 * 10000); // default : 10 * 10000
        registration.setSendBufferSizeLimit(3* 512 * 1024); // default : 512 * 1024

    }
}
