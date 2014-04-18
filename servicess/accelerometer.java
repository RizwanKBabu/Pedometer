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

public class accelerometer extends Service implements SensorEventListener {
	private SensorManager sensorManager;
	float x, y, z;
	int i = 0;
	StringBuilder builder = null;
	Timer timer;
	TimerTask task;

	@Override
	public void onCreate() {
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Sensor sensor = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER)
				.get(0);

		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		builder = new StringBuilder();
		timer = new Timer();
		starttask();
		timer.schedule(task, 0l, 50);

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

	@Override
	public IBinder onBind(Intent intent) {

		return null;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int acc) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {
			x = event.values[0];
			y = event.values[1];
			z = event.values[2];
		} catch (ArrayIndexOutOfBoundsException e) {

		}

	}

	private void refreshDisplay() throws InterruptedException {
		try {
			String output = String.format("x is: %f / y is: %f / z is: %f", x,
					y, z);

			if (i == 100) {
				
				Log.d("TAG", "TYPE_ACCELEROMETER : " + output);
				stopSelf();
				PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext())
				.edit().putString("ACCELERATION", builder.toString()).commit();
		
	
				builder.delete(0, builder.length());
				Thread.sleep(100);
				
				
				i = 0;
				sensorManager.unregisterListener(this);
				
			}

		
			builder.append(String.valueOf(x) + "," + String.valueOf(y) + ","
						+ String.valueOf(z)+ "#");
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
