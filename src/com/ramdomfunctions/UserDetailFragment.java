package com.ramdomfunctions;

import java.io.File;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class UserDetailFragment extends Fragment {

	private TextView detailEmail;
	private TextView detailFirstName;
	private TextView detailLastName;
	private ImageView loadedImage;
	private Switch actifSwitch;
	private SharedPreferences prefs;

	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.activity_user_detail_fragment, container, false);

		detailEmail = (TextView) view.findViewById(R.id.detailEmail);
		detailFirstName = (TextView) view.findViewById(R.id.detailFirstName);
		detailLastName = (TextView) view.findViewById(R.id.detailLastName);
		loadedImage = (ImageView) view.findViewById(R.id.loadedImage);
		actifSwitch = (Switch) view.findViewById(R.id.actif_switch);

		// for switch persistence
		prefs = getActivity().getSharedPreferences(this.getClass().toString(), Context.MODE_WORLD_READABLE);

		// For permissions
		verifyStoragePermissions(getActivity());

		// Check if the SD card is available
		Boolean isSDPresent = android.os.Environment.getExternalStorageState()
				.equals(android.os.Environment.MEDIA_MOUNTED);
		if (isSDPresent) {
			File file = new File("/sdcard/Download/cutest-tiger-cub.jpg");
			if (file != null) {
				loadedImage.setImageURI(Uri.fromFile(file));
			}
		}

		// Save the switch status in the preferences file
		actifSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences.Editor editor = prefs.edit();
				if (isChecked) {
					editor.putBoolean(detailEmail.getText().toString(), true);
				} else {
					editor.putBoolean(detailEmail.getText().toString(), false);
				}
				editor.commit();
			}
		});

		return view;
	}

	@Override
	public void onResume() {
		// Restore the values of the switch
		Boolean bool = prefs.getBoolean(detailEmail.getText().toString(), false);
		actifSwitch.setChecked(bool);
		super.onResume();
	}

	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = { Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE };

	/**
	 * Checks if the app has permission to write to device storage
	 *
	 * If the app does not has permission then the user will be prompted to
	 * grant permissions
	 *
	 * @param activity
	 */
	public static void verifyStoragePermissions(Activity activity) {
		// Check if we have write permission
		int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

		if (permission != PackageManager.PERMISSION_GRANTED) {
			// We don't have permission so prompt the user
			ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
		}
	}

	public void setUser(User user) {
		detailEmail.setText(user.getEmail());
		detailFirstName.setText(user.getFirstName());
		detailLastName.setText(user.getLastName());
	}
}