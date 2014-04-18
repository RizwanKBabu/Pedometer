package com.second.second;

import com.servicess.servicess.accelerometer;
import com.servicess.servicess.orientation;
import com.stat.stat.AppVariable;
import com.temp.temp.dbOptions;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteMainActivity extends Activity {

	
	EditText start;
	EditText stop;
	Button beg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_main);
		
		start=(EditText) findViewById(R.id.beginss);
		stop=(EditText) findViewById(R.id.endss);
		beg=(Button) findViewById(R.id.shoot);
		
		
		
		beg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (start.getText().toString().equals("")) {
					start.setError("Enter the starting point");
				} else if (stop.getText().toString().equals("")) {
					stop.setError("Enter the stamping point");
				} else {
				
					
					
					boolean exist=new dbOptions(getApplicationContext()).deleting(start.getText().toString(),stop.getText().toString());
					
					if(exist==false)
					{
					
						Toast.makeText(getApplicationContext(),"deletion failed",Toast.LENGTH_LONG).show();
					}else {
                        	Toast.makeText(getApplicationContext(),"deletion success",Toast.LENGTH_LONG).show();	
						}
					}
				}
				
			

			
		}
		);
		
		
	}

	

}
