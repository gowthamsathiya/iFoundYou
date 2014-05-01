package com.example.ifoundyou;

import java.util.concurrent.ExecutionException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class IFoundYouYourBudActivity extends ListActivity {
	  private FileHandler handler;

	public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    		    
		    handler = new FileHandler();
			String budList = handler.getBudList(getApplicationContext());
			if(budList!=null){
				String[] budListArray = budList.split("/-/");
				String[] users = new String[budListArray.length];
				int next=0;
				for(String bList: budListArray){
					users[next] = bList.split("-*-")[0];
					next++;
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users);
				setListAdapter(adapter);
			}
			else{
				String[] users = {"None"};
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, users);
				setListAdapter(adapter);
			}
	  }

	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
		  String personName = (String) getListAdapter().getItem(position);
		 // Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
		  String personEmail = handler.getFriendEmail(getApplicationContext(), personName);
		  //String myLocation = LocationHandler.getLocation(personName);
		  String url = "http://ifoundyou.elasticbeanstalk.com/QueryLocation?useremail="+personEmail;
		  String response;
		try {
			response = new ConnectToAWS().execute(url).get();
			Log.d("friend location url", url);
			Log.d("friend location", response);
			if(!response.equals("null")){
				Intent locationActivity = new Intent(this,IFoundYouFriendLocationActivity.class);
				locationActivity.putExtra(Constants.LAST_FOUND, response.substring(response.indexOf("-*-")+3));
				locationActivity.putExtra(Constants.PERSON_LOCATION, response.substring(0,response.indexOf("-*-")));
				locationActivity.putExtra(Constants.PERSON_NAME, personName);
				startActivity(locationActivity);
			}else{
				Intent locationActivity = new Intent(this,IFoundYouFriendLocationActivity.class);
				locationActivity.putExtra(Constants.LAST_FOUND, "Not found");
				locationActivity.putExtra(Constants.PERSON_LOCATION, "Not found");
				locationActivity.putExtra(Constants.PERSON_NAME, personName);
				startActivity(locationActivity);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		  
	  }
}