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
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/jsp/team.jsp");
		String id = request.getParameter("updateButton");
		String login = request.getParameter("username");
		String hours = request.getParameter("hours");
		
	if ((login == null) || login.equals(null) || (login == "")) {
			
			rd = sc.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
			request.setAttribute("id",id );
			request.setAttribute("message", "Please enter username" );
			rd.include(request, response);
			
		}else if((hours == null) || hours.equals(null) || (hours == "")) {
			
			rd = sc.getRequestDispatcher("/WEB-INF/jsp/edit.jsp");
			request.setAttribute("id",id );
			request.setAttribute("login", login );
			request.setAttribute("message", "Please enter hours" );
			rd.include(request, response);
			
		} else {
		User selectedUser = new User();
		
		selectedUser.setLogin(login);
		selectedUser.setHours(hours);
		selectedUser.setId(id);
		
		UserListDB.updateUser(selectedUser);
		
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
