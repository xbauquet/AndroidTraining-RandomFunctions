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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

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
		drawerButtonTitles.add("Axolotl");
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
		Intent intent = null;
		
		switch (position) {
		case 0:
			intent = new Intent(MainActivity.this, UserListActivity.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(MainActivity.this, PalindromActivity.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(MainActivity.this, AxolotlActivity.class);
			startActivity(intent);
			break;
		}

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
}
