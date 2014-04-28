package com.example.ifoundyou;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
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

	
	@SuppressWarnings("unused")
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
			try {
				FileHandler handler = new FileHandler();
				String email = handler.getMyEmail(getApplicationContext());
				String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				String url = "ifoundyou.elasticbeanstalk.com/LocationChange?useremail="+email.trim()+"&location="+connectedWifiBSSID.trim()+"&time="+time.trim();
				String location = new ConnectToAWS().execute(url).get();
				if(!currentBSSID.equals(connectedWifiBSSID)){
					if(connectedWifiBSSID!=null){
						currentBSSID = connectedWifiBSSID;
						Intent localIntent = new Intent(Constants.BROADCAST_ACTION).addCategory(Intent.CATEGORY_DEFAULT).putExtra(Constants.LOCATION_STATUS, location);
						LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
					}
					else{
						Intent localIntent = new Intent(Constants.BROADCAST_ACTION).addCategory(Intent.CATEGORY_DEFAULT).putExtra(Constants.LOCATION_STATUS, "Not connected to Wifi :(");
						LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
     }

}
