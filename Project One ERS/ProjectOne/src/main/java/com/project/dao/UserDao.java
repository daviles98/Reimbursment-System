package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.User;

public class UserDao {


	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username,
				MyConnectionFactory.password)) {

			String sql = "SELECT * FROM users";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	
	public User selectByUsername(String username) {
		
		User retrievedUser = null;
		
		try (Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username,
				MyConnectionFactory.password)) {

			String sql = "SELECT * FROM users WHERE username=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				retrievedUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return retrievedUser;
	}
	

	
	
}
