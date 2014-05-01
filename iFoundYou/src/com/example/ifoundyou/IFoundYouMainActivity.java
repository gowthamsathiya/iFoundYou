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
	private Button iFoundYouLoginButton;
	private FileHandler handler;
	
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
		
		iFoundYouLoginButton = (Button)findViewById(R.id.iFoundYouLoginButton);
		iFoundYouLoginButton.setOnClickListener(loginListener);
		
		handler = new FileHandler();
		int val = handler.checkCredential(getApplicationContext());
		if(val==0){
			//Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
			//Intent register = new Intent(this,IFoundYouRegisterActivity.class);
			//startActivity(register);
		}
		else{
			Toast.makeText(getApplicationContext(), "found", Toast.LENGTH_SHORT).show();
			Intent home = new Intent(this,IFoundYouHomeActivity.class);
			startActivity(home); 
			finish();
		}
	}

	private OnClickListener registerListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent registerActivity = new Intent(IFoundYouMainActivity.this, IFoundYouRegisterActivity.class);
			startActivity(registerActivity);
			finish();
		}
		
	};
	
	private OnClickListener loginListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			Intent loginActivity = new Intent(IFoundYouMainActivity.this, IFoundYouLoginActivity.class);
			startActivity(loginActivity);
			finish();
		}
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_main, menu);
		return true;
	}

}
