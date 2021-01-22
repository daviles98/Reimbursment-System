package com.project.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.controller.UserController;

public class AjaxRequestHelper {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws IOException{
		System.out.println(req.getRequestURI());

		switch (req.getRequestURI()) {
		
		case "/ProjectOne/api/ajax/userReimburs":
			UserController.userReimburFinder(req,res);
			break;				

		case "/ProjectOne/api/ajax/allReimburs":
			UserController.allReimburFinder(req, res);
			break;
			
		case "/ProjectOne/api/ajax/allUsers":
			UserController.allUserFinder(req, res);
			break;
		
		default:
			res.getWriter().println("null");
		}
	}

}
