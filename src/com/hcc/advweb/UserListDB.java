package com.hcc.advweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class UserListDB {
	
	public static String getUserList(){
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		String buffer = "";
		int counter = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from user");
			
			while (rs.next()) {
				buffer += "<tr><td>";
				String login = rs.getString("login");
				buffer += login;
				buffer += "</td><td>";
				String password = rs.getString("hours");
				buffer += password;
				buffer += "</td><td>";
				String id = rs.getString("id");
				
				buffer += "<form method=\"get\" action = \"Edit\"><button name=\"editButton\" value ="+id+" class=\"edit-btn\">Edit</button></form>";
				buffer += "</td><td>";
				if(counter > 0) {
					buffer += "<form method=\"get\" action = \"Remove\"><button name=\"removeButton\" value ="+id+" class=\"edit-btn\">Remove</button></form>";
				}
				buffer += "</td></tr>";
				counter++;
				
			}
		} catch(SQLException | ClassNotFoundException e) {
			 buffer = Arrays.toString(e.getStackTrace()); 
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				 buffer = Arrays.toString(e.getStackTrace()); 
			}
		}
		return buffer;
	}
	
	public static User getUser(String id){
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		User userDB = new User();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("Select * from user where id="+id);
			rs.next();
			String login = rs.getString("login");
			String hours = rs.getString("hours");
			
			userDB.setLogin(login);
			userDB.setHours(hours);
				
				
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.print( Arrays.toString(e.getStackTrace())); 
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.print( Arrays.toString(e.getStackTrace()));  
			}
		}
		return userDB;
	}
	
	public static String addUser(User newUser) {
		Connection conn = null;

		String buffer = "SUCCSESS";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			String sql = "INSERT INTO USER (login,hours) VALUES(?,?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, newUser.getLogin());
			st.setString(2, newUser.getHours());
			st.execute();
			
		} catch(SQLException | ClassNotFoundException e) {
			 buffer = Arrays.toString(e.getStackTrace()); 
			
		} finally {
			try {

				conn.close();
			} catch (SQLException e) {
				 buffer = Arrays.toString(e.getStackTrace()); 
			}
		}
		return buffer;
	}
	
	public static String updateUser(User selectedUser) {
		Connection conn = null;

		String buffer = "SUCCSESS";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			String sql = "update USER set login = ?, hours = ? where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, selectedUser.getLogin());
			st.setString(2, selectedUser.getHours());
			st.setInt(3, Integer.parseInt(selectedUser.getId()));
			st.execute();
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.print( Arrays.toString(e.getStackTrace()));  
			
		} finally {
			try {

				conn.close();
			} catch (SQLException e) {
				System.out.print( Arrays.toString(e.getStackTrace())); 
			}
		}
		return buffer;
	}
	public static String removeUser(String id) {
		Connection conn = null;

		String buffer = "SUCCSESS";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			String sql = "delete from USER where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, Integer.parseInt(id));
			st.execute();
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.print( Arrays.toString(e.getStackTrace()));  
			
		} finally {
			try {

				conn.close();
			} catch (SQLException e) {
				System.out.print( Arrays.toString(e.getStackTrace())); 
			}
		}
		return buffer;
	}
	public static boolean  authentification(String login, String password){
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 boolean  result = true;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/userdb";
			String user = "root";
			String pwd  = "admin123";
			conn = DriverManager.getConnection(connURL, user, pwd);
			stmt = conn.createStatement();
			
	
			rs = stmt.executeQuery("Select * from userdb.authentification where login='"+login+"' and password='"+password+"';");
			if (rs.next() == false) result = false;
			else result = true;
		
		} catch(SQLException | ClassNotFoundException e) {
			System.out.print( Arrays.toString(e.getStackTrace())); 
			
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.print( Arrays.toString(e.getStackTrace()));  
			}
		}
		return result;
	}

}