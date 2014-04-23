package com.ifoundyou.data;

public class IFoundYouData {
	private String name;
	private String email;
	private String password;
	private String friendsList;
	private String userLocation;
	private String time;
	
	public static final String URL = "jdbc:mysql://localhost/ifu?" +"user=root&password=root";
    public static final String DRIVER="com.mysql.jdbc.Driver";
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFriendsList() {
		return friendsList;
	}
	public void setFriendsList(String friendsList) {
		this.friendsList = friendsList;
	}
	public String getUserLocation(){
		return userLocation;
	}
	public void setUserLocation(String userLocation){
		this.userLocation = userLocation;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String time){
		this.time = time;
	}
}
