package net.javatutorial.entity;

import java.sql.Timestamp;

//this is pwm details 
public class PWMDetails {
	private String pwmId;
    private String year; 
    private String pwmGrade; //SO, SSO, SS, SSS
    private double pwmWages;
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
	/**
	 * @param pwmId
	 * @param year
	 * @param pwmGrade
	 * @param pwmWages
	 * @param createdDt
	 * @param lastModifiedDt
	 */
	public PWMDetails(String pwmId, String year, String pwmGrade, double pwmWages, Timestamp createdDt,
			Timestamp lastModifiedDt) {
		super();
		this.pwmId = pwmId;
		this.year = year;
		this.pwmGrade = pwmGrade;
		this.pwmWages = pwmWages;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	/**
	 * @return the pwmId
	 */
	public String getPwmId() {
		return pwmId;
	}
	/**
	 * @param pwmId the pwmId to set
	 */
	public void setPwmId(String pwmId) {
		this.pwmId = pwmId;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the pwmGrade
	 */
	public String getPwmGrade() {
		return pwmGrade;
	}
	/**
	 * @param pwmGrade the pwmGrade to set
	 */
	public void setPwmGrade(String pwmGrade) {
		this.pwmGrade = pwmGrade;
	}
	/**
	 * @return the pwmWages
	 */
	public double getPwmWages() {
		return pwmWages;
	}
	/**
	 * @param pwmWages the pwmWages to set
	 */
	public void setPwmWages(double pwmWages) {
		this.pwmWages = pwmWages;
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
		return "PWMDetails [pwmId=" + pwmId + ", year=" + year + ", pwmGrade=" + pwmGrade + ", pwmWages=" + pwmWages
				+ ", createdDt=" + createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
}
