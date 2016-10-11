package com.ramdomfunctions;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class UserListFragment extends Fragment {

	ListView listView;
	List<User> users;
	UserAdapter adapter;
	ClickListener listener = demmyListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_user_list_fragment, container, false);

		// Generates the user list and adapts it to thelistView
		users = generateUsers();
		adapter = new UserAdapter(view.getContext(), users);

		// Feel the listView with the adapted users
		listView = (ListView) view.findViewById(R.id.listView);
		listView.setAdapter(adapter);

		// On click on a line of the list
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Get the user of the line
				User user = adapter.getItem(position);

				// Trigger the onClick method of the listener
				listener.onClick(user);
			}
		});

		return view;
	}

	// The listener has to declare himself by using setListener with 'this' as
	// parameter, and has to implement ClickListener
	public void setListener(ClickListener listener) {
		if (listener == null) {
			this.listener = demmyListener;
		} else {
			this.listener = listener;
		}
	}

	public interface ClickListener {
		void onClick(User user);
	}

	private static ClickListener demmyListener = new ClickListener() {
		@Override
		public void onClick(User user) {
		}
	};

	// Generate a dummy list of users
	private List<User> generateUsers() {
		List<User> tweets = new ArrayList<User>();
		tweets.add(new User("toto", "toto", "toto"));
		tweets.add(new User("tata", "atat", "atat"));
		tweets.add(new User("titi", "titi", "titi"));
		return tweets;
	}
}
