package com.andrew.apollo.utils.customwidget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

public class Utils {
	public static final String W_SKIN = "widget_skin";
	public static final String DEF_SKIN = Environment.getExternalStorageDirectory() + "/ApolloMusic/widget/default";
	public static final String W_MASK = "/widget_mask.png";
	public static final String W_COVER = "/widget_cover.png";
	public static final String W_PLAY = "/widget_play.png";
	public static final String W_PAUSE = "/widget_pause.png";
	public static final String W_NEXT = "/widget_next.png";
	public static final String W_PREV = "/widget_previous.png";
	public static final String W_BG = "/widget_control_bg.png";
	
	
	public static WData getSkinInfo(String folder) {
		WData data = new WData();
		JSONObject obj = JsonParser.get(folder + "/description");
		try {
			JSONArray arai = obj.getJSONArray(WData._HEADER);
			for (int i = 0; i < arai.length(); i++) {
				JSONObject o = arai.getJSONObject(i);
				data.setAuthor(o.getString(WData._AUTHOR));
				data.setTitle(o.getString(WData._TITS));
				data.setVersion(o.getString(WData._VERSION));
				data.setTitlecolor(Color.parseColor(o.getString(WData._COLOR_T)));
				data.setArtistcolor(Color.parseColor(o
						.getString(WData._COLOR_A)));
				data.setUkuran(o.getInt(WData._SIZE));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.e("XXXXXXXXXXXXXX", data.toString());
		return data;

	}

}
