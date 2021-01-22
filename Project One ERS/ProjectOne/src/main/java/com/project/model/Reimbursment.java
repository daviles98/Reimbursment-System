package com.project.model;
import java.sql.Timestamp;

public class Reimbursment {

	private int reimburId;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte receipt;
	private int authorFK;
	private int resolverFK;
	private int reimburStatus;
	private int reimburType;
	
	public Reimbursment() {
	}

	public Reimbursment(int reimburId, double amount, Timestamp submitted, Timestamp resolved, String description,
			byte receipt, int authorFK, int resolverFK, int reimburStatus, int reimburType) {
		super();
		this.reimburId = reimburId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.authorFK = authorFK;
		this.resolverFK = resolverFK;
		this.reimburStatus = reimburStatus;
		this.reimburType = reimburType;
	}

	public int getReimburId() {
		return reimburId;
	}

	public void setReimburId(int reimburId) {
		this.reimburId = reimburId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getReceipt() {
		return receipt;
	}

	public void setReceipt(byte receipt) {
		this.receipt = receipt;
	}

	public int getAuthorFK() {
		return authorFK;
	}

	public void setAuthorFK(int authorFK) {
		this.authorFK = authorFK;
	}

	public int getResolverFK() {
		return resolverFK;
	}

	public void setResolverFK(int resolverFK) {
		this.resolverFK = resolverFK;
	}

	public int getReimburStatus() {
		return reimburStatus;
	}

	public void setReimburStatus(int reimburStatus) {
		this.reimburStatus = reimburStatus;
	}

	public int getReimburType() {
		return reimburType;
	}

	public void setReimburType(int reimburType) {
		this.reimburType = reimburType;
	}

	@Override
	public String toString() {
		return "Reimbursment [reimburId=" + reimburId + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", receipt=" + receipt + ", authorFK="
				+ authorFK + ", resolverFK=" + resolverFK + ", reimburStatus=" + reimburStatus + ", reimburType="
				+ reimburType + "]";
	}
	
	
	
}
