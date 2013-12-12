package com.example.loseit;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "loseit";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_CREATE_Locations = "CREATE TABLE locations(name, latitude, longitude);";
	// private static final String TABLE_CREATE_Exercises =
	// "CREATE TABLE exercises(name, img);";
	private static final String TABLE_CREATE_Routines = "CREATE TABLE routines(location, exercise);";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Creates the Tables here
		db.execSQL(TABLE_CREATE_Locations);
		db.execSQL(TABLE_CREATE_Routines);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	// Methods for Routines
	public void add_Routine(String loc, String exercise) {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// storage for your data
		ContentValues cv = new ContentValues();
		cv.put("location", loc);
		
		switch(Integer.parseInt(exercise)){
		case 0: exercise = "Push Up"; break;
		case 1: exercise = "Sit-up"; break;
		case 2: exercise = "Jumping Jacks"; break;
		case 3: exercise = "Bike Crunch"; break;
		case 4: exercise = "Calf Stretch"; break;
		case 5: exercise = "Hamstring Stretch"; break;
		case 6: exercise = "Quad Stretch"; break;
		case 7: exercise = "Side Plank"; break;
		case 8: exercise = "Leg Lifts"; break;
		case 9: exercise = "Step ups"; break;
		case 10: exercise = "Lunges"; break;
		
		}
		
		cv.put("exercise", exercise);
		// insert values
		db.insert("routines", null, cv);
		// Close DB
		db.close();
	}

	public void delete_Routine(String routine) { // Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete("routines", "exercise = ?", new String[] { routine });

		// Close DB
		db.close();
	}

	// Retrieve Routines from Database
	public String[] get_Routines() {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// Execute the query
		Cursor cur = db.rawQuery("SELECT * FROM routines", null);
		// Retrieve all student names
		ArrayList<String> arr = new ArrayList();
		if (cur.moveToFirst()) {
			do {
				arr.add(cur.getString(cur.getColumnIndex("location")));
			} while (cur.moveToNext());
		}
		// Close connections
		cur.close();
		db.close();

		String[] sarTitles = new String[arr.size()];
		sarTitles = (String[]) arr.toArray(sarTitles);

		return sarTitles;
	}

	public String[] get_ExerByLoc(String loc) {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// Execute the query

		ArrayList<String> arr = new ArrayList();
		Cursor cur = db.rawQuery("SELECT * FROM routines WHERE location = ?", new String[] { loc });
		// Retrieve all student names
		if (cur.moveToFirst()) {
			do {
				arr.add(cur.getString(cur.getColumnIndex("exercise")));
			} while (cur.moveToNext());
		}
		// Close connections
		cur.close();
		db.close();
		
		String[] sarTitles = new String[arr.size()];
		sarTitles = (String[]) arr.toArray(sarTitles);

		return sarTitles;
	}

	// Methods for Locations
	public void add_Location(String loc, double latitude, double longitude) {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// storage for your data
		ContentValues cv = new ContentValues();
		cv.put("name", loc);
		cv.put("latitude", latitude);
		cv.put("longitude", longitude);
		// insert values
		db.insert("locations", null, cv);
		// Close DB
		db.close();
	}

	// Retrieve Locations from Database
	public String[] get_Locations() {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// Execute the query
		Cursor cur = db.rawQuery("SELECT * FROM locations", null);
		// Retrieve all student names
		ArrayList<String> arr = new ArrayList();
		if (cur.moveToFirst()) {
			do {
				arr.add(cur.getString(cur.getColumnIndex("name")));
			} while (cur.moveToNext());
		}
		// Close connections
		cur.close();
		db.close();

		String[] sarTitles = new String[arr.size()];
		sarTitles = (String[]) arr.toArray(sarTitles);

		return sarTitles;
	}
	
	public double get_LongByLoc(String loc) {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// Execute the query

		double d = 0.0;
		Cursor cur = db.rawQuery("SELECT * FROM locations WHERE name = ?",
				new String[] { loc });
		// Retrieve all student names
		if (cur.moveToFirst()) {
			do {
				d += (cur.getDouble(cur.getColumnIndex("longitude")));
			} while (cur.moveToNext());
		}
		// Close connections
		cur.close();
		db.close();

		return d;
	}
	
	public double get_LatiByLoc(String loc) {
		// Open DB Connection
		SQLiteDatabase db = this.getWritableDatabase();
		// Execute the query

		double d = 0.0;
		Cursor cur = db.rawQuery("SELECT * FROM locations WHERE name = ?",
				new String[] { loc });
		// Retrieve all student names
		if (cur.moveToFirst()) {
			do {
				d += (cur.getDouble(cur.getColumnIndex("latitude")));
			} while (cur.moveToNext());
		}
		// Close connections
		cur.close();
		db.close();

		return d;
	}
	
	public Coordinates[] getCoordinates(){
		double lati;
		double longti;
		Coordinates[] arrCoords = new Coordinates[get_Locations().length];
		Coordinates coords;
		int i = 0;
		for (String s : get_Locations()){
			lati = get_LatiByLoc(s);
			longti = get_LongByLoc(s);
			coords = new Coordinates(lati, longti);
			arrCoords[i] = coords;
			i++;
		}
		return arrCoords;
	}

}
