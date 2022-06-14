package com.revature.ers;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import com.revature.models.Role;

/**
 * Servlet implementation class ViewMyServlet
 */
@WebServlet("/ViewMyServlet")
public class ViewMyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewMyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		//To check that the username is caught from index.html
		System.out.println(username);
		
		UserDAO userDao = new UserDAO();
		Optional<User> optionalUser = userDao.getByUsername(username);
		User user = optionalUser.get();
		ReimbursementService reimburseService = new ReimbursementService();
		Role role = user.getRole();
		
		String FirstName = user.getFirstName();
		
		//To check if role is captured
		System.out.println(role);

		int id = user.getId();
		int flag = 0;
		List<Reimbursement> reimburseList = new ArrayList<Reimbursement>();
		if (role.equals(Role.EMPLOYEE)) {
			reimburseList = reimburseService.getReimbursementByAuthor(id);
		} else if (role.equals(Role.FINANCE_MANAGER)) {
			flag = 1;
			reimburseList = reimburseService.getAllReimbursement();
		}
		
		out.println("Welcome, " + FirstName + "!");
		out.println("<head> <title> ERS | Reimbursement View </title> </head> <body>"
				+ "<h1>Reimbursement Details</h1>");
	
		out.println(
				"<table border='1'> <thead> <tr> <th> ID </th> <th> Amount </th> <th> Author </th> <th> Resolver </th> <th> Status </th> <th> Description </th> <th>Creation Date </th> <th> Resolution Date </th> <th>Receipt Image </th> ");
		if (flag == 1) {
			out.println("<th>Actions </th>");
		}
		out.println("</tr></thead><tbody>");
		for (Reimbursement reimbursement : reimburseList) {
			out.print("<tr> <td>" + reimbursement.getId() + "</td>");
			out.print("<td>" + reimbursement.getAmount() + "</td>");
			out.print("<td>" + reimbursement.getAuthor() + "</td>");
			out.print("<td>" + reimbursement.getResolver() + "</td>");
			out.print("<td>" + reimbursement.getStatus() + "</td>");
			out.print("<td>" + reimbursement.getDescription() + "</td>");
			out.print("<td>" + reimbursement.getCreationDate() + "</td>");
			out.print("<td>" + reimbursement.getResolutionDate() + "</td>");
			out.print("<td>" + reimbursement.getReceipt() + "</td>");
			if (flag == 1) {
				out.println("<td> <form action=ChoiceServlet> <select name='status'> <option value = 'Approve'>Approve</option> <option value ='Deny'>Deny</option> </select> </td>");
				out.println("<td><button>Set Action</button></form></td>");
			}
			out.println("</tr>");
		}
		out.print("</tbody></table> </body>");
		out.println("</br>");
		out.println("<a href='LogoutServlet'>Logout</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		
	}
}


