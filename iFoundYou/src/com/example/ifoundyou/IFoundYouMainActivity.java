package com.example.ifoundyou;

import com.example.ifoundyou.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class IFoundYouMainActivity extends Activity {

	public void onDestroy() {
	    //    this.unregisterReceiver(receiver);
	        super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_main);
		
		//Intent mServiceIntent = new Intent(this, SimpleIntentService.class);
		//startService(mServiceIntent);
		
		FileHandler handler = new FileHandler();
		int val = handler.checkCredential(getApplicationContext());
		if(val==0){
			Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
			Intent home = new Intent(this,IFoundYouHomeActivity.class);
			startActivity(home);
		}
		else
			Toast.makeText(getApplicationContext(), "found", Toast.LENGTH_SHORT).show();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_main, menu);
		return true;
	}

}
