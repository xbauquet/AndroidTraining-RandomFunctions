package com.ramdomfunctions;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserAdapter extends ArrayAdapter<User> {
	public UserAdapter(Context context, List<User> users) {
		super(context, 0, users);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom_cell, parent, false);
		}

		UserHolder userHolder = (UserHolder) convertView.getTag();
		if (userHolder == null) {
			userHolder = new UserHolder();
			userHolder.email = (TextView) convertView.findViewById(R.id.email);
			userHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
			userHolder.lastName = (TextView) convertView.findViewById(R.id.lastName);
			userHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
			convertView.setTag(userHolder);
		}

		User user = getItem(position);

		SharedPreferences prefs = getContext().getSharedPreferences(UserDetailFragment.class.toString(),
				Context.MODE_WORLD_READABLE);
		Boolean bool = prefs.getBoolean(user.getEmail(), false);

		if (bool) {
			userHolder.linearLayout.setBackgroundColor(parent.getResources().getColor(R.color.blue));
		} else {
			userHolder.linearLayout.setBackgroundColor(parent.getResources().getColor(R.color.red));
		}

		userHolder.email.setText(user.getEmail());
		userHolder.firstName.setText(user.getFirstName());
		userHolder.lastName.setText(user.getLastName());

		return convertView;
	}
}

class UserHolder {
	public TextView email;
	public TextView firstName;
	public TextView lastName;
	public LinearLayout linearLayout;
}
