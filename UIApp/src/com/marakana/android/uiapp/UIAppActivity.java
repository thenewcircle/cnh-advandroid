package com.marakana.android.uiapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import dalvik.system.DexClassLoader;

public class UIAppActivity extends Activity {
	static final String TAG = UIAppActivity.class.getSimpleName();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DexClassLoader cl = new DexClassLoader("/sdcard/UIComponents.apk",
				"/sdcard/", null, getClass().getClassLoader());
		Log.d(TAG, "resource: "+cl.getResource("res/string/app_name"));

		try {
			Class clazz = cl
					.loadClass("com.marakana.android.uicomponents.ComponentFragment");
			Log.d(TAG, "GOT CLASS: " + clazz.toString());
 
			FragmentTransaction t = getFragmentManager().beginTransaction();
			t.setCustomAnimations(R.anim.slide, R.anim.slide);
			t.add(R.id.container, (Fragment) clazz.newInstance());
			t.commit();
			Log.d(TAG, "DONE");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}