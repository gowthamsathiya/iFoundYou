package com.example.ifoundyou;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class IFoundYouAddBudActivity extends Activity {

	private EditText emailAddBudEditText;
	private Button addHimButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_add_bud);
		emailAddBudEditText = (EditText) findViewById(R.id.emailAddBudEditText);
		addHimButton = (Button) findViewById(R.id.addHimButton);

		addHimButton.setOnClickListener(buttonListener);
	}
	
	OnClickListener buttonListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			if(!Validate.validateEmail(emailAddBudEditText.getText().toString())){
				Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_SHORT).show();
			}
			else{
				FileHandler handler = new FileHandler();
				String url = "http://ifoundyou.elasticbeanstalk.com/AddAFriend?useremail="+handler.getMyEmail(getApplicationContext()).trim()+"&friendemail="+emailAddBudEditText.getText().toString().trim();
				try {
					Log.d("Add a friend url", url);
					String response = new ConnectToAWS().execute(url).get();
					Log.d("response from add friend", response);
					Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
					if(response.contains("Friend added successfully")){
						String responseArray = (String) response.subSequence(response.indexOf("(")+1,response.length()-2);
						Log.d("Friend name and email", responseArray);
						String friendNamenEmail[] = responseArray.split("::");
						handler.putBud(getApplicationContext(), friendNamenEmail[0], friendNamenEmail[1]);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_add_bud, menu);
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
