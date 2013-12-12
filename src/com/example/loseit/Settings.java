package com.example.loseit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Settings extends Drawer_UI{

	ListView list;
	String[] settings;
	Integer[] imageId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		onDrawerCreate(this);
		
		list = (ListView)findViewById(R.id.settings_listview);
		
		settings = new String[] {"Clear cache", "Edit My Location", "Notifications"};
		imageId = new Integer[] {R.drawable.settings_01, R.drawable.settings_02, R.drawable.settings_03};
		
		Settings_Custom_List adapter = new Settings_Custom_List(Settings.this, settings, imageId);
        
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		switch (position){
	        		case 0:
	        			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);
	        			alertDialogBuilder.setTitle("Clear Cache...");
	        			alertDialogBuilder.setMessage("All data will be wiped out. Continue?").
	        			setPositiveButton("Continue",
	        					new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										
									}
								}).
						 setNegativeButton("Cancel",
								 new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.cancel();	
									}
								});
	        			 AlertDialog alertDialog = alertDialogBuilder.create();
	        			 alertDialog.show();
	        			break;
	        			
	        		case 1: // put code here. what happens when "Edit My Location" is pressed
	        			break;
	        		case 2: // put code here. what happens when "Notification: is pressed
	        			break;
	        			
        		}
        	}
         });
	}
}
