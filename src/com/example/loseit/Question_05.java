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

public class Question_05 extends Activity {
	
	private EditText weight_edittext;
	private ImageView weight_next;
	private ImageView weight_previous;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_05);
		
		weight_edittext = (EditText) findViewById(R.id.weight_edittext);
		weight_next = (ImageView) findViewById(R.id.weight_next);
		weight_previous = (ImageView) findViewById(R.id.weight_previous);
		
		weight_next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String FILENAME = "Weight";
				String name = weight_edittext.getText().toString();
				
				if(weight_edittext.getText().toString().trim().equals("")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Question_05.this);
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
					
					Intent intent = new Intent(Question_05.this, MainActivity.class);
					startActivity(intent);
				}
			}
		});
		
		weight_previous.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Question_05.this, Question_04.class);
				startActivity(intent);
			}
		});
	}
}
