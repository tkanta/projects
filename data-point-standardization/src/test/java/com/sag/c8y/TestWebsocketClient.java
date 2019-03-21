package com.sag.c8y;

import java.net.URI;

import com.sag.c8y.inseego.websocket.IMessageHandler;
import com.sag.c8y.inseego.websocket.InseegoClientEndPoint;
import com.sag.c8y.inseego.websocket.MessageHandler;

public class TestWebsocketClient {
	
	static String handshakeMsgReq = "[{\"channel\":\"/meta/handshake\",\"ext\":{\"com.cumulocity.authn\":{\"token\":\"dDc5NDY3ODkzL2FtemFkYmFzaGEuc2hhaWtAaXNwYWNlLmNvbTpKYW51YXJ5IzIwMTk=\"}},\"version\":\"1.0\",\"mininumVersion\":\"1.0beta\",\"supportedConnectionTypes\":[\"websocket\",\"long-polling\"],\"advice\":{\"timeout\":120000,\"interval\":30000}}]";
	String handShakeResponse = "[{\"ext\":{\"ack\":true},\"minimumVersion\":\"1.0\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"supportedConnectionTypes\":[\"long-polling\",\"smartrest-long-polling\",\"websocket\"],\"channel\":\"/meta/handshake\",\"version\":\"1.0\",\"successful\":true}]";
	static String subscribeMsgReq = "[{\"channel\":\"/meta/subscribe\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"subscription\":\"/managedobjects/*\"}]";
	static String connectMsgReq = "[{\"channel\":\"/meta/connect\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"connectionType\":\"websocket\",\"advice\":{\"timeout\":1200000,\"interval\":30000}}]";
	
	public static void main(String[] args) {
		
		try {
			
			InseegoClientEndPoint endpoint = new InseegoClientEndPoint(new URI("wss://boydmachtolff.cumulocity.com/cep/realtime"));
			IMessageHandler handler = new MessageHandler(endpoint);
			endpoint.addMessageHandler(handler);
			
			endpoint.getLatch().await();
			//handshake
			endpoint.handShake(handshakeMsgReq);
			//Thread.sleep(20000l);
			while (true) {
			    Thread.sleep(Long.MAX_VALUE);
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}

}
