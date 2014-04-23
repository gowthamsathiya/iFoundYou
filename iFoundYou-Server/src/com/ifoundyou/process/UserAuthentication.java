package com.ifoundyou.process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ifoundyou.data.IFoundYouData;

public class UserAuthentication {
	IFoundYouData data;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;
	private ResultSet friendresult;
	String friendsList="";
	private ResultSet friendnameresult;
	
	public UserAuthentication(IFoundYouData data){
		this.data = data;
	}
	
	public IFoundYouData checkUser() throws SQLException{
		try{
			String sql = "select * from usertable where useremail = ? and password = ?";
			con=Connections.connect();
			ps=con.prepareStatement(sql);
			ps.setString(1,data.getEmail());
			ps.setString(2,data.getPassword());
			result = ps.executeQuery();
			
			if(result.next()){
				String friendlist = "select * from friendstable where useremail = ?";
				ps=con.prepareStatement(friendlist);
				ps.setString(0, data.getEmail());
				friendresult = ps.executeQuery();
				if(friendresult.isBeforeFirst()){
					while(friendresult.next()){
						String friendemail = friendresult.getString(2);
						String friendnamesql = "select name from usertable where useremail = ?";
						String friendname="";
						ps = con.prepareStatement(friendnamesql);
						ps.setString(0, friendemail);
						friendnameresult = ps.executeQuery();
						if(friendnameresult.next()){
							friendname = friendnameresult.getString(1);
						}
						friendsList+=friendname+"-*-"+friendemail+"/-/";
					}
					friendsList = friendsList.substring(0,friendsList.length()-3);
					data.setFriendsList(friendsList);
				}
				else{
					friendsList = "NFRNDS";
					data.setFriendsList(friendsList);
				}
				
			}
			else{
				friendsList = "USRNF";
				data.setFriendsList(friendsList);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			con.close();
		}
		
		return data;
	}
}
