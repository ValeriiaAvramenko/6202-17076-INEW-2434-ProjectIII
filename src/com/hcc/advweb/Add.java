package com.hcc.advweb;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/jsp/team.jsp");
		String username = request.getParameter("username");
		String hours = request.getParameter("hours");
		
		if ((username == null) || username.equals(null) || (username == "")) {
			
			rd = sc.getRequestDispatcher("/WEB-INF/jsp/greeting.jsp");
			request.setAttribute("message", "Please enter username" );
			rd.include(request, response);
			
		}else if((hours == null) || hours.equals(null) || (hours == "")) {
			
			rd = sc.getRequestDispatcher("/WEB-INF/jsp/greeting.jsp");
			request.setAttribute("username", username );
			request.setAttribute("message", "Please enter hours" );
			rd.include(request, response);
			
		} else {
			User newUser = new User();
			
			newUser.setLogin(username);
			newUser.setHours(hours);
		
			UserListDB.addUser(newUser);
			
			request.setAttribute("message",UserListDB.getUserList() );
			rd.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
