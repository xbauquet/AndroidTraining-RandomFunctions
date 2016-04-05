package com.ramdomfunctions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDetailFragment extends Fragment {

	private TextView detailEmail;
	private TextView detailFirstName;
	private TextView detailLastName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_user_detail_fragment, container, false);

		detailEmail = (TextView) view.findViewById(R.id.detailEmail);
		detailFirstName = (TextView) view.findViewById(R.id.detailFirstName);
		detailLastName = (TextView) view.findViewById(R.id.detailLastName);

		return view;
	}

	public void setUser(User user) {
		detailEmail.setText(user.getEmail());
		detailFirstName.setText(user.getFirstName());
		detailLastName.setText(user.getLastName());
	}
}