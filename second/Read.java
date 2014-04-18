package com.second.second;

import java.util.ArrayList;
import java.util.Locale;

import com.stat.stat.AppVariable;
import com.temp.temp.Contact;
import com.temp.temp.dbOptions;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;

public class Read extends Activity implements TextToSpeech.OnInitListener {

	protected static final int RESULT_SPEECH = 1;
	Button h;
	private TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);

		h = (Button) findViewById(R.id.input);
		tts = new TextToSpeech(this, this);

		h.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);

				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Opps! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				Toast.makeText(getApplicationContext(), text.get(0),
						Toast.LENGTH_LONG).show();

				// String[] s=text.get(0).split(" to ");
				// AppVariable.start=s[0];
				// AppVariable.stop=s[1];
				AppVariable.startstop = text.get(0);

				speakOut();

			}
			break;
		}

		}

	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			}
		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	private void speakOut() {
		new Speak().execute();
	}

	private class Speak extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			Contact contact = new dbOptions(getApplicationContext())
					.getway(AppVariable.startstop);

			if (contact != null) {
				String text = contact.s();
				String[] route = text.split(",");
				for (int i = 0; i < route.length; i++) {
					try {
						if (route[i].equalsIgnoreCase("r")) {
							tts.speak("turn right", TextToSpeech.QUEUE_FLUSH,
									null);

							Thread.sleep(2000);

						} else if (route[i].equalsIgnoreCase("l")) {
							tts.speak("turn left", TextToSpeech.QUEUE_FLUSH,
									null);
							Thread.sleep(2000);
						} else {
							tts.speak(route[i] + "steps forwrd",
									TextToSpeech.QUEUE_FLUSH, null);
							Thread.sleep(2000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				tts.speak("no entry", TextToSpeech.QUEUE_FLUSH, null);
			}
			return null;
		}

	}
}
