package com.boxoffice.application;

import com.baidu.mapapi.SDKInitializer;

import android.app.Application;

public class MApplication extends Application{

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
}
