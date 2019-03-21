package com.sag.c8y.inseego.strategy;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import com.sag.c8y.inseego.util.JsonUtil;

public class KPIUpdateSolution implements IDataPointStrategy{
	
	private static final List<String> fragmentList = new ArrayList<>(Arrays.asList("c8y_IsDevice", "c8y_Availability",  "c8y_Hardware", "c8y_Mobile", 
            								"c8y_Position", "c8y_ActiveAlarmsStatus", "c8y_RequiredAvailability", 
            								"c8y_Connection", "c8y_ComponentConfig", "c8y_Configuration", "c8y_SupportedLogs"));
	
	public void solve(String manageObject) {
		//filter fragments
		//create new KPI
		//check if KPI exist
		//persist KPI
		List<String> list = getFilteredKPI(manageObject);
		list.forEach(action -> System.out.println(action));
	}
	
	/**
	 * Create KPI from filtered fragment and series
	 * @param manageObject
	 * @return
	 */
	private List<String> getFilteredKPI(String manageObject) {
		
		List<String> list = new ArrayList<>();
		JsonObject jsonObject =  Json.createReader(new StringReader(manageObject)).readObject();
		
		jsonObject.keySet().forEach(fragment -> {
			if(fragmentList.contains(fragment)) {
				if(jsonObject.get(fragment) instanceof JsonArray) {
					jsonObject.get(fragment).asJsonArray().forEach(series -> {
						list.add(JsonUtil.buildKPIJson(fragment, series.toString().replaceAll("^\"|\"$", "")));
					});
				}else if(jsonObject.get(fragment) instanceof JsonObject) {
					jsonObject.get(fragment).asJsonObject().keySet().forEach(series -> {
						if(null != series && !series.isEmpty()) {
							list.add(JsonUtil.buildKPIJson(fragment, series));
						}
					});
				}
			}
		});;
		
		return list;
	}
	
	private boolean checkKPIExistance(String KPI) {
		
		return false;
	}
	
	private void updateKPI(String manageObject) {
		
	}
	
	
}
