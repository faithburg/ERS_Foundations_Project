package com.revature.ers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;

/**
 * Servlet implementation class RegisterServlet2
 */
@WebServlet("/RegisterServlet2")
public class RegisterServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		UserService userService = new UserService();
		User userToBeRegistered = new User();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		long phone = Long.parseLong(request.getParameter("phone"));
		
		userToBeRegistered.setUsername(username);
		userToBeRegistered.setPassword(password);
		if (role.equalsIgnoreCase("Employee"))
			userToBeRegistered.setRole(Role.EMPLOYEE);
		else if (role.equalsIgnoreCase("financeManager"))
			userToBeRegistered.setRole(Role.FINANCE_MANAGER);
		userToBeRegistered.setFirstName(fname);
		userToBeRegistered.setLastName(lname);
		userToBeRegistered.setAddress(address);
		userToBeRegistered.setEmail(email);
		userToBeRegistered.setPhone(phone);
		
		User registeredUser = userService.create(userToBeRegistered);
		
		if (registeredUser != null)
			response.sendRedirect("index.html");
		else
			out.print("Registration failed. <p><a href='signup.html'>Try again</a></p>");
	
	//To check the values coming in from signup.html
	System.out.println("Username "+username);
	System.out.println("Password "+password);
	System.out.println("Role "+role);
	System.out.println("FirstName "+fname);
	System.out.println("LastName "+lname);
	System.out.println("Address "+address);
	System.out.println("Email "+email);
	System.out.println("Phone # "+phone);

	}
}

	



