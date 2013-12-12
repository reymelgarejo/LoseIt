package com.example.loseit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class About_Us extends Drawer_UI{
	private TextView about_us;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);

		onDrawerCreate(this);
		
		about_us = (TextView) findViewById(R.id.about_text);

		String about = "This application is for people who want to lose"
				+ " weight and motivate them while doing it."
				+ "\n\n Developed by:\n\n The Pentagon"
				+ "\n\n UI Design: \n\n Chong, Mark \n De Leon, Rosalyn"
				+ "\n\n Programmers: \n\n Andres, Bryan \n Chong, Mark \n Lato, Sy Hendrick \n Melgarejo, Rey";
		about_us.setText(about);

	}
}
