package com.project.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.controller.HomeController;
import com.project.controller.ReimburController;
import com.project.controller.UserController;

public class ForwardRequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse res){
		System.out.println(req.getRequestURI());

		switch (req.getRequestURI()) {

		case "/ProjectOne/api/forward/login":
			System.out.println("Login case");
			return UserController.login(req);
			
		case "/ProjectOne/api/forward/homepage-employee":
			System.out.println("Homepage Employee case");
			return HomeController.homeEmployee(req);
			
		case "/ProjectOne/api/forward/homepage-manager":
			System.out.println("Homepage Manager case");
			return HomeController.homeManager(req);
			
		case "/ProjectOne/api/forward/insertReimbursment":
			System.out.println("Inserting new reimbursment from website");
			return ReimburController.addReimbursment(req);
			
		case "/ProjectOne/api/forward/update":
			System.out.println("Authorizing employee reimbursment");
			return ReimburController.updateReimbur(req);
			
		case "/ProjectOne/api/forward/logout":
			System.out.println("Logout");
			return UserController.logout(req);
			
		default:
			return "/resources/html/badlogin.html";
		}
		
	}
	
}
