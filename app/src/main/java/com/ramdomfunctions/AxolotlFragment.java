package com.ramdomfunctions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AxolotlFragment extends Fragment {

	public static final String PHOTO_LINK = Integer.toString(R.drawable.blank);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_axolotl_fragment, container, false);
		Bundle args = getArguments();
		ImageView image = (ImageView) view.findViewById(R.id.imageView);
		image.setImageResource(args.getInt(PHOTO_LINK));
		return view;
	}
}
