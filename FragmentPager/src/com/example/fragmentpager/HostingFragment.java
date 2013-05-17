package com.example.fragmentpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HostingFragment extends Fragment {

	private String mParentString;

	public HostingFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_hosting, container, false);
	}

	@Override
	public void onResume() {
		mParentString = getArguments().getString(MainActivity.ARG_PARENTS);
		ViewPager viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
		viewPager.setAdapter(new SimpleFragmentStatePagerAdapter(getFragmentManager(),mParentString));
		super.onResume();
	}
	
	private static class SimpleFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

		private String mHostingLevel;

		public SimpleFragmentStatePagerAdapter(FragmentManager fm, String hostingLevel) {
			super(fm);
			this.mHostingLevel = hostingLevel;
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			PageFragment pageFragment = new PageFragment();
			Bundle args = new Bundle();
			args.putString(MainActivity.ARG_PARENTS, mHostingLevel);
			args.putInt(PageFragment.ARG_POSITION, position);
			pageFragment.setArguments(args);
			return pageFragment;
		}
		
		@Override
		public int getCount() {
			return 3;
		}
	}
}
