/**
 * 
 */
package kr.starbocks.rapms.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author Playdata
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer  {
	
	@Autowired
	private MessageHandler handler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(handler, "/ws/noti").setAllowedOrigins("*");
	}
}
