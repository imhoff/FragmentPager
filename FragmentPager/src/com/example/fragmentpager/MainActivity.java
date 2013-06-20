package com.example.fragmentpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {

	private static final String CURRENT_FRAGMENT = MainActivity.class.getCanonicalName() + ".CURRENT_FRAGMENT";
	
	public static final String ARG_PARENTS = "Parents";

	public void goInto(String mHostingLevel, String mPosition) {
		Fragment hostingFragment = newHostingFragment(mHostingLevel, mPosition);
		addFragment(hostingFragment);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addBaseFragment();
	}

	private void addBaseFragment() {
		Fragment hostingFragment = newHostingFragment("", "");
		addFragment(hostingFragment);
	}

	private Fragment newHostingFragment(String mHostingLevel, String oldPosition) {
		Fragment hostingFragment = new PageListFragment();
		Bundle args = new Bundle();
		args.putString(ARG_PARENTS, mHostingLevel + oldPosition +" > ");
		hostingFragment.setArguments(args);
		return hostingFragment;
	}

	private void addFragment(Fragment hostingFragment) {
		Fragment current = getSupportFragmentManager().findFragmentByTag(CURRENT_FRAGMENT);
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.anim.slide_up_and_fadein, R.anim.slide_up_and_fadeout, R.anim.slide_down_and_fadein, R.anim.slide_down_and_fadeout);
		transaction.addToBackStack(CURRENT_FRAGMENT);
		transaction.add(R.id.fragmentSpace, hostingFragment, CURRENT_FRAGMENT);
		if (current != null) transaction.hide(current);
		transaction.commit();
	}

}
