package com.project.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import com.project.model.User;
import com.project.service.ReimburService;

public class ReimburController {
	
	public static ReimburService rServ = new ReimburService();

	public static String addReimbursment(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "/landingpage.html";
		}
		
		//req.getAttribute("logged-user");
		User u = (User)req.getSession().getAttribute("logged-user");
		String typeParam = req.getParameter("type");
		String amountParam = req.getParameter("amount");
		String description = req.getParameter("description");
		System.out.println(u);
		System.out.println(typeParam);
		System.out.println(amountParam);
		System.out.println(description);
		
		double amt = Double.parseDouble(amountParam);
		//int type = Integer.parseInt(typeParam);
		int type = typeConvert(typeParam);
		
		Timestamp submitted = Timestamp.valueOf(LocalDateTime.now());
		
		rServ.addReimbur(amt, submitted, description, u.getUserId(), type);
		
		return "/resources/html/homepage-employees.html";
	}
	
	public static String updateReimbur(HttpServletRequest req) {
		
		User u = (User)req.getSession().getAttribute("logged-user");
		
		System.out.println(u);
		String employeeParam = req.getParameter("employeeId");
		String statusParam = req.getParameter("status");
		
		
		Timestamp timeResolved = Timestamp.valueOf(LocalDateTime.now());
		int managerId = u.getUserId();
		
		int status = Integer.parseInt(statusParam);
		int employeeId = Integer.parseInt(employeeParam);
		
		System.out.println("resolved=" + timeResolved);
		System.out.println("fk_resolver=" + managerId);
		System.out.println("fk_status_id=" + statusParam);
		System.out.println("reimbur_id=" + employeeParam);
		
		rServ.modifyReimbur(timeResolved, managerId, status, employeeId);
		
		return "/resources/html/homepage-managers.html";
	}
	
	
	
	public static int typeConvert(String type) {
		switch(type) {
			case "lodging":
				return 1;
			
			case "travel":
				return 2;
			
			case "food":
				return 3;
				
			case "other":
				return 4;
				
			default:
				return -1;
		}
	}
	
	public static int statusConvert(String status) {
		switch(status) {
			case "pending":
				return 1;
			
			case "approved":
				return 2;
			
			case "denied":
				return 3;
				
			default:
				return -1;
		}
	}


}
