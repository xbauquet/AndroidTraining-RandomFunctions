package com.ramdomfunctions;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrawerFragment extends ListFragment {

    private List<String> drawerButtonTitles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initButtonList();

        setListAdapter(new ArrayAdapter<String>(inflater.getContext(), R.layout.drawer_list_item,
                drawerButtonTitles.toArray(new String[0])));

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void initButtonList() {
        drawerButtonTitles = new ArrayList<String>();
        drawerButtonTitles.add("Users");
        drawerButtonTitles.add("Palindrome");
        drawerButtonTitles.add("Axolotl");
        drawerButtonTitles.add("Contact");
        drawerButtonTitles.add("Animals");
        drawerButtonTitles.add("Maps");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = null;

        switch (position) {
            case 0:
                intent = new Intent(getActivity(), UserListActivity.class);
                break;
            case 1:
                intent = new Intent(getActivity(), PalindromActivity.class);
                break;
            case 2:
                intent = new Intent(getActivity(), AxolotlActivity.class);
                break;
            case 3:
                intent = new Intent(getActivity(), ContactListActivity.class);
                break;
            case 4:
                intent = new Intent(getActivity(), AnimalListActivity.class);
                break;
            case 5:
                intent = new Intent(getActivity(), MapsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
