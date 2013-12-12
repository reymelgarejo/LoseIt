package com.example.loseit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

public class Exercises extends Drawer_UI {
	
	private ListView list;
	String[] exercises;
	Integer[] imageId;
	
	public static final String PREFS_NAME_Exercises = "MyPrefsFileExercises";
    public CheckBox dontShowAgain;
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.exercises);
	        onDrawerCreate(this);
	  
	        final Exercise exercise_list[] = new Exercise[]{
	        		new Exercise(R.drawable.exercise_01, "Push Up"),
	        		new Exercise(R.drawable.exercise_02, "Sit Up"),
	        		new Exercise(R.drawable.exercise_03, "Jumping Jack"),
	        		new Exercise(R.drawable.exercise_04, "Bicycle Crunch"),
	        		new Exercise(R.drawable.exercise_05, "Calf Stretch"),
	        		new Exercise(R.drawable.exercise_06, "Hamstring Stretch"),
	        		new Exercise(R.drawable.exercise_07, "Quad Stretch"),
	        		new Exercise(R.drawable.exercise_08, "Side Plank"),
	        		new Exercise(R.drawable.exercise_09, "Leg Lift"),
	        		new Exercise(R.drawable.exercise_10, "Step Up"),
	        		new Exercise(R.drawable.exercise_11, "Lunge")
	        };
	        list = (ListView)findViewById(R.id.exercise_listview);
	        
	        //exercises = new String[]{"Push Up", "Sit Up", "Jumping Jack", "Bicycle Crunch", "Calf Stretch", 
	    	//        "Hamstring Stretch", "Quad Stretch", "Side Plank", "Leg Lift", "Step Up", "Lunge"};
	        //imageId = new Integer[]{R.drawable.exercise_01, R.drawable.exercise_02, R.drawable.exercise_03,
		    //      R.drawable.exercise_04, R.drawable.exercise_05, R.drawable.exercise_06, R.drawable.exercise_07,
		    //      R.drawable.exercise_08, R.drawable.exercise_09, R.drawable.exercise_10, R.drawable.exercise_11};
	        
	        //Exercises_Custom_List adapter = new Exercises_Custom_List(Exercises.this, exercises, imageId);
	        
	        ExerciseAdapter adapter = new ExerciseAdapter(this, R.layout.exercises_single_list, exercise_list);

	        list.setAdapter(adapter);
	        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	 
	        	@Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		
	        		switch (position){
                    default:
		        		Intent intent = new Intent(Exercises.this, Exercises_Instructions.class);
	                    intent.putExtra("sExercise", exercise_list[position].getName().toString());
	                    startActivity(intent);
	                    break;
	        		}
	        	}
	         });  
	 }
	 
	 //Creates a "Don't show this message again" type of dialog box
	 @Override
	 protected void onResume(){
		 AlertDialog.Builder adb = new AlertDialog.Builder(Exercises.this);
		 LayoutInflater adbInflater = LayoutInflater.from(this);
		 View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
		 dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
		 adb.setView(eulaLayout);
		 adb.setTitle("Tips!");
		 adb.setMessage("See how every exercise is done properly. With picture and" +
		 		" steps on how to do it!");
		 adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
				if(dontShowAgain.isChecked())
					checkBoxResult = "checked";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME_Exercises, 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("skipMessage", checkBoxResult);
				editor.commit();
				return;
			}
		 });
		 
		 adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
	            if(dontShowAgain.isChecked())  
	            	checkBoxResult = "checked";
	            SharedPreferences settings = getSharedPreferences(PREFS_NAME_Exercises, 0);
	            SharedPreferences.Editor editor = settings.edit();
	            editor.putString("skipMessage", checkBoxResult);
	            editor.commit();
	            return; 		
			}
		  });
		  SharedPreferences settings = getSharedPreferences(PREFS_NAME_Exercises, 0);
	      String skipMessage = settings.getString("skipMessage", "NOT checked");
	      if (!skipMessage.equals("checked")) 
	    	  adb.show();
	      super.onResume();
	 }
}
