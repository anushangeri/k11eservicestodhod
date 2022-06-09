package net.javatutorial.entity;

import java.sql.Timestamp;

public class TodHodRecord {
	private String recordId;
    private String officerName;
    private String officerIdNo;
    private String siteName;
    private String shift;
    private Timestamp timeInDt;
    private Timestamp timeOutDt;
    private String remark;
    
    public TodHodRecord(String recordId, String officerName, String officerIdNo, String siteName, String shift,
			Timestamp timeInDt) {
		super();
		this.recordId = recordId;
		this.officerName = officerName;
		this.officerIdNo = officerIdNo;
		this.siteName = siteName;
		this.shift = shift;
		this.timeInDt = timeInDt;
	}
    
	public TodHodRecord(String recordId, String officerName, String officerIdNo, String siteName, String shift,
			Timestamp timeInDt, Timestamp timeOutDt) {
		super();
		this.recordId = recordId;
		this.officerName = officerName;
		this.officerIdNo = officerIdNo;
		this.siteName = siteName;
		this.shift = shift;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
	}
	
	
	/**
	 * @param recordId
	 * @param officerName
	 * @param officerIdNo
	 * @param siteName
	 * @param shift
	 * @param timeInDt
	 * @param timeOutDt
	 * @param remark
	 */
	public TodHodRecord(String recordId, String officerName, String officerIdNo, String siteName, String shift,
			Timestamp timeInDt, Timestamp timeOutDt, String remark) {
		super();
		this.recordId = recordId;
		this.officerName = officerName;
		this.officerIdNo = officerIdNo;
		this.siteName = siteName;
		this.shift = shift;
		this.timeInDt = timeInDt;
		this.timeOutDt = timeOutDt;
		this.remark = remark;
	}

	/**
	 * @param recordId
	 * @param officerName
	 * @param officerIdNo
	 * @param siteName
	 * @param shift
	 * @param timeInDt
	 * @param remark
	 */
	public TodHodRecord(String recordId, String officerName, String officerIdNo, String siteName, String shift,
			Timestamp timeInDt, String remark) {
		super();
		this.recordId = recordId;
		this.officerName = officerName;
		this.officerIdNo = officerIdNo;
		this.siteName = siteName;
		this.shift = shift;
		this.timeInDt = timeInDt;
		this.remark = remark;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the recordId
	 */
	public String getRecordId() {
		return recordId;
	}
	/**
	 * @param recordId the recordId to set
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
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
	 * @return the siteName
	 */
	public String getSiteName() {
		return siteName;
	}
	/**
	 * @param siteName the siteName to set
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	/**
	 * @return the shift
	 */
	public String getShift() {
		return shift;
	}
	/**
	 * @param shift the shift to set
	 */
	public void setShift(String shift) {
		this.shift = shift;
	}
	/**
	 * @return the timeInDt
	 */
	public Timestamp getTimeInDt() {
		return timeInDt;
	}
	/**
	 * @param timeInDt the timeInDt to set
	 */
	public void setTimeInDt(Timestamp timeInDt) {
		this.timeInDt = timeInDt;
	}
	/**
	 * @return the timeOutDt
	 */
	public Timestamp getTimeOutDt() {
		return timeOutDt;
	}
	/**
	 * @param timeOutDt the timeOutDt to set
	 */
	public void setTimeOutDt(Timestamp timeOutDt) {
		this.timeOutDt = timeOutDt;
	}
	@Override
	public String toString() {
		return "TodHodRecord [recordId=" + recordId + ", officerName=" + officerName + ", officerIdNo=" + officerIdNo
				+ ", siteName=" + siteName + ", shift=" + shift + ", timeInDt=" + timeInDt + ", timeOutDt=" + timeOutDt
				+ "]";
	}
}
