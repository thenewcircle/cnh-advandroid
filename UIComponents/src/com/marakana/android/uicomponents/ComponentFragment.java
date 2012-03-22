package com.marakana.android.uicomponents;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ComponentFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View view = inflater.inflate(R.layout.main, null);
//		TextView out = (TextView) view.findViewById(R.id.out);
		TextView out = new TextView(getActivity());
		out.setText("Hi, I'm ComponentFragment!");
		
		return out;
	}
}
