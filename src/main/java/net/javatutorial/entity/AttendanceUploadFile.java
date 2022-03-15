package net.javatutorial.entity;

import java.io.InputStream;
import java.sql.Timestamp;

public class AttendanceUploadFile {
	private InputStream payslip;
    private Timestamp createdDt;
	/**
	 * @param payslip
	 * @param createdDt
	 */
	public AttendanceUploadFile(InputStream payslip, Timestamp createdDt) {
		super();
		this.payslip = payslip;
		this.createdDt = createdDt;
	}
	/**
	 * @param payslip
	 */
	public AttendanceUploadFile(InputStream payslip) {
		super();
		this.payslip = payslip;
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
		return "AttendanceUploadFile [payslip=" + payslip + ", createdDt=" + createdDt + "]";
	}
    
	
    
}
