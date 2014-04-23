package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class LocationUpdate {

	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	
	public LocationUpdate(IFoundYouData data){
		this.data = data;
	}
	
	public void updateLocation() throws SQLException{
		try{
			String sql = "update userlocation set location = ? and time = ? where useremail = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getUserLocation());
			ps.setString(2,data.getTime());
			ps.setString(3,data.getEmail());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
}
