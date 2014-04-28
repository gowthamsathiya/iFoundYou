package com.example.ifoundyou;

import com.example.ifoundyou.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class IFoundYouMainActivity extends Activity {

	Button iFoundYouRegisterButton;
	
	public void onDestroy() {
	    //    this.unregisterReceiver(receiver);
	        super.onDestroy();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_main);
		
		iFoundYouRegisterButton = (Button)findViewById(R.id.iFoundYouRegisterButton);
		iFoundYouRegisterButton.setOnClickListener(registerListener);
		
		//Intent mServiceIntent = new Intent(this, SimpleIntentService.class);
		//startService(mServiceIntent);
		
		FileHandler handler = new FileHandler();
		/**
		String l = "awd-*-efwe:::wjj-*-jbwq:::jhwhdwq jhg-*-wqqwf:::hvhjv-*-jgjg ygu";
		handler.putBudList(getApplicationContext(), l);
		**/
		int val = handler.checkCredential(getApplicationContext());
		if(val==0){
			Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
			Intent home = new Intent(this,IFoundYouRegisterActivity.class);
			startActivity(home);
		}
		else
			Toast.makeText(getApplicationContext(), "found", Toast.LENGTH_SHORT).show();
		
		
	}

	private OnClickListener registerListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent registerActivity = new Intent(IFoundYouMainActivity.this, IFoundYouRegisterActivity.class);
			startActivity(registerActivity);
		}
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_main, menu);
		return true;
	}

}
