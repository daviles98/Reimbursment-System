package com.project.controller;

import javax.servlet.http.HttpServletRequest;

public class HomeController {

	public static String homeEmployee(HttpServletRequest request) {
		return "/resources/html/homepage-employees.html";
	}
	
	public static String homeManager(HttpServletRequest request) {
		return "/resources/html/homepage-managers.html";
	}
	
}
