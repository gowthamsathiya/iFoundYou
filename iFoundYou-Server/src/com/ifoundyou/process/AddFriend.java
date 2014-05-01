package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class AddFriend {
	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;
	private String friendName;
	public AddFriend(IFoundYouData data){
		this.data = data;
	}
	
	public String add() throws SQLException{
		if(verifyFriend()){
			if(!alreadyFriend()){
			try{
				String sql = "insert into friendstable values(?,?)";
				con=Connections.connect();
				ps=con.prepareStatement(sql);
				ps.setString(1,data.getEmail());
				ps.setString(2,data.getFriendEmail());
			//	System.out.println(data.getName()+".."+data.getEmail()+".."+data.getPassword());
				int numb = ps.executeUpdate();
				if(numb == 1){
					return "Friend added successfully ("+friendName+"::"+data.getFriendEmail()+")";
				}
				else
					return "Error adding friend. Try again latter";
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				con.close();
			}
			}
			else{
				return "Already a friend with him";
			}
		}
		else{
			return "No such user found. Suggest your friend iFoundYou";
		}
		return "Error adding friend. Try again latter";
	}

	private boolean verifyFriend() throws SQLException {
		try{
			String sql = "select * from usertable where useremail = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getFriendEmail());
			result = ps.executeQuery();
			
			if(result.next()){
				friendName = result.getString(2);
				return true;
			}
			else return false;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
	
	private boolean alreadyFriend() throws SQLException {
		try{
			String sql = "select * from friendstable where useremail = ? and friendemail = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			ps.setString(2, data.getFriendEmail());
			result = ps.executeQuery();
			
			if(result.next()){
				return true;
			}
			else return false;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		return false;
	}
}
