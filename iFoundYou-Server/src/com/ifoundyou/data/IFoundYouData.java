package com.ifoundyou.data;

public class IFoundYouData {
	private String name;
	private String email;
	private String password;
	private String friendsList;
	private String userLocation;
	private String time;
	private String bssid;
	private String location;
	private String friendemail;
	
	public static final String URL = "jdbc:mysql://localhost:3306/ifoundyou";
	public static final String user= "root";
	public static final String dbpassword= "root";
    public static final String DRIVER="com.mysql.jdbc.Driver";
    
    public static String getURL(){
    	String dbName = System.getProperty("RDS_DB_NAME"); 
    	String userName = System.getProperty("RDS_USERNAME"); 
    	String awspassword = System.getProperty("RDS_PASSWORD"); 
    	String hostname = System.getProperty("RDS_HOSTNAME");
		String port = System.getProperty("RDS_PORT");
	
		String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + awspassword;
    
		return jdbcUrl;
    }
    
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

	public String getBSSID() {
		return bssid;
	}

	public void setBSSID(String bssid){
		this.bssid = bssid;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}

	public String getFriendEmail() {
		return friendemail;
	}

	public void setFriendEmail(String friendemail) {
		this.friendemail = friendemail;
	}
}
