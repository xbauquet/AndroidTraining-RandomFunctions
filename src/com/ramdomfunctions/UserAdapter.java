package com.ramdomfunctions;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
			convertView.setTag(userHolder);
		}

		User user = getItem(position);

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
}
