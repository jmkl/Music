package com.andrew.apollo.utils.customwidget;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	public static JSONObject get(String url) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(url));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			json = sb.toString();

		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}