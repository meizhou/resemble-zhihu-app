package com.noworkday.view;

import java.util.LinkedList;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.noworkday.R;
import com.noworkday.activity.WebViewActivity;
import com.noworkday.adapter.ListPostAdapter;
import com.noworkday.adapter.PageCarouselAdapter;
import com.noworkday.base.ApiUrl;
import com.noworkday.base.ApiData;
import com.noworkday.bean.PostBean;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainFragment extends Fragment{
	private View view;
	private CarouselViewPage viewPager;
	private IndicatorLayout indicatorLayout;
	private PullToRefreshListView listView;
	private ListPostAdapter listPostAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_main, container, false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initPostListView();
		initPageView();
	}
	public void initPageView() {
		View pageView = LayoutInflater.from(getActivity()).inflate(R.layout.vp_main, null);
		viewPager = (CarouselViewPage) pageView.findViewById(R.id.vp_main);
		viewPager.setAdapter(new PageCarouselAdapter(getActivity()));
		indicatorLayout = (IndicatorLayout) pageView.findViewById(R.id.indicate_main);
		indicatorLayout.setViewPage(viewPager);
		listView.getRefreshableView().addHeaderView(pageView, null, false);
	}
	public void initPostListView() {
		listView = (PullToRefreshListView) view.findViewById(R.id.main_list_view);
		listView.getRefreshableView().setDivider(null);
		listView.getRefreshableView().setVerticalScrollBarEnabled(false);
		listView.setMode(Mode.BOTH);
		listPostAdapter = new ListPostAdapter(getActivity());
		listView.setAdapter(listPostAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				startActivity(intent);
				//Toast.makeText(getActivity(), view.getTag().toString(), Toast.LENGTH_LONG).show();
			}
		});
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getActivity(),
						System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
								| DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);

				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				new AsyncTask<Void, Void, String>() {

					@Override
					protected String doInBackground(Void... arg0) {
						return ApiData.httpGet(ApiUrl.POST_LIST);
					}
					protected void onPostExecute(String result) {
						LinkedList<PostBean> postBeans = PostBean.parse(result);
						listPostAdapter.addPost(postBeans);
						listPostAdapter.notifyDataSetChanged();
						Toast toast = Toast.makeText(getActivity(), "更新了"+postBeans.size()+"条数据", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.TOP, 0, 100);
						toast.show();
						listView.onRefreshComplete();
					}
				}.execute();
				
			}
		});
	}
}
