package com.example.ifoundyou;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class iFoundYouWifiManager {

	public static String getBSSID(WifiManager myWifiManager){
        WifiInfo myWifiInfo = myWifiManager.getConnectionInfo();
		return myWifiInfo.getBSSID(); 
	}
}
