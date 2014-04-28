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


public class IFoundYouLoginActivity extends Activity {
	private EditText nameEditText;
	private EditText passwordEditText;
	private Button loginbutton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ifound_you_login);
		nameEditText = (EditText)findViewById(R.id.emailLoginEditText);
		passwordEditText=(EditText)findViewById(R.id.passwordLoginEditText);
		loginbutton=(Button)findViewById(R.id.loginButton);
		loginbutton.setOnClickListener(loginButtonListener);
	}

	
	OnClickListener loginButtonListener=new OnClickListener()
	{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String url ="http://ifoundyou.elasticbeanstalk.com/Authentication?useremail="+nameEditText.getText().toString()+"&password="+passwordEditText.getText().toString();
try

{
	String resultString = new ConnectToAWS().execute(url).get();
	if(resultString.equals("USRNF")){
		Toast.makeText(getApplicationContext(), "Invalid Username! try register", Toast.LENGTH_SHORT).show();

	}else if (resultString.equals("NFRNDS")){
		
	}else {
		FileHandler fh= new FileHandler();
		fh.putBudList(getApplicationContext(), resultString);
		Intent navhome= new Intent(getApplicationContext(),IFoundYouHomeActivity.class);
		startActivity(navhome);
	
	}

} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (ExecutionException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
finally
{
}
}

		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ifound_you_login, menu);
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
