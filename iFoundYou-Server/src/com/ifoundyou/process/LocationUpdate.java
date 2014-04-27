package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		if(checkUserLocation()){
			update();
		}
		else{
			addUserLocation();
		}
	}
	public void update() throws SQLException{
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
	
	public boolean checkUserLocation() throws SQLException{
		try{
			String sql = "select * from userlocation where useremail = ?";
			con = Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			ResultSet result = ps.executeQuery();
			if(result.next()){
				return true; //user already present in userlocation table
			}
			else
				return false;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	public void addUserLocation() throws SQLException{
		try{
			String sql = "insert into userlocation values(?,?,?)";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			ps.setString(2,data.getUserLocation());
			ps.setString(3,data.getTime());
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
	}
}
