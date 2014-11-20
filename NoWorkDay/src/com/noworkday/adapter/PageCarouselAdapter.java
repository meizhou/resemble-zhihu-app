package com.noworkday.adapter;

import java.util.LinkedList;

import com.noworkday.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class PageCarouselAdapter extends PageBaseAdapter{
	Context context;
	public PageCarouselAdapter(Context context) {
		views = new LinkedList<View>();
		int[] images = { com.noworkday.R.drawable.new01, R.drawable.new02,
				R.drawable.new03, R.drawable.new04 };
		for (int j = 0; j < images.length; j++) {
			View view = LayoutInflater.from(context).inflate(R.layout.view_page, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.view_page_image);
			imageView.setImageResource(images[j]);
			view.setTag(Integer.toString(j));
			views.add(view);
		}
		this.context = context;
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View viewCurr = views.get(position);
		container.addView(viewCurr);
		viewCurr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(context, view.getTag().toString(), Toast.LENGTH_LONG).show();
			}
		});
		return viewCurr;
	}
}
