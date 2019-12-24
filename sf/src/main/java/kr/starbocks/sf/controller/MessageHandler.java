/**
 * 
 */
package kr.starbocks.sf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * @author Playdata
 *
 */
@Component
public class MessageHandler extends TextWebSocketHandler {
	public final static String TAGET_ALL ="all";
	
	private HashMap<String, WebSocketSession> wsSessions = new HashMap<String, WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Web Socket Connected!");
		System.out.println(session.getHandshakeHeaders().toString());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload(); // This MUST be JSessionID
		System.out.println("Incomming Message - JSessionID:" + payload);
		
		this.wsSessions.put(payload, session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		for(Map.Entry<String, WebSocketSession> entry :	wsSessions.entrySet()) {
			if(entry.getValue() == session) {
				System.out.println("Web Socket Session Removed." + session.getId());
				wsSessions.remove(entry.getKey());
				break;
			}
		}
	}
	
	public void sendMessage(Notification noti){
		if(noti == null) return;
		if(!TAGET_ALL.equals(noti.getAddressee())
			&& !this.wsSessions.containsKey(noti.getAddressee())) {
			System.out.println("There is no addressee with the given id:" + noti.getAddressee());
			return;
		}
		String msg = noti.toString();
		if(TAGET_ALL.equals(noti.getAddressee())) {
			for(WebSocketSession session : this.wsSessions.values()) {
				if(!session.isOpen()) {
					System.out.println("The WebSocketSession with the given id does not NOT open yet.");
					continue;
				}
				sendMessage(session, msg);
			}
		} else {
			WebSocketSession session = this.wsSessions.get(noti.getAddressee());
			if(!session.isOpen()) {
				System.out.println("The WebSocketSession with the given id does not NOT open yet.");
				return;
			}
			sendMessage(session, msg);
		}
	}
  
	private void sendMessage(WebSocketSession session, final String message) {
		try {
			session.sendMessage(new TextMessage(message));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}