package com.example.loseit;

import android.app.Activity; 
import android.content.Context; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ArrayAdapter; 
import android.widget.ImageView; 
import android.widget.TextView;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
	Context context;
	int layoutResourceId;
	Exercise data[] = null;
	
	public ExerciseAdapter(Context context, int layoutResourceId, Exercise[] data){
		super(context, layoutResourceId, data);
		
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View row = convertView;
		ExerciseHolder holder = null;
		
		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			 holder = new ExerciseHolder();
			 holder.imgIcon = (ImageView)row.findViewById(R.id.img);
			 holder.txtTitle = (TextView)row.findViewById(R.id.txt);
			 
			 row.setTag(holder);
		}
		else{
			holder = (ExerciseHolder)row.getTag();
		}
		
		Exercise exercise = data[position];
		holder.txtTitle.setText(exercise.name);
		holder.imgIcon.setImageResource(exercise.icon);
		
		return row;
	}
	
	static class ExerciseHolder{
		ImageView imgIcon;
		TextView txtTitle;
	}
}
