package com.example.loseit;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.content.Intent;

public class CCS_Splash extends Activity {
	
private static final int SPLASH_TIME = 2 * 1000;// 4 seconds
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ccs_logo);
		
		
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(CCS_Splash.this,
						App_Logo.class);
				startActivity(intent);

				CCS_Splash.this.finish();

				// overridePendingTransition(R.anim.fadein, R.anim.fadeout);

			}
		}, SPLASH_TIME);
		
		new Handler().postDelayed(new Runnable() {
			  @Override
			  public void run() {
			         } 
			    }, SPLASH_TIME);

	}
}
