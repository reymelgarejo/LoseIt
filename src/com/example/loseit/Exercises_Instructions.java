package com.example.loseit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Exercises_Instructions extends Activity {

	ImageView img;
	String j;
	ListView list;
	TextView text;
	private ArrayAdapter<String> arrayAdapter;    
	String[] exercise = new String[] {"Push Up","Sit Up","Jumping Jack","Bicycle Crunch","Calf Stretch",
			"Hamstring Stretch","Quad Stretch","Side Plank","Leg Lift","Step Up","Lunge"};
	String[] exercise1 = new String[] {"Step 1: Assume a face-down prone position on the floor. Keep your feet together. Your weight should be on your chest."
									, "Step 2: Position hands palms-down on the floor, approximately shoulder width apart."
									, "Step 3: Curl your toes upward (towards your head). The balls of your feet should touch the ground."
									, "Step 4: Raise yourself using your arms."};
	String[] exercise2 = new String[]{"Step1: Lie down on the floor placing your feet either under something that will not move or by having a partner hold them."
									,"Step 2: Place your hands behind your head and lock them together by clasping your fingers. This is the starting position."
									,"Step 3: Elevate your upper body so that it creates an imaginary V-shape with your thighs. Breathe out when performing this part of the exercise."
									,"Step 4: Once you feel the contraction for a second, lower your upper body back down to the starting position while inhaling."
									,"Step 5: Repeat for the recommended amount of repetitions."};
	String[] exercise3 = new String[]{"Step 1: Stand with your arms at your sides. Be sure your feet are straight and close together."
									,"Step 2: Bend your knees. Jump up while spreading your arms and legs at the same time."
									,"Step 3: Clap or touch your hands above your head."
									,"Step 4: As you return from jumping up bring your arms back down to your sides and at the same time bring your feet back together."
									,"Step 5: Continue with as many jumping jack repetitions as you can do."};
	String[] exercise4 = new String[]{"Step 1: Keep a mat on the floor and lie on it."
									,"Step 2: Now keep your hands behind your head and interlock your fingers. Also put your feet in air."
									,"Step 3: Now lift your head up and touch your right elbow to the left knee while pulling your leg up towards your head."
									,"Step 4: Now do the opposite with left elbow and right knee."};
	String[] exercise5 = new String[]{"Step 1: Stand facing the wall with your feet side by side. Place both of your hands against the wall at shoulder level in front of you with your elbows slightly bent"
									,"Step 2: Step back with your right foot. Gently lower your heel to the floor with your toes pointing forward and your left knee slightly bent."
									,"Step 3: Hold the stretch for 15 to 30 seconds. And remember to continue to breath"
									,"Step 4: Return back to your starting position."
									,"Step 5: Repeat the stretch with the opposite leg."
									,"Step 6: Continue to repeat steps 2 through 5 for 3 to 5 minutes."};
	String[] exercise6 = new String[]{"Step 1: Stand tall with your left foot a few inches in front of your right foot and your left toes lifted."
									,"Step 2: Bend your right knee slightly and pull your abdominals gently inward."
									,"Step 3: Lean forward from your hips, and rest both palms on top of your right thigh for balance and support."
									,"Step 4: Keep your shoulders down and relaxed; don’t round your lower back."
									,"Step 5: Repeat the stretch with your right leg forward."};
	String[] exercise7 = new String[]{"Step 1: Stand tall with your feet hip-width apart, pull your abdominals in, and relax your shoulders."
									,"Step 2: Bend your left leg, bringing your heel toward your butt, and grasp your left foot with your right hand."
									,"Step 3: Switch legs and repeat the stretch."};
	String[] exercise8 = new String[]{"Step 1: Lie on your left side, with the whole side of your body on a mat."
									,"Step 2: Use your left elbow to prop up the rest of your body."
									,"Step 3: Tighten your abs and glutes as you hold your pose."
									,"Step 4: For a more challenging pose, lift your hips and prop your weight entirely on your left hand. Bring your elbow off the ground and rest your weight on only your hand."
									,"Step 5: Extend your right arm up into the air and hold the pose."};
	String[] exercise9 = new String[]{"Step 1: Lie flat on your back with your legs stretched out in front of you."
									,"Step 2: Bend your legs and raise them, keeping your toes pointed. Your thighs should be perpendicular to your body, while your shins are parallel."
									,"Step 3: Straighten your legs so that they're pointed at the ceiling. Keep your toes pointed."
									,"Step 4: Slowly lower your legs to about an inch off the floor."
									,"Step 5: Slowly raise your legs back up to the ceiling."};
	String[] exercise10 = new String[]{"Step 1: Place right foot in the middle of step and step up as you balance your body for 1-2 seconds on the right leg."
									,"Step 2: Your left leg should be behind your body to help stabilize your weight as it is shifting."
									,"Step 3: Step down with your left leg first and continue on down with your right."};
	String[] exercise11 = new String[]{"Step 1: Stand with your feet shoulder's width apart, spine long and straight, shoulders back, gaze forward."
									,"Step 2: Step forward with one leg into a wide stance (about one leg's distance between feet) while maintaining spine alignment."
									,"Step 3: Lower your hips until both knees are bent at approximately a 90 degree angle. Your front knee should not extend over your ankle, and your back knee should hover above the ground."
									,"Step 4: Keep your weight in your heels as you push back up to starting position. Repeat on both sides."};
	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.exercises_instructions);
	        
	        img = (ImageView) findViewById(R.id.exercise_image);
	        img.setImageDrawable(null);
	        list = (ListView)findViewById(R.id.instruction_list);
	        text = (TextView)findViewById(R.id.exercise_text);
			
	        Intent intent = getIntent();
	        Bundle b = intent.getExtras();
	        if(b != null)
	        {
	        	j = (String) b.get("sExercise");
	        }
	        
	        if(j.equals("Sit Up"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.sit_up_crunch));
	        	text.setText(exercise[1]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise2);                
	    		list.setAdapter(arrayAdapter);  
	        }
	        else if(j.equals("Push Up"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.push_up));
	        	text.setText(exercise[0]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise1);                
	    		list.setAdapter(arrayAdapter);  
	        }
	        else if(j.equals("Jumping Jack"))
	        {
		       	img.setImageDrawable(getResources().getDrawable(R.drawable.jumping_jacks));
		       	text.setText(exercise[2]);
		       	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise3);                
	    		list.setAdapter(arrayAdapter);  
	        }
	        else if(j.equals("Bicycle Crunch"))
		    {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.bicycle_crunches));
	        	text.setText(exercise[3]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise4);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Calf Stretch"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.calf_stretch));
	        	text.setText(exercise[4]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise5);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Hamstring Stretch"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.hamstring_stretch));
	        	text.setText(exercise[5]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise6);                
	    		list.setAdapter(arrayAdapter);
	        }
	        
	        else if(j.equals("Quad Stretch"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.quad_stretch));
	        	text.setText(exercise[6]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise7);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Side Plank"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.side_plank));
	        	text.setText(exercise[7]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise8);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Leg Lift"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.leg_lifts));
	        	text.setText(exercise[8]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise9);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Step Up"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.step_ups));
	        	text.setText(exercise[9]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise10);                
	    		list.setAdapter(arrayAdapter);
	        }
	        else if(j.equals("Lunge"))
	        {
	        	img.setImageDrawable(getResources().getDrawable(R.drawable.lunges));
	        	text.setText(exercise[10]);
	        	arrayAdapter = new ArrayAdapter<String>(this, R.layout.exercises_list, exercise11);                
	    		list.setAdapter(arrayAdapter);
	        }
	  }
}
