package com.example.fragmentpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class PageFragment extends Fragment {

	public static final String ARG_POSITION = "Position";
	
	private String mHostingLevel;
	private int mPosition;

	public PageFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View contentView = inflater.inflate(R.layout.fragment_page, container, false);
		setupTextView(contentView);
		setupButton(contentView);
		return contentView;
	}

	private void setupTextView(View contentView) {
		mPosition = getArguments().getInt(ARG_POSITION);
		mHostingLevel = getArguments().getString(MainActivity.ARG_PARENTS);
		TextView text = (TextView) contentView.findViewById(R.id.textView);
		text.setText("Parent Fragments " + mHostingLevel + " \n\nCurrent Fragment "+ mPosition);
	}

	private void setupButton(View contentView) {
		Button button = (Button) contentView.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openNewLevel();
			}
		});
	}
	
	protected void openNewLevel() {
		MainActivity activity = (MainActivity) getActivity();
		activity.goInto(mHostingLevel, Integer.toString(mPosition));
	}

}
