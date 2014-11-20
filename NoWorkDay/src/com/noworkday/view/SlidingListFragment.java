package com.noworkday.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.noworkday.R;
import com.noworkday.activity.MainActivity;


import android.R.integer;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SlidingListFragment extends ListFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.menu_left_fragment, container, false);
	}
	private class MyItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new MainFragment();
				break;
			case 1:
				fragment = new MainFragment();
				break;
			case 2:
				fragment = new CollectFragment();
				break;
			default:
				fragment = new MainFragment();
				break;
			}
			switchFragment(fragment);
			
		}
		
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listView = getListView();
		SimpleAdapter adapter = new SimpleAdapter(
				getActivity(), 
				this.getData(),
				R.layout.menu_left_list,
				new String[]{"image","text"},
				new int[]{R.id.menu_list_image, R.id.menu_list_text});
		listView.setAdapter(adapter);
		listView.setDivider(null);
		listView.setDividerHeight(0);
		listView.setOnItemClickListener(new MyItemClickListener());
	}
	private ListView listView;
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int[] images = {
				R.drawable.menu_home,
				R.drawable.menu_management,
				R.drawable.menu_collect,
				R.drawable.menu_message,
				R.drawable.menu_setting
				};
		String[] textStrings = getResources().getStringArray(R.array.menuList);
		for (int i = 0; i < textStrings.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("image", images[i]);
			map.put("text", textStrings[i]);
			list.add(map);
		}
		return list;
	}
	private void switchFragment(Fragment fragment) {
		MainActivity mainActivity =  (MainActivity) getActivity();
		mainActivity.switchFragment(fragment);
	}
}
