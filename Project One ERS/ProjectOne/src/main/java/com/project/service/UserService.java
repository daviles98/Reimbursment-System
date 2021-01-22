package com.project.service;

import java.util.List;

import com.project.dao.UserDao;
import com.project.model.User;

public class UserService {

	private UserDao dao = new UserDao();
	
	
	public List<User> getAllUsers(){
		return dao.selectAllUsers();
	}
	
	public User getUserByUsername(String username) {
		return dao.selectByUsername(username);
	}
	
}
