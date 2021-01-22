package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Reimbursment;
import com.project.model.User;

public class ReimburDao {
	
	
	public ReimburDao() {
	}

	/*********************************************************READ*********************************************************/
	public List<Reimbursment> selectAllReimburs(){
		List<Reimbursment> reimburs = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username,
				MyConnectionFactory.password)) {

			String sql = "SELECT * FROM reimbur ORDER BY reimbur_id";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimburs.add(new Reimbursment(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getByte(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimburs;
	}
	
	
	public List<Reimbursment> selectAllUserReimburs(User u){
		List<Reimbursment> userReimburs = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username,
				MyConnectionFactory.password)) {

			String sql = "SELECT * FROM reimbur WHERE fk_author=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				userReimburs.add(new Reimbursment(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getByte(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userReimburs;
	}
	
	public List<Reimbursment> selectByStatus(int status){
		List<Reimbursment> reimburs = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username,
				MyConnectionFactory.password)) {

			String sql = "SELECT * FROM reimbur WHERE fk_status_id=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimburs.add(new Reimbursment(rs.getInt(1), rs.getDouble(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getString(5), rs.getByte(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimburs;
	}
	
	
	
	/*********************************************************CREATE*********************************************************/
	public void insertNewReimbur(double amount, Timestamp timeSubmitted, String description, int employeeId, int type) {
		
		try(Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username, MyConnectionFactory.password)){
			
			String sql = "INSERT INTO reimbur(amount,submitted,description,fk_author,fk_status_id,fk_type_id) "
						+ "VALUES(?,?,?,?,1,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setTimestamp(2, timeSubmitted);
			ps.setString(3, description);
			ps.setInt(4, employeeId);
			ps.setInt(5, type);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	/*********************************************************UPDATE*********************************************************/
	public void updateReimbur(Timestamp timeResolved, int managerId, int status, int employeeId) {
		
		try(Connection conn = DriverManager.getConnection(MyConnectionFactory.url, MyConnectionFactory.username, MyConnectionFactory.password)){
			
			String sql = "UPDATE reimbur SET resolved=?,fk_resolver=?,fk_status_id=? WHERE reimbur_id=?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, timeResolved);
			ps.setInt(2, managerId);
			ps.setInt(3, status);
			ps.setInt(4, employeeId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	

}
