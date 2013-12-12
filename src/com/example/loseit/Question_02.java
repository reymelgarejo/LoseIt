package com.example.loseit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class Question_02 extends Activity {
	
	private EditText age_edittext;
	private ImageView age_next;
	private ImageView age_previous;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_02);
		
		age_edittext = (EditText) findViewById(R.id.age_edittext);
		age_next = (ImageView) findViewById(R.id.age_next);
		age_previous = (ImageView) findViewById(R.id.age_previous);
		
		age_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String FILENAME = "Age";
				String name = age_edittext.getText().toString();
				
				if(age_edittext.getText().toString().trim().equals("")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Question_02.this);
        			alertDialogBuilder.setTitle("Attention!");
        			alertDialogBuilder.setMessage("Do not leave the text field blank.").
        			setPositiveButton("OK",
        					new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							});
        			 AlertDialog alertDialog = alertDialogBuilder.create();
        			 alertDialog.show();
				}
				else{
					FileOutputStream fos;
					try {
						fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
						fos.write(name.getBytes());
						fos.close();
						
						Toast.makeText(getApplicationContext(),FILENAME + " saved", Toast.LENGTH_LONG).show();
					}catch (FileNotFoundException e) {e.printStackTrace();}
					catch (IOException e) {e.printStackTrace();}
					
					Intent intent = new Intent(Question_02.this, Question_03.class);
					startActivity(intent);
				}
			}
		});
		
		age_previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Question_02.this, Question_01.class);
				startActivity(intent);
			}
		});
	}
}
