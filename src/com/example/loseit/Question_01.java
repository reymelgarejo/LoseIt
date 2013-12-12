package com.example.loseit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Question_01 extends Activity {

	private EditText name_edittext;
	private ImageView name_next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_01);

		String filename1 = "Name";
		StringBuffer stringBuffer1 = new StringBuffer();
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(openFileInput(filename1)));
			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				Intent intent = new Intent(Question_01.this, MainActivity.class);
				startActivity(intent);
				Question_01.this.finish();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		name_edittext = (EditText) findViewById(R.id.name_edittext);
		name_next = (ImageView) findViewById(R.id.name_next);
		
		name_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String FILENAME = "Name";
				String name = name_edittext.getText().toString();

				if(name_edittext.getText().toString().trim().equals("")){
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Question_01.this);
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

						Toast.makeText(getApplicationContext(),
								FILENAME + " saved", Toast.LENGTH_LONG).show();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					Intent intent = new Intent(Question_01.this, Question_02.class);
					startActivity(intent);
				}
				
			}
		});
	}
}
