package com.ramdomfunctions;

import java.util.ArrayList;
import java.util.List;

import com.ramdomfunctions.UserListFragment.ClickListener;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements ClickListener {

	private List<String> drawerButtonTitles;
	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private CharSequence title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initButtonList();

		title = getTitle();
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		drawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawerButtonTitles));
		// Set the list's click listener
		drawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	private void initButtonList() {
		drawerButtonTitles = new ArrayList<String>();
		drawerButtonTitles.add("Users");
		drawerButtonTitles.add("Palindrom");
		drawerButtonTitles.add("Activity 3");

	}

	@Override
	public void setTitle(CharSequence title) {
		this.title = title;
		getActionBar().setTitle(this.title);
	}

	// Listener
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	// Swaps fragments in the main content view
	private void selectItem(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new UserListFragment();
			// Declare itself as a listener of the UserListFragment
			((UserListFragment) fragment).setListener(this);
			break;
		case 1:
			fragment = new PalindromFragment();
			break;
		case 2:
			fragment = new PalindromFragment();
			break;
		}

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();

		// Highlight the selected item, update the title, and close the drawer
		drawerList.setItemChecked(position, true);
		setTitle(drawerButtonTitles.get(position));
		drawerLayout.closeDrawer(drawerList);
	}

	// Used when press back button
	@Override
	public void onBackPressed() {
		if (getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStack();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	// Dummy Fragment
	public static class DummyFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public DummyFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.dummy_fragment, container, false);
			return rootView;
		}
	}

	// Has to be implemented because AdminPage is declared as a listener of the
	// UserListFragment
	@Override
	public void onClick(User user) {
		UserDetailFragment detailFragment = (UserDetailFragment) getFragmentManager()
				.findFragmentById(R.id.userDetailFragment);

		if (detailFragment == null || !detailFragment.isInLayout()) {
			Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
			intent.putExtra("user", user);
			startActivity(intent);
		} else {
			detailFragment.setUser(user);
		}
	}
}
