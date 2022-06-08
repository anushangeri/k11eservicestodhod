package net.javatutorial.entity;

import java.sql.Timestamp;

public class Leave {
	private String leaveID;
    private String idNo; //officer ID number
    private String leaveType; //AL, Sick Leave, Hospital Leave
    private Timestamp leaveStartDate;
    private Timestamp leaveEndDate;
    private int numDaysLeave;
    private String requestStatus; //status of leave; Approved, Pending, Rejected
    private String approvingSupervisor; //Name of Supervisor that approved/rejected
    private String remarks; //additional comments can be made by approving officer
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	/**
	 * @param leaveID
	 * @param idNo
	 * @param leaveType
	 * @param leaveStartDate
	 * @param leaveEndDate
	 * @param numDaysLeave
	 * @param requestStatus
	 * @param approvingSupervisor
	 * @param remarks
	 * @param createdDt
	 * @param lastModifiedDt
	 */
	public Leave(String leaveID, String idNo, String leaveType, Timestamp leaveStartDate, Timestamp leaveEndDate,
			int numDaysLeave, String requestStatus, String approvingSupervisor, String remarks, Timestamp createdDt,
			Timestamp lastModifiedDt) {
		super();
		this.leaveID = leaveID;
		this.idNo = idNo;
		this.leaveType = leaveType;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.numDaysLeave = numDaysLeave;
		this.requestStatus = requestStatus;
		this.approvingSupervisor = approvingSupervisor;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	
	

	/**
	 * @param leaveID
	 * @param idNo
	 * @param leaveType
	 * @param leaveStartDate
	 * @param leaveEndDate
	 * @param numDaysLeave
	 * @param requestStatus
	 * @param approvingSupervisor
	 * @param remarks
	 */
	public Leave(String leaveID, String idNo, String leaveType, Timestamp leaveStartDate, Timestamp leaveEndDate,
			int numDaysLeave, String requestStatus, String approvingSupervisor, String remarks) {
		super();
		this.leaveID = leaveID;
		this.idNo = idNo;
		this.leaveType = leaveType;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.numDaysLeave = numDaysLeave;
		this.requestStatus = requestStatus;
		this.approvingSupervisor = approvingSupervisor;
		this.remarks = remarks;
	}



	/**
	 * @return the leaveID
	 */
	public String getLeaveID() {
		return leaveID;
	}

	/**
	 * @param leaveID the leaveID to set
	 */
	public void setLeaveID(String leaveID) {
		this.leaveID = leaveID;
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
	 * @return the leaveType
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * @param leaveType the leaveType to set
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * @return the leaveStartDate
	 */
	public Timestamp getLeaveStartDate() {
		return leaveStartDate;
	}

	/**
	 * @param leaveStartDate the leaveStartDate to set
	 */
	public void setLeaveStartDate(Timestamp leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	/**
	 * @return the leaveEndDate
	 */
	public Timestamp getLeaveEndDate() {
		return leaveEndDate;
	}

	/**
	 * @param leaveEndDate the leaveEndDate to set
	 */
	public void setLeaveEndDate(Timestamp leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	/**
	 * @return the numDaysLeave
	 */
	public int getNumDaysLeave() {
		return numDaysLeave;
	}

	/**
	 * @param numDaysLeave the numDaysLeave to set
	 */
	public void setNumDaysLeave(int numDaysLeave) {
		this.numDaysLeave = numDaysLeave;
	}

	/**
	 * @return the requestStatus
	 */
	public String getRequestStatus() {
		return requestStatus;
	}

	/**
	 * @param requestStatus the requestStatus to set
	 */
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * @return the approvingSupervisor
	 */
	public String getApprovingSupervisor() {
		return approvingSupervisor;
	}

	/**
	 * @param approvingSupervisor the approvingSupervisor to set
	 */
	public void setApprovingSupervisor(String approvingSupervisor) {
		this.approvingSupervisor = approvingSupervisor;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	/**
	 * @return the lastModifiedDt
	 */
	public Timestamp getLastModifiedDt() {
		return lastModifiedDt;
	}

	/**
	 * @param lastModifiedDt the lastModifiedDt to set
	 */
	public void setLastModifiedDt(Timestamp lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}

	@Override
	public String toString() {
		return "Leave [leaveID=" + leaveID + ", idNo=" + idNo + ", leaveType=" + leaveType + ", leaveStartDate="
				+ leaveStartDate + ", leaveEndDate=" + leaveEndDate + ", numDaysLeave=" + numDaysLeave
				+ ", requestStatus=" + requestStatus + ", approvingSupervisor=" + approvingSupervisor + ", remarks="
				+ remarks + ", createdDt=" + createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
}
