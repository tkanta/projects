package com.sag.c8y.inseego.websocket;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class InseegoClientEndPoint {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InseegoClientEndPoint.class);
	
	CountDownLatch latch = new CountDownLatch(1);
	private Session userSession = null;
	private IMessageHandler messageHandler;
	
	
	public InseegoClientEndPoint(URI uri) {
		
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, uri);
		}catch(Exception ex) {
			
			LOGGER.error("Inseego Websocket container error :: {} ", ex.getMessage());
		}
		
	}
	
	
	/**
	 * Callback hook for connection open event
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.userSession = session;
		latch.countDown();
	}
	
	
	/**
	 * Callback hook for connection close event
	 * @param session
	 * @param reason
	 */
	@OnClose
	public void onClose(Session session, CloseReason reason) {
		this.userSession = null;
		LOGGER.info("Session is closing because of :: {}", reason.getReasonPhrase());
	}
	
	
	/**
	 * Callback hook for message event
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message) {
		if(this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}
	
	
	/**
	 * Register Message Handler
	 * @param handler
	 */
	public void addMessageHandler(IMessageHandler handler) {
		this.messageHandler = handler;
	}
	
	
	/**
	 * Send message
	 * @param message
	 */
	public void sendMessage(String message) {
		//this.userSession.getAsyncRemote().sendText(message);
		try {
			this.userSession.getBasicRemote().sendText(message);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	
	public void handShake(String message) {
		this.sendMessage(message);
	}
	
	public void subscribe(String message) {
		this.sendMessage(message);
	}
	
	public void connect(String message) {
		this.sendMessage(message);
	}


	public CountDownLatch getLatch() {
		return latch;
	}
	
	
}
