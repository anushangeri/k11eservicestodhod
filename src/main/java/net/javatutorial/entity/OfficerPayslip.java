package net.javatutorial.entity;

import java.io.InputStream;
import java.sql.Timestamp;

//this class is to generate the individual payslip to save to DB and display on officer side
public class OfficerPayslip {
	private String payslipId;
	private String officerIdNo;
	private InputStream payslip;
    private Timestamp createdDt;
	/**
	 * @param payslipId
	 * @param officerIdNo
	 * @param payslip
	 * @param createdDt
	 */
	public OfficerPayslip(String payslipId, String officerIdNo, InputStream payslip, Timestamp createdDt) {
		super();
		this.payslipId = payslipId;
		this.officerIdNo = officerIdNo;
		this.payslip = payslip;
		this.createdDt = createdDt;
	}
	
	/**
	 * @param payslipId
	 * @param officerIdNo
	 * @param payslip
	 */
	public OfficerPayslip(String payslipId, String officerIdNo, InputStream payslip) {
		super();
		this.payslipId = payslipId;
		this.officerIdNo = officerIdNo;
		this.payslip = payslip;
	}


	/**
	 * @return the payslipId
	 */
	public String getPayslipId() {
		return payslipId;
	}
	/**
	 * @param payslipId the payslipId to set
	 */
	public void setPayslipId(String payslipId) {
		this.payslipId = payslipId;
	}
	/**
	 * @return the officerIdNo
	 */
	public String getOfficerIdNo() {
		return officerIdNo;
	}
	/**
	 * @param officerIdNo the officerIdNo to set
	 */
	public void setOfficerIdNo(String officerIdNo) {
		this.officerIdNo = officerIdNo;
	}
	/**
	 * @return the payslip
	 */
	public InputStream getPayslip() {
		return payslip;
	}
	/**
	 * @param payslip the payslip to set
	 */
	public void setPayslip(InputStream payslip) {
		this.payslip = payslip;
	}
	/**
	 * @return the createdDt
	 */
	public Timestamp getCreatedDt() {
		return createdDt;
	}
	/**
	 * @param createdDt the createdDt to set
	 */
	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}
	@Override
	public String toString() {
		return "OfficerPayslip [payslipId=" + payslipId + ", officerIdNo=" + officerIdNo + ", payslip=" + payslip
				+ ", createdDt=" + createdDt + "]";
	}
	
}
