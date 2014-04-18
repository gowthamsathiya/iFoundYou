package com.example.ifoundyou;

import com.example.ifoundyou.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.widget.Toast;

public class IFoundYouHomeActivity extends Activity {
	
	private Intent mServiceIntent;
	private ResponseReceiver mDownloadStateReceiver;
	private IntentFilter statusIntentFilter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_home);
		
		mServiceIntent = new Intent(this, LocationServiceHandler.class);
		startService(mServiceIntent);
		
		statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        mDownloadStateReceiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mDownloadStateReceiver,statusIntentFilter);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_home, menu);
		return true;
	}
	
	
	private class ResponseReceiver extends BroadcastReceiver
	{
	    // Prevents instantiation
	    private ResponseReceiver() {
	    }
	    // Called when the BroadcastReceiver gets an Intent it's registered to receive
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			 String text = arg1.getStringExtra(Constants.LOCATION_STATUS);
			 Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
		}
	}	

}
