package com.ramdomfunctions;

import android.app.Fragment;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_palindrom, container, false);

		EditText editText = (EditText) view.findViewById(R.id.input);

		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				TextView textView = (TextView) view.findViewById(R.id.output);
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
