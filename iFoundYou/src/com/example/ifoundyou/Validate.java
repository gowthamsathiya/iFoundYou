package com.example.ifoundyou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String NAME_PATTERN = 
			"^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
	private static final String PASSWORD_PATTER =
			"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	
	@SuppressWarnings("unused")
	private static Pattern pattern;
	private static Matcher matcher;
	
	public static boolean validateEmail(String email){
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean validateName(String name){
		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(name);
		return matcher.matches();
	}
	
	public static boolean validatePassword(String password){
		pattern = Pattern.compile(PASSWORD_PATTER);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}
	
	public static boolean comparePassword(String password,String cpassword){
		if(password.equals(cpassword)) return true;
		else return false;
	}
}
