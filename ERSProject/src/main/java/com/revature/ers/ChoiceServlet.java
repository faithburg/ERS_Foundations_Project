package com.revature.ers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Authenticator.RequestorType;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;

/**
 * Servlet implementation class ChoiceServlet
 */
@WebServlet("/ChoiceServlet")
public class ChoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoiceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
		UserDAO userDao = new UserDAO();
		Optional<User> optionalUser = userDao.getByUsername(username);
		User user = optionalUser.get();
		int id=user.getId();
		
		//To check that the ID has been captured
		System.out.println("User ID: " + id);

		ReimbursementService reimburseService = new ReimbursementService();
		Reimbursement unprocessedReimbursement = new Reimbursement();
		
		String status = request.getParameter("status");
		//To check that status is selected
		System.out.println("Status: " + status);
		
		unprocessedReimbursement.setResolver(id);
		if (status.equalsIgnoreCase("Approve"))
			unprocessedReimbursement.setStatus(Status.APPROVED);
		else if (status.equalsIgnoreCase("Deny"))
			unprocessedReimbursement.setStatus(Status.DENIED);
		//unprocessedReimbursement.set
		
		Reimbursement updatedReimbursement = reimburseService.process(unprocessedReimbursement); 
		
		
		
		
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
}
	}


