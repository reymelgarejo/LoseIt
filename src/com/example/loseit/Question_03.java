package com.example.loseit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;

public class Question_03 extends Activity {
	
	private ImageView sex_next;
	private ImageView sex_previous;
	private RadioGroup sex_group;
	private RadioButton sex_radioButton;
	private RadioButton sex_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_03);
		
		sex_next = (ImageView) findViewById(R.id.sex_next);
		sex_previous = (ImageView) findViewById(R.id.sex_previous);
		sex_group = (RadioGroup) findViewById(R.id.radio_sex);
		
		
		
		sex_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int selectedId = sex_group.getCheckedRadioButtonId();
				
				sex_radioButton = (RadioButton) findViewById(selectedId);
				
				String FILENAME = "Sex";
				String name = (String) sex_radioButton.getText();
				
				FileOutputStream fos;
				try {
					fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
					fos.write(name.getBytes());
					fos.close();
					
					Toast.makeText(getApplicationContext(),FILENAME + " saved", Toast.LENGTH_LONG).show();
				}catch (FileNotFoundException e) {e.printStackTrace();}
				catch (IOException e) {e.printStackTrace();}
				
				Intent intent = new Intent(Question_03.this, Question_04.class);
				startActivity(intent);
			}
		});
		
		sex_previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Question_03.this, Question_02.class);
				startActivity(intent);
			}
		});	
	}
}
