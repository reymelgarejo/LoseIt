package com.example.loseit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class Settings_Custom_List extends ArrayAdapter<String>{
 
	private final Activity context;
	private final String[] settings;
	private final Integer[] imageId;
	
	public Settings_Custom_List(Activity context, String[] settings, Integer[] imageId) {
		super(context, R.layout.settings_single_list, settings);
		this.context = context;
		this.settings = settings;
		this.imageId = imageId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.settings_single_list, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
		 
		ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
		txtTitle.setText(settings[position]);
		 
		imageView.setImageResource(imageId[position]);
		return rowView;
	}
}
