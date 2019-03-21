package com.sag.c8y;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

public class TestJsonClient {

	public static void main(String[] args) {
		String handshakeMsgReq = "[{\"channel\":\"/meta/handshake\",\"ext\":{\"com.cumulocity.authn\":{\"token\":\"dDc5NDY3ODkzL2FtemFkYmFzaGEuc2hhaWtAaXNwYWNlLmNvbTpKYW51YXJ5IzIwMTk=\"}},\"version\":\"1.0\",\"mininumVersion\":\"1.0beta\",\"supportedConnectionTypes\":[\"websocket\",\"long-polling\"],\"advice\":{\"timeout\":120000,\"interval\":30000}}]";
		String handShakeResponse = "[{\"ext\":{\"ack\":true},\"minimumVersion\":\"1.0\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"supportedConnectionTypes\":[\"long-polling\",\"smartrest-long-polling\",\"websocket\"],\"channel\":\"/meta/handshake\",\"version\":\"1.0\",\"successful\":true}]";
		String subscribeMsgReq = "[{\"channel\":\"/meta/subscribe\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"subscription\":\"/managedobjects/*\"}]";
		String connectMsgReq = "[{\"channel\":\"/meta/connect\",\"clientId\":\"cf2ln1cjhlzh5mguw4tjv3qm052\",\"connectionType\":\"websocket\",\"advice\":{\"timeout\":1200000,\"interval\":30000}}]";
		String clientId = "123";
		
		try {
			
			
			//---------- update json object and create an array ------------
			JsonArray messageReceived =  Json.createReader(new StringReader(subscribeMsgReq)).readArray();
			JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(messageReceived.getJsonObject(0)).add("clientId", clientId);
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder().add(jsonObjectBuilder);
			System.out.println(arrayBuilder.build().toString());
			
			//---- client Id test -----------
			/*JsonArray messageReceived =  Json.createReader(new StringReader(handShakeResponse)).readArray();
			System.out.println(messageReceived.getJsonObject(0).get("clientId"));*/
			
			//------- channel test ---------
			/*JsonArray messageReceived =  Json.createReader(new StringReader(handshakeMsgReq)).readArray();
			System.out.println(messageReceived.getJsonObject(0).get("channel").toString());*/
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
