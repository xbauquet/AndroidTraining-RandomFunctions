package com.ramdomfunctions;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PalindromFragment extends Fragment {

	View view;
	SharedPreferences prefs;
	EditText editText;
	TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_palindrom_fragment, container, false);

		editText = (EditText) view.findViewById(R.id.input);
		textView = (TextView) view.findViewById(R.id.output);
		
		prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
		
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TextView isPalindromText = (TextView) view.findViewById(R.id.is_palindrom);
				String invert = stringEnvers(s.toString());
				textView.setText(invert);
				isPalindromText.setText("Is a palindrom : " + isPalindrom(s.toString(), invert).toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		return view;
	}

	@Override
	public void onPause() {
		// Save the editText input 
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("palindrom", editText.getText().toString());
		editor.commit();
		super.onPause();
	}
	
	@Override
	public void onResume() {
		// Restore the values of editText and textView
		String string = prefs.getString("palindrom","");
		editText.setText(string);
		textView.setText(stringEnvers(string));
		super.onResume();
	}
	
	public String stringEnvers(String word) {
		StringBuffer buffer = new StringBuffer();
		for (int i = word.length() - 1; i >= 0; i--) {
			buffer.append(word.charAt(i));
		}
		return buffer.toString();
	}

	public Boolean isPalindrom(String string, String invert) {
		return string.equals(invert);
	}

}
