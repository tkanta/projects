package com.sag.c8y.inseego.util;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class JsonUtil {

	public static String buildKPIJson(String fragment, String series) {
		String jsonKPI = "{\"c8y_Kpi\":{\"redRangeMax\":\"\",\"fragment\":\"\",\"unit\":\"\",\"color\":\"\",\"series\":\"\",\"label\":\"\",\"yellowRangeMax\":\"\",\"yellowRangeMin\":\"\",\"target\":\"\",\"redRangeMin\":\"\"}}";
		JsonObject object = Json.createReader(new StringReader(jsonKPI)).readObject();
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder(object.get("c8y_Kpi").asJsonObject())
				.add("fragment", fragment).add("series", series).add("label", series);

		return jsonObjectBuilder.build().toString();
	}
}
