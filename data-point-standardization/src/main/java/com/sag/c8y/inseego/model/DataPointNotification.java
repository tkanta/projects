package com.sag.c8y.inseego.model;

public class DataPointNotification {
	
	private static final String CREATE_ACTION = "CREATE";
	
	private Object data;
	private String realtimeAction;
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getRealtimeAction() {
		return realtimeAction;
	}
	public void setRealtimeAction(String realtimeAction) {
		this.realtimeAction = realtimeAction;
	}
	
	public boolean isCreateNotification() {
		return CREATE_ACTION.equals(realtimeAction);
	}
}
