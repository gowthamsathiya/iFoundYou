package com.example.ifoundyou;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IFoundYouHomeActivity extends Activity {
	
	private Intent mServiceIntent;
	private ResponseReceiver mDownloadStateReceiver;
	private IntentFilter statusIntentFilter;
	private static TextView currentLocationTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_home);
		currentLocationTextView = (TextView) findViewById(R.id.currentLocationTextView);
		ImageView budListImageView = (ImageView) findViewById(R.id.budListImageView);
		ImageView addbudlist=(ImageView) findViewById(R.id.addBudImageView);
		Button removeButton = (Button) findViewById(R.id.removeButton);
		removeButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				FileHandler hand = new FileHandler();
				hand.removeSP(getApplicationContext());
			}
		});
		budListImageView.setOnClickListener(budlistimage);
		addbudlist.setOnClickListener(addbudimage);
		mServiceIntent = new Intent(this, LocationServiceHandler.class);
		startService(mServiceIntent);
		
		statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION);
        statusIntentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        mDownloadStateReceiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mDownloadStateReceiver,statusIntentFilter);
        
	}

	
	private OnClickListener budlistimage = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent Navbudlist= new Intent(getApplicationContext(),IFoundYouYourBudActivity.class);
			startActivity(Navbudlist);
		}
	};
	
	private OnClickListener addbudimage = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent navaddbud= new Intent(getApplicationContext(),IFoundYouAddBudActivity.class);
			startActivity(navaddbud);
		}

	};
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
			 IFoundYouHomeActivity.currentLocationTextView.setText(text);
		}
	}	

}
