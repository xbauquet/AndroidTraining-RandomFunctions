package com.ramdomfunctions;

import com.ramdomfunctions.UserListFragment.ClickListener;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserListActivity extends Activity implements ClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		UserListFragment fragment = new UserListFragment();
		// Declare itself as a listener of the UserListFragment
		((UserListFragment) fragment).setListener(this);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.user_list_fragment_frame, fragment).addToBackStack(null).commit();
	}

	// Has to be implemented because AdminPage is declared as a listener of the
		// UserListFragment
		@Override
		public void onClick(User user) {
			UserDetailFragment detailFragment = (UserDetailFragment) getFragmentManager()
					.findFragmentById(R.id.userDetailFragment);

			if (detailFragment == null || !detailFragment.isInLayout()) {
				Intent intent = new Intent(UserListActivity.this, UserDetailActivity.class);
				intent.putExtra("user", user);
				startActivity(intent);
			} else {
				detailFragment.setUser(user);
			}
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
