package com.example.ifoundyou;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

public class LocationServiceHandler extends IntentService{

	private WifiManager myWifiManager;
	private String currentBSSID = "";

	//private Handler handler = new Handler();
	
	public LocationServiceHandler() {
		super("LocationServiceHandler");
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		myWifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);

		while(true){
			SystemClock.sleep(5000);
		/**
        handler.post(new Runnable()
        {  
//          @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), "File has been saved", 
                    Toast.LENGTH_SHORT).show();
            }
        });
        **/ 
			String connectedWifiBSSID = iFoundYouWifiManager.getBSSID(myWifiManager);
			if(!currentBSSID.equals(connectedWifiBSSID)){
				currentBSSID = connectedWifiBSSID;
				Intent localIntent = new Intent(Constants.BROADCAST_ACTION).addCategory(Intent.CATEGORY_DEFAULT).putExtra(Constants.LOCATION_STATUS, connectedWifiBSSID);
				LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
			}
		}
     }

}
