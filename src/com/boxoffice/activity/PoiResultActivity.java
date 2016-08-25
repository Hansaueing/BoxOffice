package com.boxoffice.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.boxoffice.R;
import com.boxoffice.adapter.NearCinemaAdapter;

public class PoiResultActivity extends Activity implements OnItemClickListener{
	
//	private TextView tvCinemaName,tvAddress,tvTel,tvDistance;
//	private ImageView ivGo;
	private ListView listview;
	
	private List<PoiInfo> cinemas;
	private List<String> distances;
	private int position;
//	private double distance;
//	private int clickPosition;
	
	private NearCinemaAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nearcinemas);
		cinemas = (List<PoiInfo>) getIntent().getSerializableExtra("poiResult");
		distances = getIntent().getStringArrayListExtra("distances");
		position = getIntent().getIntExtra("n", 0);
//		distance = getIntent().getDoubleExtra("distance", 0);
		
		Log.i("text", "PoiResultActivity results position="+position);
		Log.i("text", "PoiResultActivity results.size()="+cinemas.size());
		Log.i("text", "PoiResultActivity distances.size()="+distances.size());
		
		
		init();
		loadContent();
		setListener();
//		ivGo.setOnClickListener(this);
		
	}
	private void setListener(){
		listview.setOnItemClickListener(this);
	}
	
	private void loadContent() {
		listview.setAdapter(adapter);
		
	}

	
	private void init() {
		
		listview = (ListView) findViewById(R.id.lv_near_cinema);
		adapter = new NearCinemaAdapter(cinemas,this,distances,new IVListener());
		
	}
	
	public class IVListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Log.i("text", "call finish()");
			PoiResultActivity.this.finish();
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i("chenjia", ""+position);
		
	}	
}
