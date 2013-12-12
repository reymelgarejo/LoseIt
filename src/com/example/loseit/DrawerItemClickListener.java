package com.example.loseit;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

	private Activity activity;

	public DrawerItemClickListener(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onItemClick(AdapterView parent, View view, int position, long id) {
		switch (position) {
		case 0: {
			Intent intent = new Intent(activity, MainActivity.class);
			activity.startActivity(intent);
		}
			break;

		case 1: {
			Intent intent = new Intent(activity, Routines.class);
			activity.startActivity(intent);
		}
			break;

		case 2: {
			Intent intent = new Intent(activity, Exercises.class);
			activity.startActivity(intent);
		}
			break;

		case 3: {
			Intent intent = new Intent(activity, About_Us.class);
			activity.startActivity(intent);
		}
			break;

		case 4: {
			Intent intent = new Intent(activity, Settings.class);
			activity.startActivity(intent);
		}
			break;
		}
	}
}
