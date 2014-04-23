package com.ifoundyou.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifoundyou.data.IFoundYouData;
import com.ifoundyou.process.UserRegister;

/**
 * Servlet implementation class Register
 */
@WebServlet(description = "Register new user to iFoundYou environment", urlPatterns = { "/Register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFoundYouData data = new IFoundYouData();
		data.setEmail(request.getParameter("useremail"));
		data.setName(request.getParameter("name"));
		data.setPassword(request.getParameter("password"));
		PrintWriter out = response.getWriter();
		UserRegister register = new UserRegister(data);
		try {
			int status = register.registerUser();
			if(status == 0){
				//user already registered
				out.println("User already registered");
			}
			else{
				//registered successfully
				out.println("Registration successfull");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("Some problem with server. Try again pls");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
