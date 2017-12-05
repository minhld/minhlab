package com.usu.minhlab.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.client.MongoCursor;

public class JSonUtils {
	public static String print(MongoCursor<Event> events) {
		JSONArray mainObj = new JSONArray();
		
		Event e;
		JSONObject jo;
		while (events.hasNext()) {
			e = events.next();
			jo = new JSONObject();
			jo.put("time", e.time).put("type", e.type).put("info", e.info);
			mainObj.put(jo);
		}
		
		return mainObj.toString();
	}
}
