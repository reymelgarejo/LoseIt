package com.example.loseit;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Routines extends Drawer_UI implements LocationListener{
	private FrameLayout frameLayout;
	private ListView loclistitem;
	private ArrayAdapter<String> arrayAdapter;

	protected LocationManager locationManager;
	protected LocationListener locationListener;
	protected Context context;
	String lat;
	String provider;
	protected String latitude, longitude;
	protected boolean gps_enabled, network_enabled;
	private static final String PROX_ALERT_INTENT = "com.example.loseit.ProximityAlert";
	
	double iLatitude, iLongitude;
	
	final DatabaseHelper dbHelper = new DatabaseHelper(this, null, null, 0);
	
	public static final String PREFS_NAME_Routines = "MyPrefsFileRoutines";
    public CheckBox dontShowAgain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.routines);
		
		onDrawerCreate(this);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		
		// Start of Content Frame Layout
		frameLayout = (FrameLayout) findViewById(R.id.content_frame);
		loclistitem = (ListView) findViewById(R.id.routine_listview);
		
		final String[] locations = dbHelper.get_Locations();
		
		arrayAdapter = new ArrayAdapter<String>(this, R.layout.locations_list, locations);
		loclistitem.setAdapter(arrayAdapter);
		
		loclistitem.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                switch (arg2) {
                    default:
                    	Intent intent = new Intent(Routines.this, Routines_My.class);
                        intent.putExtra("selection", locations[arg2]);
                        startActivity(intent);
                        break;
                }
            }
        });
	}	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		if (item.getItemId() == R.id.add_location) {
			final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			View dialog_layout = getLayoutInflater().inflate(R.layout.dialog_layout, null);
			final EditText ed = (EditText) dialog_layout.findViewById(R.id.location_text);
				
			alertDialogBuilder.setTitle("Add New Location");
			alertDialogBuilder.setPositiveButton("Save Location", new DialogInterface.OnClickListener() {		
						public void onClick(DialogInterface dialog, int id) {		
							dbHelper.add_Location("My " + (ed.getText() + " " + "Routines").toString(), iLatitude, iLongitude);
							addProximityAlert(iLatitude, iLongitude);
							Intent intent = new Intent(Routines.this, Routines.class);
							startActivity(intent);
							finish();
						}
					})
					.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			alertDialogBuilder.setView(dialog_layout);
			alertDialogBuilder.show();
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void addProximityAlert(double latitude, double longitude) {
		
		Intent intent = new Intent(PROX_ALERT_INTENT);
		PendingIntent proximityIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
		
		locationManager.addProximityAlert(latitude, longitude, 1, -1, proximityIntent);
		
		IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
		registerReceiver(new ProximityIntentReceiver(), filter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_locations, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onLocationChanged(Location arg0) {
		iLatitude = arg0.getLatitude();
		iLongitude = arg0.getLongitude();
		
		for(Coordinates c : dbHelper.getCoordinates()){
			Location test = new Location("place");
			test.setLatitude(c.getLatitude());
			test.setLongitude(c.getLongtitude());
			float distance = arg0.distanceTo(test);
			if(distance < 100.00)
			{
				//Toast.makeText(Routines.this, test.getLatitude() + " " + test.getLongitude() + " " + arg0.getLatitude() + " " + arg0.getLongitude() + " " + distance, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Routines.this, Routines.class);
				PendingIntent proximityIntent = PendingIntent.getActivity(Routines.this, 0, intent, 0);
				
				Notification noti = new Notification.Builder(this).setContentTitle("You've arrived at a hotspot")
						.setContentText("You have some exercises to do")
						.setContentIntent(proximityIntent)
						.setSmallIcon(R.drawable.ic_launcher).build();
				
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
				noti.flags |= Notification.FLAG_AUTO_CANCEL;
				notificationManager.notify(0, noti);
			}
		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		Log.d("Latitude", "disable");
	}

	@Override
	public void onProviderEnabled(String arg0) {
		Log.d("Latitude", "enable");
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		Log.d("Latitude", "status");
	}
	
	//Creates a "Don't show this message again" type of dialog box
	@Override
	protected void onResume(){
		 AlertDialog.Builder adb = new AlertDialog.Builder(Routines.this);
		 LayoutInflater adbInflater = LayoutInflater.from(this);
		 View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
		 dontShowAgain = (CheckBox) eulaLayout.findViewById(R.id.skip);
		 adb.setView(eulaLayout);
		 adb.setTitle("Tips!");
		 adb.setMessage("Press the '+' button on the upper right screen " +
		 		"to add a location to where" +
		 		" you want to do your exercises!\n" +
		 		"GPS of the location will be saved for notification purposes.");
		 adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String checkBoxResult = "NOT checked";
				if(dontShowAgain.isChecked())
					checkBoxResult = "checked";
				SharedPreferences settings = getSharedPreferences(PREFS_NAME_Routines, 0);
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
	            SharedPreferences settings = getSharedPreferences(PREFS_NAME_Routines, 0);
	            SharedPreferences.Editor editor = settings.edit();
	            editor.putString("skipMessage", checkBoxResult);
	            editor.commit();
	            return; 		
			}
		  });
		  SharedPreferences settings = getSharedPreferences(PREFS_NAME_Routines, 0);
	      String skipMessage = settings.getString("skipMessage", "NOT checked");
	      if (!skipMessage.equals("checked")) 
	    	  adb.show();
	      super.onResume();
	      
	 }
}