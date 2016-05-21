package com.boxoffice.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import com.boxoffice.R;
import com.boxoffice.adapter.RealTimeBOAdadapter;
import com.boxoffice.adapter.WeekBOAdadapter;
import com.boxoffice.adapter.WeekendsBOAdadapter;
import com.boxoffice.entity.BoxOfficeInfo;
import com.boxoffice.entity.WeekBO;
import com.boxoffice.entity.WeekendBO;
import com.boxoffice.model.BoxOfficeModel;

public class BoxOfficeInfoFragment extends Fragment {

	private List<BoxOfficeInfo> boxOfficeInfos = new ArrayList<BoxOfficeInfo>();
	private List<WeekendBO> weekendBOs = new ArrayList<WeekendBO>();
	private List<WeekBO> weekBOs = new ArrayList<WeekBO>();
	private RadioGroup rg;
	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;
	private BoxOfficeModel model;
	private RealTimeBOAdadapter realTimeBOAdadapter;
	private WeekendsBOAdadapter weekendsBOAdadapter;
	private WeekBOAdadapter weekBOAdadapter;
	private ListView lvBoxoffice;
	
	private RelativeLayout rlLoadimg;
	private ImageView ivLoadImg;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				rlLoadimg.setVisibility(View.INVISIBLE);
				List<BoxOfficeInfo> list = (List<BoxOfficeInfo>) msg.obj;
				realTimeBOAdadapter = new RealTimeBOAdadapter(getActivity(),
						list);
				lvBoxoffice.setAdapter(realTimeBOAdadapter);
				break;
			case 1:
				rlLoadimg.setVisibility(View.INVISIBLE);
				List<WeekendBO> list1 = (List<WeekendBO>) msg.obj;
				weekendsBOAdadapter = new WeekendsBOAdadapter(getActivity(),
						list1);
				lvBoxoffice.setAdapter(weekendsBOAdadapter);
				break;
			case 2:
				rlLoadimg.setVisibility(View.INVISIBLE);
				List<WeekBO> list2 = (List<WeekBO>) msg.obj;
				weekBOAdadapter = new WeekBOAdadapter(getActivity(), list2);
				lvBoxoffice.setAdapter(weekBOAdadapter);
			}

		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment, null);

		init(v);
		
		setAnimation();
		
		showRealtimeBO();
		Log.i("han", "直接加载数据");
		setListener();

		return v;

	}

	private void init(View v) {
		rg = (RadioGroup) v.findViewById(R.id.rg_boxoffice_fragment1);
		rb1 = (RadioButton) v.findViewById(R.id.rb_realtime_box);
		rb2 = (RadioButton) v.findViewById(R.id.rb_weekend_box);
		rb3 = (RadioButton) v.findViewById(R.id.rb_overweek_box);
		model = new BoxOfficeModel();
		lvBoxoffice = (ListView) v.findViewById(R.id.lv_boxofficeinfo);
		rlLoadimg = (RelativeLayout) v.findViewById(R.id.rl_loadimg);
		ivLoadImg = (ImageView) v.findViewById(R.id.iv_loadimg);
	}

	private void setListener() {
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_realtime_box:
					setAnimation();
					Log.i("info", "点击实时票房按钮加载数据");
					showRealtimeBO();
					break;
				case R.id.rb_weekend_box:
					setAnimation();
					Log.i("info", "周末票房按钮记载数据");
					showWeekend();
					break;
				case R.id.rb_overweek_box:
					setAnimation();
					Log.i("info", "周票房数据");
					showWeek();
					break;
				}
			}
		});
	}

	/**
	 * 获取实时票房的数据
	 */
	private void showRealtimeBO() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				boxOfficeInfos = model.getRealtimeBoxInfo();
				Message msg = handler.obtainMessage(0, boxOfficeInfos);
				handler.sendMessage(msg);
			}
		}).start();
	}

	/**
	 * 获取周末票房的数据
	 */
	private void showWeekend() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				weekendBOs = model.getWeekendBOInfo();
				Message msg = handler.obtainMessage(1, weekendBOs);
				handler.sendMessage(msg);
			}
		}).start();
	}
	
	private void showWeek(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				weekBOs = model.getWeekBOInfo();
				Message msg = handler.obtainMessage(2, weekBOs);
				handler.sendMessage(msg);
			}
		}).start();
	}
	/**
	 * 加载等待数据的动画
	 */
	private void setAnimation(){
		if(lvBoxoffice.getVisibility()==View.INVISIBLE){
			RotateAnimation totate = new RotateAnimation(0, 259, ivLoadImg.getWidth()/2, ivLoadImg.getHeight()/2);
		totate.setDuration(300000);
		totate.setInterpolator(new LinearInterpolator());
		ivLoadImg.setAnimation(totate);
		}
	}
	
	
}
