package com.noworkday.activity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.noworkday.R;
import com.noworkday.base.Util;
import com.noworkday.view.MainFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
	private SlidingMenu slidingMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		slidingMenu = Util.initSlidingMenu(this);
		Util.initActionButton(this, slidingMenu);
		switchFragment(new MainFragment());
	}
	public void switchFragment(Fragment fragment) {
		getFragmentManager().beginTransaction()
		.replace(R.id.content_fragment, fragment).commit();
		slidingMenu.showContent();
	}
}
