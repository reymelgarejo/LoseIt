package com.example.loseit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Routines_Custom_List extends ArrayAdapter<String>{
	
	private final Activity context;
	private final String[] routines;
	private Integer[] imageId;
	//private final Integer[] statusId;
	
	public Routines_Custom_List(Activity context, String[] routines, Integer[] imageId){
		super(context, R.layout.routines_single_list, routines);
		this.context = context;
		this.routines = routines;
		this.imageId = imageId;
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.routines_single_list, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.routines_txt);
		 
		ImageView imageView = (ImageView) rowView.findViewById(R.id.routines_img);
		txtTitle.setText(routines[position]);
		 
		imageView.setImageResource(getImageId()[position]);
		return rowView;
	}

	private Integer[] getImageId() {
		return imageId;
	}
	
	public void setImageId(Integer[] img) {
		imageId = img;
	}
}
