package com.boxoffice.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.boxoffice.HostUrl.HostUrl;
import com.boxoffice.entity.BoxOfficeInfo;
import com.boxoffice.entity.WeekBO;
import com.boxoffice.entity.WeekendBO;
import com.boxoffice.httputils.HttpUtils;

public class BoxOfficeModel {

	/**
	 * 获取实时票房的数据
	 * @return 封装了BoxOfficeInfo的集合
	 */
	public List<BoxOfficeInfo> getRealtimeBoxInfo() {
		List<BoxOfficeInfo> boxOfficeInfos = new ArrayList<BoxOfficeInfo>();
		String path = HostUrl.REQUEST_URL_DAY;
		InputStream is = HttpUtils.getIs(path);
		String str = HttpUtils.getStr(is);

		try {
			JSONObject obj = new JSONObject(str);
			JSONArray result = obj.getJSONArray("result");
			for (int i = 0; i < result.length(); i++) {
				JSONObject info = result.getJSONObject(i);
				BoxOfficeInfo boxOfficeInfo = new BoxOfficeInfo();

				boxOfficeInfo.setBoxOfficeToday(info.isNull("cur") ? "" : info
						.getString("cur"));
				boxOfficeInfo.setBeOnDays(info.isNull("days") ? "" : info
						.getString("days"));
				boxOfficeInfo.setMovieName(info.isNull("name") ? "" : info
						.getString("name"));
				boxOfficeInfo.setBoxOfficeTotal(info.isNull("sum") ? "" : info
						.getString("sum"));

				boxOfficeInfos.add(boxOfficeInfo);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return boxOfficeInfos;
	}

	/**
	 * 获取周末票房的数据
	 * @return 封装了WeekendBO数据的集合
	 */
	public List<WeekendBO> getWeekendBOInfo() {
		List<WeekendBO> weekendBOs = new ArrayList<WeekendBO>();
		String path = HostUrl.REQUEST_URL_WEEKENDS;
		InputStream is = HttpUtils.getIs(path);
		String str = HttpUtils.getStr(is);
		try {
			JSONObject obj = new JSONObject(str);
			JSONArray result = obj.getJSONArray("result");
			for (int i = 0; i < result.length(); i++) {
				JSONObject info = result.getJSONObject(i);
				WeekendBO w = new WeekendBO();

				w.setName(info.isNull("name") ? "" : info.getString("name"));
				w.setWeekendPeriod(info.isNull("weekendPeriod") ? "" : info
						.getString("weekendPeriod"));
				w.setWeekendSum(info.isNull("weekendSum") ? "" : info
						.getString("weekendSum"));
				weekendBOs.add(w);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return weekendBOs;
	}
	
	/**
	 * 获取周票房数据
	 * @return 封装了周票房数据的集合
	 */
	public List<WeekBO> getWeekBOInfo() {
		List<WeekBO> weekBOs = new ArrayList<WeekBO>();
		String path = HostUrl.REQUEST_URL_WEEK;
		InputStream is = HttpUtils.getIs(path);
		String str = HttpUtils.getStr(is);
		try {
			JSONObject obj = new JSONObject(str);
			JSONArray result = obj.getJSONArray("result");
			for (int i = 0; i < result.length(); i++) {
				JSONObject info = result.getJSONObject(i);
				WeekBO w = new WeekBO();

				w.setName(info.isNull("name") ? "" : info.getString("name"));
				w.setWeekPeriod(info.isNull("weekPeriod") ? "" : info
						.getString("weekPeriod"));
				w.setWeekSum(info.isNull("weekSum") ? "" : info
						.getString("weekSum"));
				weekBOs.add(w);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return weekBOs;
	}
	
}
