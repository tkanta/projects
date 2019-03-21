package com.sag.c8y.inseego.websocket;

public interface IMessageHandler {
	
	public void handleMessage(String message);
	public String getClientId();
}
