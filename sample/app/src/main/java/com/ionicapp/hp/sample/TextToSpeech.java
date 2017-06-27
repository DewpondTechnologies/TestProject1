package com.ionicapp.hp.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

@SuppressWarnings("ALL")
public class TextToSpeech extends Activity implements android.speech.tts.TextToSpeech.OnInitListener
{
	/** Called when the activity is first created. */

	private android.speech.tts.TextToSpeech tts;
	private Button btnSpeak;
	private EditText txtText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text_to_speech);

		tts = new android.speech.tts.TextToSpeech(this, this);

		btnSpeak = (Button) findViewById(R.id.btnSpeak);

		txtText = (EditText) findViewById(R.id.txtText);

		// button on click event
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				speakOut();
			}

		});
	}

	@Override
	public void onDestroy()
	{
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onInit(int status)
	{
		// TODO Auto-generated method stub

		if (status == android.speech.tts.TextToSpeech.SUCCESS)
		{
			int result = tts.setLanguage(Locale.US);

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); // set speech speed rate

			if (result == android.speech.tts.TextToSpeech.LANG_MISSING_DATA
					|| result == android.speech.tts.TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			}
			else
				{
				btnSpeak.setEnabled(true);
				speakOut();
				}

		} else {
			Log.e("TTS", "Initilization Failed");
		}

	}

	private void speakOut()
	{
		String text = txtText.getText().toString();
		tts.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null);
	}
}