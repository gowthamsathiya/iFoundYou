package com.example.ifoundyou;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class FileHandler {

	public String credFileName = "iFoundYouCredentials";
	public String budFileName = "iFoundYouBud";

	/**
	private void createCredFile(String username, String password, Context ctx){
		try{
			String data = username+";;"+password;
			FileOutputStream fos = ctx.openFileOutput(credFileName,Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private int checkCredFile(Context ctx){
		try{
			FileInputStream fis = ctx.openFileInput(credFileName);
			return 1;
		}catch(FileNotFoundException e){
			return 0;
		}
	}
	
	private String readCredFile(Context ctx){
		try{
			FileInputStream fis = ctx.openFileInput(credFileName);
			String credData = new String(null, fis.read(), 0, "UTF-8");
			return 1;
		}catch(FileNotFoundException e){
			return 0;
		}
	}
	**/
	
	public void putCredential(Context ctx, String username, String password){
		String data = username+";;"+password;
		SharedPreferences cred = ctx.getSharedPreferences(credFileName, 0); 
		Editor editor = cred.edit();
		editor.putString("credValue", data);
		editor.commit();
	}
	

	public int checkCredential(Context ctx){
		SharedPreferences cred = ctx.getSharedPreferences(credFileName, 0); 
		String creddata = cred.getString("credValue", null);
		if(creddata!=null)
			return 1; //credentials found
		else
			return 0; //credentials not found
	}
	

	public String getCredential(Context ctx){
		SharedPreferences cred = ctx.getSharedPreferences(credFileName, 0); 
		String creddata = cred.getString("credValue", null);
		if(creddata!=null)
			return creddata;
		else
			return null;
	}
	
	public void putBud(Context ctx, String username, String email){
		String data = username+"-*-"+email; // each username & corresponding email is seperated by -*-
		SharedPreferences cred = ctx.getSharedPreferences(budFileName, 0);
		String budListData = cred.getString("budValue", null);
		if(budListData!=null){			
			Editor editor = cred.edit();
			editor.putString("budValue", budListData+":::"+data); //each user is sperated by :::
			editor.commit();
		}
		else{
			Editor editor = cred.edit();
			editor.putString("budValue", data);
			editor.commit();
		}
	}
	
	public void putBudList(Context ctx, String list){
		SharedPreferences cred = ctx.getSharedPreferences(budFileName, 0); 
		Editor editor = cred.edit();
		editor.putString("budValue", list); //each user is sperated by :::
		editor.commit();
	}
	
	public String getBudList(Context ctx){
		SharedPreferences cred = ctx.getSharedPreferences(budFileName, 0); 
		String budListData = cred.getString("budValue", null);
		if(budListData!=null)
			return budListData;
		else
			return null;
	}
}
