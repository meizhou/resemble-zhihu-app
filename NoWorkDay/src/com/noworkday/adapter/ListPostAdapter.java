package com.noworkday.adapter;

import java.util.LinkedList;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.noworkday.R;
import com.noworkday.base.Util;
import com.noworkday.bean.PostBean;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListPostAdapter extends BaseAdapter{
	public LinkedList<PostBean> postBeans;
	Context context;
	public ListPostAdapter(Context context) {
		this.context = context;
		int[] images = {
				R.drawable.new01,
				R.drawable.new02,
				R.drawable.new03,
				R.drawable.new04,
				R.drawable.new01,
				R.drawable.new03
				};
		String[] textStrings = context.getResources().getStringArray(R.array.testList);
		postBeans =  new LinkedList<PostBean>();
		for (int i = 0; i < images.length; i++) {
			PostBean postBean = new PostBean();
			postBean.setImage(images[i]);
			postBean.setText(textStrings[i]);
			postBeans.add(postBean);
		}
	}
	public ListPostAdapter(Context context, LinkedList<PostBean> postBeans) {
		this.postBeans = postBeans;
		this.context = context;
	}
	@Override
	public int getCount() {
		return postBeans.size();
	}

	@Override
	public Object getItem(int arg0) {
		return postBeans.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		PostBean postBean = postBeans.get(position);
		//todo  view == null
		view = LayoutInflater.from(context).inflate(R.layout.main_list_view, null);
		TextView textView = (TextView) view.findViewById(R.id.post_list_text);
		ImageView imageView = (ImageView) view.findViewById(R.id.post_list_image);
		textView.setText(Util.getText(postBean.getText()));
		if (postBean.getImageUrl() != null) {
			ImageLoader.getInstance().displayImage(postBean.getImageUrl(), imageView, Util.getImageOption(context));
		} else {
			imageView.setImageResource(postBean.getImage());
		}
		view.setTag(position);
		return view;
	}
	public void addPost(LinkedList<PostBean> newPostBeans) {
		for (int i = 0; i < newPostBeans.size(); i++) {
			postBeans.addFirst(newPostBeans.get(i));
		}
		
	}

}
