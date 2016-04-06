package com.ramdomfunctions;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {

	List<Integer> photoLinks;
	List<String> photoNames;
	
	public CollectionPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new AxolotlFragment();
		Bundle args = new Bundle();
		args.putInt(AxolotlFragment.PHOTO_LINK, photoLinks.get(i).intValue()); 
		
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// Number of object to be swiped
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		getPhotos();
		return photoNames.get(position);
	}
	
	public void getPhotos(){
		photoNames = new ArrayList<String>();
		photoLinks = new ArrayList<Integer>();
		// BalckAxolotl
		photoNames.add("Black");
		photoLinks.add(R.drawable.backaxolotl);
		// WhiteAxolotl
		photoNames.add("White");
		photoLinks.add(R.drawable.whiteaxolotl);
		// YellowAxolotl
		photoNames.add("Yellow");
		photoLinks.add(R.drawable.yellowaxolotl);
		// PinkAxolotl
		photoNames.add("Pink");
		photoLinks.add(R.drawable.pinkaxolotl);
	}
}
