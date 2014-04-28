package com.example.ifoundyou;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class IFoundYouFriendLocationActivity extends Activity {

	String personName;
	String personLocation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			personName = extras.getString(Constants.PERSON_NAME);
			personLocation = extras.getString(Constants.PERSON_LOCATION);
		}
		setContentView(R.layout.activity_ifound_you_friend_location);
		
		TextView personNameTextView = (TextView)findViewById(R.id.personNameTextView);
		TextView personLocationTextView = (TextView)findViewById(R.id.personLocationTextView);
		
		personNameTextView.setText(personName);
		personLocationTextView.setText(personLocation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_friend_location, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
