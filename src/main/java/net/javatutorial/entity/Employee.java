package net.javatutorial.entity;

import java.io.InputStream;
import java.sql.Timestamp;
//this is the KET module
//to-do: add other sections from KET form and recreated employee table
public class Employee {
//	//Section A: details of employments
	private String employeeId;
//	private String companyName;
//	private String jobTitle;
//    private String employeeName;
//    private String employementType; //full-time or part-time
    private String idNo;
//    private String durationOfEmployment; //permanent or contract
//    private Date employmentStartDate; //permanent or contract
//    private String employmentPlaceOfWork; //brief description of site
//    
//    //Section B: Working Hours and Rest days
//    private String workingHoursDetails;
//    private String numberOfWorkingDaysPerWeek;	
    
	//Shangeri on June 6th, we are just going to upload the KET form as PDF and enter the leave numbers manually
	//so those number can be used in the leave module. later on, come back and continue creating the fields for the other sections
	// as commented out above
	
	//Section D: types of leaves
	private int paidAnnualLeavePerYear;
	private int paidOutpatientSickLeavePerYear;
	private int paidHospitalisationLeavePerYear;
	
	private InputStream ketDocument;
	
    private Timestamp created_dt;
    private Timestamp last_modified_dt;
    
	/**
	 * @param employeeId
	 * @param idNo
	 * @param paidAnnualLeavePerYear
	 * @param paidOutpatientSickLeavePerYear
	 * @param paidHospitalisationLeavePerYear
	 * @param ketDocument
	 * @param created_dt
	 * @param last_modified_dt
	 */
	public Employee(String employeeId, String idNo, int paidAnnualLeavePerYear, int paidOutpatientSickLeavePerYear,
			int paidHospitalisationLeavePerYear, InputStream ketDocument, Timestamp created_dt,
			Timestamp last_modified_dt) {
		super();
		this.employeeId = employeeId;
		this.idNo = idNo;
		this.paidAnnualLeavePerYear = paidAnnualLeavePerYear;
		this.paidOutpatientSickLeavePerYear = paidOutpatientSickLeavePerYear;
		this.paidHospitalisationLeavePerYear = paidHospitalisationLeavePerYear;
		this.ketDocument = ketDocument;
		this.created_dt = created_dt;
		this.last_modified_dt = last_modified_dt;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the paidAnnualLeavePerYear
	 */
	public int getPaidAnnualLeavePerYear() {
		return paidAnnualLeavePerYear;
	}

	/**
	 * @param paidAnnualLeavePerYear the paidAnnualLeavePerYear to set
	 */
	public void setPaidAnnualLeavePerYear(int paidAnnualLeavePerYear) {
		this.paidAnnualLeavePerYear = paidAnnualLeavePerYear;
	}

	/**
	 * @return the paidOutpatientSickLeavePerYear
	 */
	public int getPaidOutpatientSickLeavePerYear() {
		return paidOutpatientSickLeavePerYear;
	}

	/**
	 * @param paidOutpatientSickLeavePerYear the paidOutpatientSickLeavePerYear to set
	 */
	public void setPaidOutpatientSickLeavePerYear(int paidOutpatientSickLeavePerYear) {
		this.paidOutpatientSickLeavePerYear = paidOutpatientSickLeavePerYear;
	}

	/**
	 * @return the paidHospitalisationLeavePerYear
	 */
	public int getPaidHospitalisationLeavePerYear() {
		return paidHospitalisationLeavePerYear;
	}

	/**
	 * @param paidHospitalisationLeavePerYear the paidHospitalisationLeavePerYear to set
	 */
	public void setPaidHospitalisationLeavePerYear(int paidHospitalisationLeavePerYear) {
		this.paidHospitalisationLeavePerYear = paidHospitalisationLeavePerYear;
	}

	/**
	 * @return the ketDocument
	 */
	public InputStream getKetDocument() {
		return ketDocument;
	}

	/**
	 * @param ketDocument the ketDocument to set
	 */
	public void setKetDocument(InputStream ketDocument) {
		this.ketDocument = ketDocument;
	}

	/**
	 * @return the created_dt
	 */
	public Timestamp getCreated_dt() {
		return created_dt;
	}

	/**
	 * @param created_dt the created_dt to set
	 */
	public void setCreated_dt(Timestamp created_dt) {
		this.created_dt = created_dt;
	}

	/**
	 * @return the last_modified_dt
	 */
	public Timestamp getLast_modified_dt() {
		return last_modified_dt;
	}

	/**
	 * @param last_modified_dt the last_modified_dt to set
	 */
	public void setLast_modified_dt(Timestamp last_modified_dt) {
		this.last_modified_dt = last_modified_dt;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", idNo=" + idNo + ", paidAnnualLeavePerYear="
				+ paidAnnualLeavePerYear + ", paidOutpatientSickLeavePerYear=" + paidOutpatientSickLeavePerYear
				+ ", paidHospitalisationLeavePerYear=" + paidHospitalisationLeavePerYear + ", ketDocument="
				+ ketDocument + ", created_dt=" + created_dt + ", last_modified_dt=" + last_modified_dt + "]";
	}
    
	
}
