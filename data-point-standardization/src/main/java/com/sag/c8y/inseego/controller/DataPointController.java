package com.sag.c8y.inseego.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import com.cumulocity.microservice.context.ContextService;
import com.cumulocity.microservice.context.credentials.MicroserviceCredentials;
import com.cumulocity.microservice.subscription.model.MicroserviceSubscriptionAddedEvent;
import com.cumulocity.microservice.subscription.service.MicroserviceSubscriptionsService;
import com.sag.c8y.inseego.util.C8yUtil;

public class DataPointController {
	
	static final Logger LOGGER = LoggerFactory.getLogger(DataPointController.class);
	
	@Autowired
	private MicroserviceSubscriptionsService subscription;
	
	@Autowired
	private ContextService<MicroserviceCredentials> contextService;
	
	private MicroserviceCredentials credential;
	
	@EventListener
	private void onMicroServiceSubscriptionAddedEvent(final MicroserviceSubscriptionAddedEvent event) {
		credential = contextService.getContext();
		subscription.runForTenant(event.getCredentials().getTenant(), () -> {
			String encodedTokenStr = C8yUtil.getTenantEncodedToken(event.getCredentials().getTenant(),
					event.getCredentials().getUsername(), event.getCredentials().getPassword());
			String url = C8yUtil.getTenantUrl(event.getCredentials().getTenant());
			
		});
	}
}
