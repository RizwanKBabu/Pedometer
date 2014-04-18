package com.second.second;

import com.servicess.servicess.accelerometer;
import com.servicess.servicess.gravity;
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

public class Write extends Activity {
	EditText start;
	EditText stop;
	Button beg;
	int flag=0;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);
		start=(EditText) findViewById(R.id.Text1);
		stop=(EditText) findViewById(R.id.Text2);
		beg=(Button) findViewById(R.id.begin);
		
		beg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (start.getText().toString().equals("")) {
					start.setError("Enter the starting point");
				} else if (stop.getText().toString().equals("")) {
					stop.setError("Enter the stamping point");
				} else {
				
					AppVariable.start=start.getText().toString();
					AppVariable.stop=stop.getText().toString();
					
					boolean exist=new dbOptions(getApplicationContext()).checking(start.getText().toString(),stop.getText().toString());
					
					if(exist==false)
					{
					if (flag == 0) {

			//			startService(new Intent(Write.this,gravity.class));
						startService(new Intent(Write.this, accelerometer.class));
						startService(new Intent(Write.this, orientation.class));
						
						beg.setText("end");
						flag = 1;
					} else {
						flag = 0;
					  beg.setText("begin");
				//		stopService(new Intent(Write.this,gravity.class));
						stopService(new Intent(Write.this, accelerometer.class));
						stopService(new Intent(Write.this, orientation.class));
						
                        if(AppVariable.res!=null){
						Toast.makeText(getApplicationContext(),AppVariable.res,Toast.LENGTH_LONG).show();
						boolean rsp = new dbOptions(getApplicationContext()).insertway();
					
						if (rsp) {
							Toast.makeText(getApplicationContext(),
									"Inserted Succesfull", Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(getApplicationContext(), "Insertion failed",
									Toast.LENGTH_SHORT).show();
						}
						AppVariable.res=",";
                        }else {
                        	Toast.makeText(getApplicationContext(),"no steps",Toast.LENGTH_LONG).show();	
						}
					}}else{
						Toast.makeText(getApplicationContext(),"already exists",Toast.LENGTH_LONG).show();
					}
				}
			}

			
		});
		
		



	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.write, menu);
		return true;
	}

}
