package com.example.ifoundyou;

import java.util.concurrent.ExecutionException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class IFoundYouRegisterActivity extends Activity {


	private EditText nameEditText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText cPasswordEditText;
	private Button registerbutton;
	private Intent navigateIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_register);
		
		nameEditText = (EditText)findViewById(R.id.nameEditText);
		emailEditText = (EditText)findViewById(R.id.emailEditText);
		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
		cPasswordEditText = (EditText)findViewById(R.id.cPasswordEditText);
		registerbutton = (Button)findViewById(R.id.registerButton);
		
		registerbutton.setOnClickListener(registerButtonListener);
		
		
	}

	OnClickListener registerButtonListener = new OnClickListener(){
		boolean valid = true;
		@Override
		public void onClick(View arg0) {
			if(!Validate.comparePassword(passwordEditText.getText().toString(), cPasswordEditText.getText().toString())){
				valid = false;
				Toast.makeText(getApplicationContext(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();
			}
			if(!Validate.validateName(nameEditText.getText().toString())){
				valid = false;
				Toast.makeText(getApplicationContext(), "Name is invalid", Toast.LENGTH_SHORT).show();
			}
			if(!Validate.validateEmail(emailEditText.getText().toString())){
				valid = false;
				Toast.makeText(getApplicationContext(), "Email id invalid", Toast.LENGTH_SHORT).show();
			}
			if(!Validate.validatePassword(passwordEditText.getText().toString())){
				valid = false;
				Toast.makeText(getApplicationContext(), "Password should contain atleast one numeric, lower case and upper case character", Toast.LENGTH_SHORT).show();
			}
			if(valid){
				String url ="http://ifoundyou.elasticbeanstalk.com/Register?useremail="+emailEditText.getText().toString()+"&name="+nameEditText.getText().toString()+"&password="+passwordEditText.getText().toString();
				
				try {
					String resultString = new ConnectToAWS().execute(url).get();
					Toast.makeText(getApplicationContext(), resultString, Toast.LENGTH_SHORT).show();
					if(resultString.contains("Registration successfull"))
					{
						FileHandler fh= new FileHandler();
						fh.putCredential(getApplicationContext(), emailEditText.getText().toString(), passwordEditText.getText().toString());
						navigateIntent = new Intent(getApplicationContext(),IFoundYouHomeActivity.class);
						startActivity(navigateIntent);
					}else if(resultString.contains("User already registered"))
					{
						Toast.makeText(getApplicationContext(),"User already registered! try login", Toast.LENGTH_SHORT).show();

					}else
					{
						Toast.makeText(getApplicationContext(),"Some problem with server. Try again after sometime pls", Toast.LENGTH_SHORT).show();

					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_register, menu);
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
