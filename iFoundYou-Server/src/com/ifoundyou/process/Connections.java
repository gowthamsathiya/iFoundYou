package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ifoundyou.data.IFoundYouData;



 public class Connections {

	
	public static Connection connect()
	{
		Connection con=null;
		try {
			
			
			Class.forName(IFoundYouData.DRIVER);
			 con=DriverManager.getConnection(IFoundYouData.URL);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
}

}

