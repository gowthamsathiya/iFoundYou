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
import com.ifoundyou.process.LocationUpdate;

/**
 * Servlet implementation class LocationChange
 */
@WebServlet("/LocationChange")
public class LocationChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IFoundYouData data = new IFoundYouData();
		data.setEmail(request.getParameter("useremail"));
		data.setUserLocation(request.getParameter("location"));
		data.setTime(request.getParameter("time"));
		LocationUpdate update = new LocationUpdate(data);
		PrintWriter out = response.getWriter();
		try {
			String locationName = update.updateLocation();
			out.println(locationName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
