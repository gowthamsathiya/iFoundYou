package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class FriendLocation {
	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;
	
	public FriendLocation(IFoundYouData data){
		this.data = data;
	}
	
	public IFoundYouData checkLocation() throws SQLException{
		try{
			String sql = "select * from userlocation where useremail = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			result = ps.executeQuery();
			
			while(result.next()){
				String lastLocation = result.getString(1);
				String lastTimeFound = result.getString(2);
				data.setUserLocation(lastLocation+"-*-"+lastTimeFound);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return data;
	}
}
