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
import com.ifoundyou.process.AddFriend;

/**
 * Servlet implementation class AddAFriend
 */
@WebServlet("/AddAFriend")
public class AddAFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFoundYouData data = new IFoundYouData();
		data.setEmail(request.getParameter("email"));
		data.setFriendEmail(request.getParameter("friendemail"));
		PrintWriter out = response.getWriter();
		AddFriend friend = new AddFriend(data);
		try {
			String result = friend.add();
			out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
			out.println("Some error happened. Try again latter");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
