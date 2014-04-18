package com.second.second;

import com.stat.stat.AppVariable;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondMainActivity extends Activity implements OnClickListener{

	Button b;
	Button c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_main);
		b=(Button) findViewById(R.id.butt);
		c=(Button) findViewById(R.id.button2);
		b.setOnClickListener(this);
		c.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
	      
		if(v.getId()==b.getId()){
			AppVariable.deter=0;
			startActivity(new Intent(getApplicationContext(),Normal.class));
			
		}else if (v.getId()==c.getId()) {
			
			AppVariable.deter=1;
			startActivity(new Intent(getApplicationContext(),Blind.class));
			
			
		}
		
		
	}

}
