package com.example.ifoundyou;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

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
			String connectedWifiBSSID = iFoundYouWifiManager.getBSSID(myWifiManager);
			try {
				FileHandler handler = new FileHandler();
				String email = handler.getMyEmail(getApplicationContext());
				//String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd(HH:mm:ss)");
				String time = dformat.format(new Date());
				String url = "http://ifoundyou.elasticbeanstalk.com/LocationChange?useremail="+email.trim()+"&location="+connectedWifiBSSID.trim()+"&time="+time.trim();
				Log.d("location change url", url);
				String location = new ConnectToAWS().execute(url).get();
				Log.d("response location of me", location);
				//Toast.makeText(this, "response from url : "+location, Toast.LENGTH_SHORT).show();
				//if(!currentBSSID.equals(connectedWifiBSSID)){
					if(connectedWifiBSSID!=null){
						currentBSSID = connectedWifiBSSID;
						Intent localIntent = new Intent(Constants.BROADCAST_ACTION).addCategory(Intent.CATEGORY_DEFAULT).putExtra(Constants.LOCATION_STATUS, location);
						LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
					}
					else{
						Intent localIntent = new Intent(Constants.BROADCAST_ACTION).addCategory(Intent.CATEGORY_DEFAULT).putExtra(Constants.LOCATION_STATUS, "Not connected to Wifi :(");
						LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
					}
				//}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
     }

}
