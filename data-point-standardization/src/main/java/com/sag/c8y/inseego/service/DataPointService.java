package com.sag.c8y.inseego.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cumulocity.sdk.client.notification.Subscription;
import com.cumulocity.sdk.client.notification.SubscriptionListener;
import com.sag.c8y.inseego.model.DataPointNotification;

public class DataPointService implements SubscriptionListener<String, DataPointNotification>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataPointService.class);
	
	@Override
	public void onError(Subscription<String> arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNotification(Subscription<String> arg0, DataPointNotification arg1) {
		// TODO Auto-generated method stub
		
	}
	
	private void onNotificationWithContext(DataPointNotification notification) {
		
		
		
		LOGGER.info("Reveived and manageObject notification with realtime Action {}", notification.getRealtimeAction());
		
		if(notification.isCreateNotification()) {
		
			if(notification.getData() instanceof HashMap<?,?>) {
				
				 HashMap<?,?> notificationDataMap = (HashMap<?,?>)notification.getData();
				 
				 if(notificationDataMap.containsKey("c8y_IsDevice")) {
					 long id = (long) notificationDataMap.get("id");
				 }
				 
			}
		}
		
		
	}
	
	private void getManageObjectFromInventory() {
		
	}
}

	
