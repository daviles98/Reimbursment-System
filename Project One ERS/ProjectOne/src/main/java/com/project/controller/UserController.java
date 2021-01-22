package com.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Reimbursment;
import com.project.model.User;
import com.project.service.ReimburService;
import com.project.service.UserService;

public class UserController {

	public static UserService myServ = new UserService();
	public static ReimburService rServ = new ReimburService();
	
	public static String login(HttpServletRequest req) {

		
		if(!req.getMethod().equals("POST")) {
			return "/landingpage.html";
		}
		
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		User loggedUser = myServ.getUserByUsername(username);
		
		if(loggedUser == null || (loggedUser != null && !loggedUser.getPw().equals(password))) {
			System.out.println("Bad login");
			return "/api/forward/badlogin";
		}else {
			req.getSession().setAttribute("logged-user", loggedUser);

			System.out.println("Succesful login");
			if(loggedUser.getUserRole() == 1) {
				return "/api/forward/homepage-employee";
			}
			else {
				return "/api/forward/homepage-manager";
			}
		}
	}
	
	
	public static void allUserFinder(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		List<User> myUserList = myServ.getAllUsers();
		res.getWriter().write(new ObjectMapper().writeValueAsString(myUserList));
	}

	
	public static void userReimburFinder(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {		
		List<Reimbursment> userReimburs = rServ.getUsersReimbursments((User)req.getSession().getAttribute("logged-user"));
		res.getWriter().write(new ObjectMapper().writeValueAsString(userReimburs));

		System.out.println("request session attributes: " + req.getSession().getAttribute("logged-user"));
	}
	
	
	public static void allReimburFinder(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		List<Reimbursment> allReimburs = rServ.getAllReimburs();
		res.getWriter().write(new ObjectMapper().writeValueAsString(allReimburs));
	}

	public static String logout(HttpServletRequest req) {
		System.out.println("Before Invalidation: " + req.getSession().getAttribute("logged-user"));
		req.getSession().invalidate();
		System.out.println("After Invalidation: " + req.getSession().getAttribute("logged-user"));
		return "/resources/html/landingpage.html";
	}
	
	
	
	

}
