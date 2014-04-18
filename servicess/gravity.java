package com.servicess.servicess;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.stat.stat.AppVariable;
import com.temp.temp.Blintcalculation;
import com.temp.temp.Calculation;

public class gravity extends Service implements SensorEventListener {

	private SensorManager sensorManager;
	float first, second, third;

	Timer timer;
	TimerTask task;
	StringBuilder builder = null;
	int i = 0;
	
	@Override
	public void onCreate() {
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		Sensor sensor = sensorManager.getSensorList(Sensor.TYPE_GRAVITY).get(0);

		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		builder = new StringBuilder();

		timer = new Timer();
		starttask();
		timer.schedule(task, 0l, 50);

		super.onCreate();
	}

	public IBinder onBind(Intent arg0) {

		return null;
	}

	private void starttask() {
		// TODO Auto-generated method stub
		task = new TimerTask() {

			@Override
			public void run() {

				refreshDisplay();
			}
		};

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int acc) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {
			first = event.values[0];
			second = event.values[1];
			third = event.values[2];
		} catch (ArrayIndexOutOfBoundsException e) {

		}

	}

	private void refreshDisplay() {
		try {
			String output = String.format(
					"first is: %f / second is: %f / third is: %f", first,
					second, third);

			if (i == 100) {
				
				Log.d("TAG", "gravity : " + output);
				stopSelf();
			
				PreferenceManager
						.getDefaultSharedPreferences(getApplicationContext())
						.edit().putString("HELLO", builder.toString()).commit();
				Thread.sleep(100);
				
		    	
				builder.delete(0, builder.length());
			
				
				

				i = 0;
				sensorManager.unregisterListener(this);

			}
		
			builder.append(String.valueOf(first) + "," + String.valueOf(second)
					+ "," + String.valueOf(third) + "#");
			i++;
		} catch (ArrayIndexOutOfBoundsException e) {

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		timer.cancel();
		task.cancel();
		timer = null;
		task = null;
		super.onDestroy();
	} 

}
