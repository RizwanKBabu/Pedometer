package com.second.second;

import com.servicess.servicess.accelerometer;
import com.servicess.servicess.gravity;
import com.servicess.servicess.orientation;
import com.stat.stat.AppVariable;
import com.temp.temp.Calorie;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Normal extends Activity implements OnClickListener{
	
	
	Button x;
	EditText height;
	EditText weight;
	int flag=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);
		
		x = (Button) findViewById(R.id.b);
		x.setOnClickListener(this);
		height=(EditText) findViewById(R.id.editTex);
        weight=(EditText) findViewById(R.id.ed);
		
	
	}

	
	
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		
		if(height.getText().toString().equals("")){
			height.setError("enter your height");
		}else if (weight.getText().toString().equals("")) {
			weight.setError("enter your height");
		}else{
            AppVariable.height=height.getText().toString();
            AppVariable.weight=weight.getText().toString();
		if (flag == 0) {

	//		startService(new Intent(Normal.this,gravity.class));
			startService(new Intent(Normal.this, accelerometer.class));
     		startService(new Intent(Normal.this, orientation.class));
						x.setText("stop");
			flag = 1;
		} else {
			flag = 0;
			x.setText("start");
	//		stopService(new Intent(Normal.this,gravity.class));
			stopService(new Intent(Normal.this, accelerometer.class));
        	stopService(new Intent(Normal.this, orientation.class));
		
			Toast.makeText(getApplicationContext(),""+AppVariable.st,Toast.LENGTH_LONG).show();
			
			float calorie=new Calorie(getApplicationContext()).calc();
			
			Toast.makeText(getApplicationContext(),""+calorie,Toast.LENGTH_LONG).show();
			
			AppVariable.st=0;
		}
		}

	}

}
