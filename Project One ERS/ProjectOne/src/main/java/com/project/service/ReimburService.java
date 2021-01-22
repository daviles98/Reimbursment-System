package com.project.service;

import java.sql.Timestamp;
import java.util.List;

import com.project.dao.ReimburDao;
import com.project.model.Reimbursment;
import com.project.model.User;

public class ReimburService {
	
	private ReimburDao dao = new ReimburDao();
	
	
	public List<Reimbursment> getAllReimburs(){
		return dao.selectAllReimburs();
	}
	
	public List<Reimbursment> getUsersReimbursments(User u) {
		return dao.selectAllUserReimburs(u);
	}
	
	public List<Reimbursment> getReimbursByStatus(int status){
		return dao.selectByStatus(status);
	}
	
	public void addReimbur(double amount, Timestamp timeSubmitted, String description, int employeeId, int type) {
		dao.insertNewReimbur(amount, timeSubmitted, description, employeeId, type);
	}
	
	public void modifyReimbur(Timestamp timeResolved, int managerId, int status, int employeeId) {
		dao.updateReimbur(timeResolved, managerId, status, employeeId);
	}
	
	

}
