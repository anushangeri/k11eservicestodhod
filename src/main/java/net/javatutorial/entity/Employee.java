package net.javatutorial.entity;

import java.sql.Date;

public class Employee {
	private String employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String maritalStatus;
    private Date dob;
    private int age;
    private String nationality;
    private String pob;
    private String identification;
    private String idType;
    private String idNo;
    private String religion;
    private String race;
    private String mobileNo;
    private String email;
    private String emergencyName;
    private String emergencyRlp;
    private String emergencyNo;
    private String employeeStatus;
    private Date joiningDt;
    private Date probDtFrm;
    private Date probDtTo;
    private String supervisor;
    private String allowLogin;
    
    private Date created_dt;
    private Date last_modified_dt;
    private String created_by;
    private String last_modified_by;
    
    
	public Employee(String firstName, String lastName, String idNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNo = idNo;
	}
	public Employee(String employeeId, String firstName, String lastName, String gender, String maritalStatus, Date dob,
			int age, String nationality, String pob, String identification, String idType, String idNo, String religion,
			String race, String mobileNo, String email, String emergencyName, String emergencyRlp, String emergencyNo,
			String employeeStatus, Date joiningDt, Date probDtFrm, Date probDtTo, String supervisor, String allowLogin,
			Date created_dt, Date last_modified_dt, String created_by, String last_modified_by) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.maritalStatus = maritalStatus;
		this.dob = dob;
		this.age = age;
		this.nationality = nationality;
		this.pob = pob;
		this.identification = identification;
		this.idType = idType;
		this.idNo = idNo;
		this.religion = religion;
		this.race = race;
		this.mobileNo = mobileNo;
		this.email = email;
		this.emergencyName = emergencyName;
		this.emergencyRlp = emergencyRlp;
		this.emergencyNo = emergencyNo;
		this.employeeStatus = employeeStatus;
		this.joiningDt = joiningDt;
		this.probDtFrm = probDtFrm;
		this.probDtTo = probDtTo;
		this.supervisor = supervisor;
		this.allowLogin = allowLogin;
		this.created_dt = created_dt;
		this.last_modified_dt = last_modified_dt;
		this.created_by = created_by;
		this.last_modified_by = last_modified_by;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPob() {
		return pob;
	}
	public void setPob(String pob) {
		this.pob = pob;
	}
	public String getIdentification() {
		return identification;
	}
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmergencyName() {
		return emergencyName;
	}
	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}
	public String getEmergencyRlp() {
		return emergencyRlp;
	}
	public void setEmergencyRlp(String emergencyRlp) {
		this.emergencyRlp = emergencyRlp;
	}
	public String getEmergencyNo() {
		return emergencyNo;
	}
	public void setEmergencyNo(String emergencyNo) {
		this.emergencyNo = emergencyNo;
	}
	public String getEmployeeStatus() {
		return employeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	public Date getJoiningDt() {
		return joiningDt;
	}
	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}
	public Date getProbDtFrm() {
		return probDtFrm;
	}
	public void setProbDtFrm(Date probDtFrm) {
		this.probDtFrm = probDtFrm;
	}
	public Date getProbDtTo() {
		return probDtTo;
	}
	public void setProbDtTo(Date probDtTo) {
		this.probDtTo = probDtTo;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getAllowLogin() {
		return allowLogin;
	}
	public void setAllowLogin(String allowLogin) {
		this.allowLogin = allowLogin;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public Date getLast_modified_dt() {
		return last_modified_dt;
	}
	public void setLast_modified_dt(Date last_modified_dt) {
		this.last_modified_dt = last_modified_dt;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getLast_modified_by() {
		return last_modified_by;
	}
	public void setLast_modified_by(String last_modified_by) {
		this.last_modified_by = last_modified_by;
	}
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", dob=" + dob + ", age=" + age
				+ ", nationality=" + nationality + ", pob=" + pob + ", identification=" + identification + ", idType="
				+ idType + ", idNo=" + idNo + ", religion=" + religion + ", race=" + race + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", emergencyName=" + emergencyName + ", emergencyRlp=" + emergencyRlp
				+ ", emergencyNo=" + emergencyNo + ", employeeStatus=" + employeeStatus + ", joiningDt=" + joiningDt
				+ ", probDtFrm=" + probDtFrm + ", probDtTo=" + probDtTo + ", supervisor=" + supervisor + ", allowLogin="
				+ allowLogin + ", created_dt=" + created_dt + ", last_modified_dt=" + last_modified_dt + ", created_by="
				+ created_by + ", last_modified_by=" + last_modified_by + "]";
	}
    
    
}
