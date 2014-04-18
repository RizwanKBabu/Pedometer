package com.servicess.servicess;

import java.util.Timer;
import java.util.TimerTask;

import com.stat.stat.AppVariable;
import com.temp.temp.Blintcalculation;
import com.temp.temp.Calculation;

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

public class orientation extends Service implements SensorEventListener {

	private SensorManager sensorManager;
	float yaw, pitch, roll;
	Timer timer;
	TimerTask task;
	StringBuilder builder = null;
	int i = 0;


	@Override
	public void onCreate() {
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		@SuppressWarnings("deprecation")
		Sensor sensor = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION)
				.get(0);
		builder = new StringBuilder();
		timer = new Timer();
		starttask();
		timer.schedule(task, 0l, 50);
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		super.onCreate();
	}

	private void starttask() {
		// TODO Auto-generated method stub
		task = new TimerTask() {

			@Override
			public void run() {

				try {
					refreshDisplay();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

	}

	public IBinder onBind(Intent arg0) {

		return null;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int acc) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		yaw = event.values[0];
		pitch = event.values[1];
		roll = event.values[2];

	}

	private void refreshDisplay() throws InterruptedException {
		try {
			String output = String
					.format("yaw is: %f / pitch is: %f / roll is: %f", yaw,
							pitch, roll);

			if (i == 100) {
				Log.d("N", "oriantation : " + output);
				stopSelf();
				PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext())
				.edit().putString("ORIENTATION", builder.toString()).commit();
				builder.delete(0, builder.length());
				Thread.sleep(100);
				
                  if(AppVariable.deter==0){
					
					Calculation get = new Calculation(getApplicationContext());
					get.gravit();
				}else{
					Blintcalculation set=new Blintcalculation(getApplicationContext());
					set.gravit();
				}

				i = 0;
				sensorManager.unregisterListener(this);
			}

			builder.append(String.valueOf(yaw) + "," + String.valueOf(pitch)
					+ "," + String.valueOf(roll) + "#");
			i++;
		} catch (ArrayIndexOutOfBoundsException e) {

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
