package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class UserRegister {
	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;
	
	public UserRegister(IFoundYouData data){
		this.data = data;
	}
	
	public int registerUser() throws SQLException{
		try {

			if(!checkUser()){
				String sql = "insert into usertable values(?,?,?)";
				con=Connections.connect();
		
				ps=con.prepareStatement(sql);
				ps.setString(1,data.getName());
				ps.setString(2,data.getEmail());
				ps.setString(3,data.getPassword());
				ps.executeUpdate();
				
				return 1; //registered successfully
			}
			else{
				return 0; //already present
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			con.close();
		}
		return 0;
	}
	
	public boolean checkUser() throws SQLException{
		try{
			String sql = "select * from usertable where useremail = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			result = ps.executeQuery(sql);
			
			if(result.next()){
				return true; 
			}
			else{
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return false;
		
	}

}
