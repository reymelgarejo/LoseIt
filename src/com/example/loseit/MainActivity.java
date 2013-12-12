package com.example.loseit;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Drawer_UI {

	public static final String PREFS_NAME_MainActivity = "MyPrefsFileMainActivity";
    public CheckBox dontShowAgain;
    TextView main_text;
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//View myView = findViewById(R.id.home_bg);
		//View root = myView.getRootView();
		onDrawerCreate(this);
		
		main_text = (TextView) findViewById(R.id.main_text);
		//myView.setBackgroundColor(getResources().getColor(android.R.color.white));
	}
	
	//Creates a "Don't show this message again" type of dialog box
	@Override
	protected void onResume(){
		AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater adbInflater = LayoutInflater.from(this);
		View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
		dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
		adb.setView(eulaLayout);
		adb.setTitle("Tips!");
		adb.setMessage("Welcome to Lose It! \n\n" +
				"Navigate through the pages by pressing the icon" +
				" on the upper left side of the screen.");
		adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
				if(dontShowAgain.isChecked())
					checkBoxResult = "checked";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME_MainActivity, 0);
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
		            SharedPreferences settings = getSharedPreferences(PREFS_NAME_MainActivity, 0);
		            SharedPreferences.Editor editor = settings.edit();
		            editor.putString("skipMessage", checkBoxResult);
		            editor.commit();
		            return; 		
				}
			  });
			  SharedPreferences settings = getSharedPreferences(PREFS_NAME_MainActivity, 0);
		      String skipMessage = settings.getString("skipMessage", "NOT checked");
		      if (!skipMessage.equals("checked")) 
		    	  adb.show();
		      super.onResume();
		 }
}
