package com.example.ifoundyou;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class IFoundYouYourBudActivity extends ListActivity {
	  public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    		    
		    FileHandler handler = new FileHandler();
			String budList = handler.getBudList(getApplicationContext());
			if(budList!=null){
				String[] budListArray = budList.split(":::");
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
		  String item = (String) getListAdapter().getItem(position);
		  Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  }
}