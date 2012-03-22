package com.marakana.android.uiapp;

import dalvik.system.DexClassLoader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

public class UIAppActivity extends Activity {
	static final String TAG = UIAppActivity.class.getSimpleName();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DexClassLoader cl = new DexClassLoader("/sdcard/components.jar",
				"/sdcard/", null, getClass().getClassLoader());

		try { 
			Class clazz = cl.loadClass("com.marakana.android.uicomponents.ComponentFragment");
			Log.d(TAG, "GOT CLASS: " + clazz.toString());

			getFragmentManager().beginTransaction()
					.add(R.id.container, (Fragment) clazz.newInstance())
					.commit();
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