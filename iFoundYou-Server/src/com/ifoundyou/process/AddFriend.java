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
	
	public AddFriend(IFoundYouData data){
		this.data = data;
	}
	
	public String add() throws SQLException{
		if(verifyFriend()){
			try{
				String sql = "insert into friendstable values(?,?)";
				con=Connections.connect();
				ps=con.prepareStatement(sql);
				ps.setString(2,data.getFriendEmail());
				ps.setString(1,data.getEmail());
			//	System.out.println(data.getName()+".."+data.getEmail()+".."+data.getPassword());
				int numb = ps.executeUpdate();
				if(numb == 1){
					return "Friend added successfully";
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
