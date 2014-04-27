package com.ifoundyou.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifoundyou.process.Connections;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PreparedStatement ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		//out.println("From get method: success");
		Connection con=null;
		try {
			 con=Connections.connect();
			 
			 /**
			 String userTableSQL = "create table usertable(useremail varchar(50) primary key, name varchar(50),password varchar(50))";
			 String locationTableSQL = "create table locationtable(locationid int primary key, locationname varchar(100))";
			 String userLocationSQL = "create table userlocation(useremail varchar(50) primary key, lastlocation int ,  lasttimefound varchar(20), foreign key(useremail) references usertable(useremail), foreign key(lastlocation) references locationtable(locationid))";
			 String friendsTableSQL = "create table friendstable(useremail varchar(50), friendemail varchar(50), foreign key(useremail) references usertable(useremail), foreign key(friendemail) references usertable(useremail))";
			 
			 ps = con.prepareStatement(userTableSQL);
			 out.println("user table prepared successfully");
			 if(ps.execute()) out.println("user table created successfully");
			 
			 ps = con.prepareStatement(locationTableSQL);
			 out.println("location table prepared successfully");
			 if(ps.execute()) out.println("location table created successfully");
			 
			 ps = con.prepareStatement(userLocationSQL);
			 out.println("user location prepared successfully");
			 if(ps.execute()) out.println("user location created successfully");
			 
			 ps = con.prepareStatement(friendsTableSQL);
			 out.println("friends table prepared successfully");
			 if(ps.execute()) out.println("friends table created successfully");
			 **/
			 
			 /**
			 String insertTableSQL = "insert into usertable values(\"gowtham@gmail.com\",\"gowtham\",\"gowtham\")";
			 String insertLocationSQL = "insert into locationtable values(12,\"UC\")";
			 Date d = new Date();
			 String insertULSQL = "insert into userlocation values(\"gowtham@gmail.com\",12,\""+d.getDate()+"\"";
			 
			 ps = con.prepareStatement(insertTableSQL);
			 int i = ps.executeUpdate();
			 ps = con.prepareStatement(insertLocationSQL);
			 int j = ps.executeUpdate();
			 ps = con.prepareStatement(insertULSQL);
			 int k = ps.executeUpdate();
			 
			 out.println(i+j+k+" rows inserted");
			 **/
			 /**
			 String selectUT = "select * from usertable";
			 String locationUT = "select * from locationtable";
			 String ul = "select * from userlocation";
			 
			 ps = con.prepareStatement(selectUT);
			 ResultSet rs1 = ps.executeQuery();
			 while(rs1.next()){
				 out.println(rs1.getString(1)+"--"+rs1.getInt(2)+"--"+rs1.getString(3));
			 }

			 ps = con.prepareStatement(locationUT);
			 ResultSet rs2 = ps.executeQuery();
			 while(rs2.next()){
				 out.println(rs2.getInt(1)+"--"+rs2.getString(2));
			 }
			 ps = con.prepareStatement(ul);
			 ResultSet rs3 = ps.executeQuery();
			 while(rs3.next()){
				 out.println(rs3.getString(1)+"--"+rs3.getInt(2)+"--"+rs3.getString(3));
			 }
			 
			 **/
			 /**
			 PreparedStatement dropul = con.prepareStatement("drop table userlocation");
			 dropul.execute();
			 
			 PreparedStatement droplt = con.prepareStatement("drop table locationtable");
			 droplt.execute();
			 
			 
			 
			 ps = con.prepareStatement("show tables");
			 ResultSet rs4 = ps.executeQuery();
			 while(rs4.next()){
				 out.println("After droping");
				 out.println(rs4.getString(1));
			 }
			 **/
			 String locationTableSQL = "create table locationtable(locationid varchar(50) primary key, locationname varchar(100))";
			 String userLocationSQL = "create table userlocation(useremail varchar(50) primary key, lastlocation varchar(50) ,  lasttimefound varchar(20), foreign key(useremail) references usertable(useremail), foreign key(lastlocation) references locationtable(locationid))";
			
			 PreparedStatement createlt = con.prepareStatement(locationTableSQL);
			 out.println("location table prepared successfully");
			 if(createlt.execute()) out.println("location table created successfully");
			 
			 PreparedStatement createul = con.prepareStatement(userLocationSQL);
			 out.println("user location prepared successfully");
			 if(createul.execute()) out.println("user location created successfully");
			
			 ps = con.prepareStatement("show tables");
			 ResultSet rs5 = ps.executeQuery();
			 while(rs5.next()){
				 out.println("After Adding");
				 out.println(rs5.getString(1));
			 }		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				out.println("SQL Exception");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("From post method: success");
	}

}
