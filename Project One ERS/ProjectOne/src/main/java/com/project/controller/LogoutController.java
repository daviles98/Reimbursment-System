package com.project.controller;

import javax.servlet.http.HttpServletRequest;

public class LogoutController {

	public static String backToLogin(HttpServletRequest request) {
		return "/resources/html/landingpage.html";
	}
	
}
