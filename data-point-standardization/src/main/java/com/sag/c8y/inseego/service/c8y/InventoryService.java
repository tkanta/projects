package com.sag.c8y.inseego.service.c8y;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.sag.c8y.inseego.service.DataPointService;
import com.sag.c8y.inseego.util.C8yUtil;
import com.sag.c8y.inseego.util.RestTemplateUtil;

@Service
public class InventoryService implements IInventoryService{

	private static final Logger LOGGER = LoggerFactory.getLogger(DataPointService.class);

	@Override
	public Optional<String> getManageObject(String deviceId, String tenantUrl, String authStr) {

		String deviceUrl = C8yUtil.buildC8yUrl(tenantUrl, "inventory/managedObjects", deviceId);
		LOGGER.info("Device url {}", deviceUrl);
		return Optional.fromNullable(RestTemplateUtil.get(deviceUrl, authStr));
	}

	@Override
	public boolean isFragmentSeriesExist(String fragment, String series, String tenantUrl, String authStr) {
		String queryString = "?query=$filter=c8y_Kpi.fragment eq "+fragment+" and c8y_Kpi.series eq "+series;
		String filterUrl = C8yUtil.buildC8yUrl(tenantUrl, "inventory/managedObjects", queryString);
		LOGGER.info("Filter URL {}", filterUrl);
		String response = RestTemplateUtil.get(filterUrl, authStr);
		boolean isPresent = false;
		if(null != response && !response.isEmpty()) {
			JsonObject jsonObject = Json.createReader(new StringReader(response)).readObject();
			isPresent = !jsonObject.get("managedObjects").asJsonArray().isEmpty();
		}
		LOGGER.info("Is Fragment Exist response {}",response);
		return isPresent;
	}

	@Override
	public Optional<Boolean> saveKPI(String kpi, String tenantUrl, String authStr) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
