package com.example.loseit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

public class App_Logo extends Activity {
	
private static final int SPLASH_TIME = 2 * 1000;// 4 seconds
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_logo);
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(App_Logo.this,
						Question_01.class);
				startActivity(intent);

				App_Logo.this.finish();

			}
		}, SPLASH_TIME);
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			         } 
			    }, SPLASH_TIME);

	}
}
