package com.boxoffice.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.boxoffice.R;
import com.boxoffice.activity.PoiResultActivity.IVListener;

public class NearCinemaAdapter extends BaseAdapter{

	private List<PoiInfo> lists;
	private Context context;
	private List<String> distances;
	private IVListener listener;


	
	public NearCinemaAdapter(List<PoiInfo> lists, Context context , List<String> distances,IVListener listener) {
		super();
		this.lists = lists;
		this.context = context;
		this.distances = distances;
		this.listener = listener;
		
	}

	@Override
	public int getCount() {
		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder{
		TextView tvName;
		TextView tvAddress;
		TextView tvtel;
		TextView tvDistance;
		ImageView ivGo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PoiInfo cinemaInfo =  lists.get(position);
		String distance = distances.get(position);
		ViewHolder holder;
		if(convertView==null){
		convertView = LayoutInflater.from(context).inflate(R.layout.item_poiinfo, parent,false);
		holder = new ViewHolder();
		holder.tvName = (TextView) convertView.findViewById(R.id.tv_cinema_name);
		holder.tvAddress = (TextView) convertView.findViewById(R.id.tv_cinema_address);
		holder.tvtel = (TextView) convertView.findViewById(R.id.tv_cinema_tel);
		holder.tvDistance = (TextView) convertView.findViewById(R.id.tv_distance);
		holder.ivGo = (ImageView) convertView.findViewById(R.id.iv_go);
		convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvName.setText("影院名称:	"+cinemaInfo.name);
		holder.tvAddress.setText("详细信息:	"+cinemaInfo.address);
		holder.tvtel.setText("联系电话:	"+cinemaInfo.phoneNum);
		holder.tvDistance.setText("当前距离:	"+distance+"	米");
		holder.ivGo.setBackgroundResource(R.drawable.icon_geo);
		
		holder.ivGo.setOnClickListener(listener);
		
		return convertView;
	}
	

	

}
