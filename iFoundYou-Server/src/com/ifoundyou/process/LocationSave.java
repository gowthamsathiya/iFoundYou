package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class LocationSave {
	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	private int numb;
	
	
	public LocationSave(IFoundYouData data){
		this.data = data;
	}
	
	public int addLocation() throws SQLException{
		try{
			String sql = "insert into locationtable values(?,?)";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getBSSID());
			ps.setString(2, data.getLocation());
			numb = ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return numb;
	}
}
