package com.sag.c8y.inseego.service.c8y;

import com.google.common.base.Optional;

public interface IInventoryService {
	
	public Optional<String> getManageObject(String deviceId, String url, String authStr);
	public boolean isFragmentSeriesExist(String fragment, String Series, String tenantUrl, String authStr);
	public Optional<Boolean> saveKPI(String kpi, String tenantUrl, String authStr);
}
