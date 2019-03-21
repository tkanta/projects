package com.sag.c8y.inseego.websocket;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.sag.c8y.inseego.util.IConstants;

public class MessageHandler implements IMessageHandler {
	
	private String clientId = IConstants.EMPTY_STRING;
	private String handShakeSuccessFul = IConstants.EMPTY_STRING;
	private String subscribeSuccessFul = IConstants.EMPTY_STRING;
	static String subscribeMsgReq = "[{\"channel\":\"/meta/subscribe\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"subscription\":\"/managedobjects/*\"}]";
	static String connectMsgReq = "[{\"channel\":\"/meta/connect\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"connectionType\":\"websocket\",\"advice\":{\"timeout\":1200000,\"interval\":30000}}]";
	
	private InseegoClientEndPoint endpoint;
	
	public MessageHandler(InseegoClientEndPoint endpoint) {
		this.endpoint = endpoint;
	}
	
	@Override
	public void handleMessage(String message) {
		// TODO Auto-generated method stub
		JsonArray messageArrayReceived =  Json.createReader(new StringReader(message)).readArray();
		
		String channel = messageArrayReceived.getJsonObject(0).get("channel").toString().replaceAll("^\"|\"$", "");
		System.out.println("Channel : "+ channel);
		
		
		if(IConstants.META_HANDSHAKE.equalsIgnoreCase(channel)) {
			
			System.out.println("handshake response : " + message);
			clientId =  messageArrayReceived.getJsonObject(0).get("clientId").toString().replaceAll("^\"|\"$", "");
			System.out.println("Client ID : " + clientId);
			handShakeSuccessFul = messageArrayReceived.getJsonObject(0).get("successful").toString();
			
			if(IConstants.TRUE.equalsIgnoreCase(handShakeSuccessFul)) {
				String updatedSubscriberReqMsg = updateMessageWithClientId(subscribeMsgReq, clientId);
				System.out.println("updatedSubscriberReqMsg :: "+ updatedSubscriberReqMsg);
				this.endpoint.subscribe(updatedSubscriberReqMsg);
			}else {
				System.out.println("handshake successful : " + handShakeSuccessFul);
			}
			
			
		}else if(IConstants.META_SUBSCRIBE.equalsIgnoreCase(channel)) {
			
			System.out.println("subscribe Response : " + message);
			subscribeSuccessFul = messageArrayReceived.getJsonObject(0).get("successful").toString();
			
			if(IConstants.TRUE.equalsIgnoreCase(subscribeSuccessFul)) {
				String updatedConnectReqMsg = updateMessageWithClientId(connectMsgReq, clientId);
				endpoint.connect(updatedConnectReqMsg);
			}else {
				System.out.println("subscribtion successful : " + subscribeSuccessFul);
			}
			
			
			
		}else {
			
			System.out.println("connect : " + message);
		}
		
	}

	public String getClientId() {
		return clientId;
	}

	public String getHandShakeSuccessFul() {
		return handShakeSuccessFul;
	}

	public String getSubscribeSuccessFul() {
		return subscribeSuccessFul;
	}
	
	private static String updateMessageWithClientId(String message, String clientId) {
		JsonArray messageReceived =  Json.createReader(new StringReader(message)).readArray();
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(messageReceived.getJsonObject(0)).add("clientId", clientId);
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder().add(jsonObjectBuilder);
		String updatedSubscriberReqMsg = arrayBuilder.build().toString();
		return updatedSubscriberReqMsg;
	}
	
}
