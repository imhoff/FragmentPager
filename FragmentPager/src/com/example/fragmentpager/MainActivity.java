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
		args.putString(ARG_PARENTS, mHostingLevel + oldPosition +">");
		hostingFragment.setArguments(args);
		return hostingFragment;
	}

	private void addFragment(Fragment hostingFragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentSpace, hostingFragment, CURRENT_FRAGMENT);
		transaction.addToBackStack(null);
		transaction.commit();
	}

}
