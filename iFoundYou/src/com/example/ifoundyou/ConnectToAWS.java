package com.example.ifoundyou;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class ConnectToAWS extends AsyncTask<String,String,String>{

	private String responseString;

	@Override
	protected String doInBackground(String... arg0) {
		HttpClient httpClient = new DefaultHttpClient();
		/**
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		nameValuePairs.add(new BasicNameValuePair("useremail",emailEditText.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("name",nameEditText.getText().toString()));
		nameValuePairs.add(new BasicNameValuePair("password",passwordEditText.getText().toString() ));
		
		String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
		Toast.makeText(getApplicationContext(), url + "?" + paramsString, Toast.LENGTH_SHORT).show();
		**/
		HttpGet httpGet = new HttpGet(arg0[0]);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			responseString = new BasicResponseHandler().handleResponse(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseString;
	}
	
	protected void onPostExecute(String result) {
	    super.onPostExecute(result);
	}

}
