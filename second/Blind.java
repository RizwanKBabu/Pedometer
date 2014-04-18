package com.second.second;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Blind extends Activity implements OnClickListener{
	Button d;
	Button e;
	Button k;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blind);
		
		d=(Button) findViewById(R.id.bu);
		e=(Button) findViewById(R.id.but);
		k=(Button) findViewById(R.id.delete);
		d.setOnClickListener(this);
		e.setOnClickListener(this);
		k.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blind, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
if(v.getId()==e.getId()){
			
			startActivity(new Intent(getApplicationContext(),Read.class));
		}else if (v.getId()==d.getId()) {
			
			startActivity(new Intent(getApplicationContext(),Write.class));
			
		}else{
			startActivity(new Intent(getApplicationContext(),DeleteMainActivity.class));
		}
	}

}
