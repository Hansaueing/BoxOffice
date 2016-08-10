package com.boxoffice.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.boxoffice.R;

public class PersonalInfoFragment extends Fragment {

	private ListView listView;
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = View.inflate(getActivity(), R.layout.fragment, null);
//		listView = (ListView) v.findViewById(R.id.list_personal);
//		listView.setVisibility(View.VISIBLE);
		String[] abc = { "我的账号", "我的关注", "浏览记录", "...."};

//		adapter = new ArrayAdapter<String>(getActivity(),
//				android.R.layout.simple_list_item_1, abc);
//		listView.setAdapter(adapter);
		
		

		return v;

	}

}
