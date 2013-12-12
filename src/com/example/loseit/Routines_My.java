package com.example.loseit;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Routines_My extends Activity {
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this, null, null, 0);
	private DatabaseHelper dbHelp;
	private ListView routines_list;
	private Button add_routine;
	private final ArrayList selectedItems = new ArrayList();
	private ArrayAdapter<String> arrayAdapter;
	private ImageView image;
	
	final CharSequence[] items = {"Push Up","Sit Up","Jumping Jack", "Bicycle Crunch", "Calf Stretch", 
			"Hamstring Stretch", "Quad Stretch", "Side Plank", "Leg Lift", "Step Up", "Lunge"};
	
	final Integer[] imgId = {R.drawable.undone, R.drawable.undone, R.drawable.undone, R.drawable.undone,
			R.drawable.undone, R.drawable.undone, R.drawable.undone, R.drawable.undone, R.drawable.undone,
			R.drawable.undone, R.drawable.undone};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routines_my);
		
		routines_list = (ListView) findViewById(R.id.routineslistitem);
		image = (ImageView) findViewById(R.id.routines_img);
		
		Bundle extras = getIntent().getExtras();
		String loc = extras.getString("selection");
		
		dbHelp = new DatabaseHelper(this, null, null, 0);
		final String[] routines = dbHelper.get_ExerByLoc(loc);
		
		View myView = findViewById(R.id.routines_my_bg);
		View root = myView.getRootView();
		
		if(routines.length > 0){	
			myView.setBackgroundColor(getResources().getColor(R.color.light_blue));
		}else
			myView.setBackgroundResource(R.drawable.blue_background_instructions);
		
		//RoutineAdapter adapter = new RoutineAdapter(this, R.layout.routines_single_list, routine_list);
		final Routines_Custom_List adapter = new Routines_Custom_List(Routines_My.this, routines, imgId);
		//arrayAdapter = new ArrayAdapter<String>(this, R.layout.routines_list, routines);
		routines_list.setAdapter(adapter);
		
		routines_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			  @Override
	          public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				  
				  switch(arg2){
				  		default:
				  			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Routines_My.this);
		        			alertDialogBuilder.setTitle("Routine Done...");
		        			alertDialogBuilder.setMessage("Are you done doing the " + items[arg2].toString() + "?").
		        			setPositiveButton("Yes",
		        					new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											//View myView = findViewById(R.drawable.undone);
											//View root = myView.getRootView();
											//myView.setBackgroundResource(R.drawable.done);
											
											imgId[arg2].equals(R.drawable.done);
											adapter.setImageId(imgId);
											
										}
									}).
							 setNegativeButton("No",
									 new DialogInterface.OnClickListener() {
										@Override
										public void onClick(DialogInterface dialog, int which) {
											dialog.cancel();	
										}
									});
		        			 AlertDialog alertDialog = alertDialogBuilder.create();
		        			 alertDialog.show();
				  }
			  }
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_routines, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId() == R.id.add_routines){
			final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Routines_My.this);
			alertDialogBuilder.setTitle("Add New Routines...");
            alertDialogBuilder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener(){
           
                 @Override
                 public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked){
                     if (isChecked){
                         selectedItems.add(indexSelected);
                
                     } 
                     else if (selectedItems.contains(indexSelected)){
                         selectedItems.remove(Integer.valueOf(indexSelected));
                     }
                 }
             })
         .setPositiveButton("Add Routines", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id){
                //put code here
//            	 dbHelper = new DatabaseHelper(MyLocation.this, null, null, 0);
            	 Bundle extras = getIntent().getExtras();
            	 String loc = extras.getString("selection");
            	 for(int i = 0; i < selectedItems.size(); i++)
                 {
                	 dbHelper.add_Routine(loc, selectedItems.get(i).toString());
                 }
            	 Toast.makeText(Routines_My.this.getApplicationContext(),"Success", Toast.LENGTH_LONG).show();
            	 Intent intent = new Intent(Routines_My.this, Routines_My.class);
            	 intent.putExtra("selection", loc);
					startActivity(intent);
					finish();
             }
         })
         .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int id){
            	 dialog.cancel();
             }
         });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
		}
		return super.onOptionsItemSelected(item);
	}
	
}
		