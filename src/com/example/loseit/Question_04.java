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

public class Question_04 extends Activity {
	
	private EditText height_edittext;
	private ImageView height_next;
	private ImageView height_previous;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_04);
		
		height_edittext = (EditText) findViewById(R.id.height_edittext);
		height_next = (ImageView) findViewById(R.id.height_next);
		height_previous = (ImageView) findViewById(R.id.height_previous);
		
		height_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(height_edittext.getText().toString().trim().equals("")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Question_04.this);
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
					String FILENAME = "Height";
					String name = height_edittext.getText().toString();
					
					FileOutputStream fos;
					try {
						fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
						fos.write(name.getBytes());
						fos.close();
						
						Toast.makeText(getApplicationContext(),FILENAME + " saved", Toast.LENGTH_LONG).show();
					}catch (FileNotFoundException e) {e.printStackTrace();}
					catch (IOException e) {e.printStackTrace();}
					
					Intent intent = new Intent(Question_04.this, Question_05.class);
					startActivity(intent);
				}
			}
		});
		
		height_previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Question_04.this, Question_03.class);
				startActivity(intent);
			}
		});
	}
}
