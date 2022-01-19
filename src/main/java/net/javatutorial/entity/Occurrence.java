package net.javatutorial.entity;

import java.sql.Timestamp;

public class Occurrence {
	private String occurrenceId;
	
    private String reportingSite;
    private String reportingClassification;
    private String officerOnDutyName;
    private String officerOnDutyId;
    private String securityRiskThreat;
    private String descriptionOfSecurityRiskThreat;
    private String nonConformanceSOP;
    private String desciptionOfNonConformance;
    private String partiesInvolved;
    private String descriptionOfPartiesInvolved;
    private String descriptionOfK11Notified;
    private String howIncidentOccurred;
    private String whatIncidentOccurred;
    private String whyIncidentOccurred;
    private String whatActionWasTaken;
    private String extraDetailsOnAction;
    private String pendingAction;
    private String incidentCategory;
    
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	public Occurrence(String occurrenceId, String reportingSite, String reportingClassification,
			String officerOnDutyName, String officerOnDutyId, String securityRiskThreat,
			String descriptionOfSecurityRiskThreat, String nonConformanceSOP, String desciptionOfNonConformance,
			String partiesInvolved, String descriptionOfPartiesInvolved, String descriptionOfK11Notified,
			String howIncidentOccurred, String whatIncidentOccurred, String whyIncidentOccurred,
			String whatActionWasTaken, String extraDetailsOnAction, String pendingAction, String incidentCategory,
			Timestamp createdDt, Timestamp lastModifiedDt) {
		super();
		this.occurrenceId = occurrenceId;
		this.reportingSite = reportingSite;
		this.reportingClassification = reportingClassification;
		this.officerOnDutyName = officerOnDutyName;
		this.officerOnDutyId = officerOnDutyId;
		this.securityRiskThreat = securityRiskThreat;
		this.descriptionOfSecurityRiskThreat = descriptionOfSecurityRiskThreat;
		this.nonConformanceSOP = nonConformanceSOP;
		this.desciptionOfNonConformance = desciptionOfNonConformance;
		this.partiesInvolved = partiesInvolved;
		this.descriptionOfPartiesInvolved = descriptionOfPartiesInvolved;
		this.descriptionOfK11Notified = descriptionOfK11Notified;
		this.howIncidentOccurred = howIncidentOccurred;
		this.whatIncidentOccurred = whatIncidentOccurred;
		this.whyIncidentOccurred = whyIncidentOccurred;
		this.whatActionWasTaken = whatActionWasTaken;
		this.extraDetailsOnAction = extraDetailsOnAction;
		this.pendingAction = pendingAction;
		this.incidentCategory = incidentCategory;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	
	public String getOccurrenceId() {
		return occurrenceId;
	}
	public void setOccurrenceId(String occurrenceId) {
		this.occurrenceId = occurrenceId;
	}
	public String getReportingSite() {
		return reportingSite;
	}
	public void setReportingSite(String reportingSite) {
		this.reportingSite = reportingSite;
	}
	public String getReportingClassification() {
		return reportingClassification;
	}
	public void setReportingClassification(String reportingClassification) {
		this.reportingClassification = reportingClassification;
	}
	public String getOfficerOnDutyName() {
		return officerOnDutyName;
	}
	public void setOfficerOnDutyName(String officerOnDutyName) {
		this.officerOnDutyName = officerOnDutyName;
	}
	public String getOfficerOnDutyId() {
		return officerOnDutyId;
	}
	public void setOfficerOnDutyId(String officerOnDutyId) {
		this.officerOnDutyId = officerOnDutyId;
	}
	public String getSecurityRiskThreat() {
		return securityRiskThreat;
	}
	public void setSecurityRiskThreat(String securityRiskThreat) {
		this.securityRiskThreat = securityRiskThreat;
	}
	public String getDescriptionOfSecurityRiskThreat() {
		return descriptionOfSecurityRiskThreat;
	}
	public void setDescriptionOfSecurityRiskThreat(String descriptionOfSecurityRiskThreat) {
		this.descriptionOfSecurityRiskThreat = descriptionOfSecurityRiskThreat;
	}
	public String getNonConformanceSOP() {
		return nonConformanceSOP;
	}
	public void setNonConformanceSOP(String nonConformanceSOP) {
		this.nonConformanceSOP = nonConformanceSOP;
	}
	public String getDesciptionOfNonConformance() {
		return desciptionOfNonConformance;
	}
	public void setDesciptionOfNonConformance(String desciptionOfNonConformance) {
		this.desciptionOfNonConformance = desciptionOfNonConformance;
	}
	public String getPartiesInvolved() {
		return partiesInvolved;
	}
	public void setPartiesInvolved(String partiesInvolved) {
		this.partiesInvolved = partiesInvolved;
	}
	public String getDescriptionOfPartiesInvolved() {
		return descriptionOfPartiesInvolved;
	}
	public void setDescriptionOfPartiesInvolved(String descriptionOfPartiesInvolved) {
		this.descriptionOfPartiesInvolved = descriptionOfPartiesInvolved;
	}
	public String getDescriptionOfK11Notified() {
		return descriptionOfK11Notified;
	}
	public void setDescriptionOfK11Notified(String descriptionOfK11Notified) {
		this.descriptionOfK11Notified = descriptionOfK11Notified;
	}
	public String getHowIncidentOccurred() {
		return howIncidentOccurred;
	}
	public void setHowIncidentOccurred(String howIncidentOccurred) {
		this.howIncidentOccurred = howIncidentOccurred;
	}
	public String getWhatIncidentOccurred() {
		return whatIncidentOccurred;
	}
	public void setWhatIncidentOccurred(String whatIncidentOccurred) {
		this.whatIncidentOccurred = whatIncidentOccurred;
	}
	public String getWhyIncidentOccurred() {
		return whyIncidentOccurred;
	}
	public void setWhyIncidentOccurred(String whyIncidentOccurred) {
		this.whyIncidentOccurred = whyIncidentOccurred;
	}
	public String getWhatActionWasTaken() {
		return whatActionWasTaken;
	}
	public void setWhatActionWasTaken(String whatActionWasTaken) {
		this.whatActionWasTaken = whatActionWasTaken;
	}
	public String getExtraDetailsOnAction() {
		return extraDetailsOnAction;
	}
	public void setExtraDetailsOnAction(String extraDetailsOnAction) {
		this.extraDetailsOnAction = extraDetailsOnAction;
	}
	public String getPendingAction() {
		return pendingAction;
	}
	public void setPendingAction(String pendingAction) {
		this.pendingAction = pendingAction;
	}
	public String getIncidentCategory() {
		return incidentCategory;
	}
	public void setIncidentCategory(String incidentCategory) {
		this.incidentCategory = incidentCategory;
	}
	public Timestamp getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(Timestamp createdDt) {
		this.createdDt = createdDt;
	}
	public Timestamp getLastModifiedDt() {
		return lastModifiedDt;
	}
	public void setLastModifiedDt(Timestamp lastModifiedDt) {
		this.lastModifiedDt = lastModifiedDt;
	}

	@Override
	public String toString() {
		return "Occurrence [occurrenceId=" + occurrenceId + ", reportingSite=" + reportingSite
				+ ", reportingClassification=" + reportingClassification + ", officerOnDutyName=" + officerOnDutyName
				+ ", officerOnDutyId=" + officerOnDutyId + ", securityRiskThreat=" + securityRiskThreat
				+ ", descriptionOfSecurityRiskThreat=" + descriptionOfSecurityRiskThreat + ", nonConformanceSOP="
				+ nonConformanceSOP + ", desciptionOfNonConformance=" + desciptionOfNonConformance
				+ ", partiesInvolved=" + partiesInvolved + ", descriptionOfPartiesInvolved="
				+ descriptionOfPartiesInvolved + ", descriptionOfK11Notified=" + descriptionOfK11Notified
				+ ", howIncidentOccurred=" + howIncidentOccurred + ", whatIncidentOccurred=" + whatIncidentOccurred
				+ ", whyIncidentOccurred=" + whyIncidentOccurred + ", whatActionWasTaken=" + whatActionWasTaken
				+ ", extraDetailsOnAction=" + extraDetailsOnAction + ", pendingAction=" + pendingAction
				+ ", incidentCategory=" + incidentCategory + ", createdDt=" + createdDt + ", lastModifiedDt="
				+ lastModifiedDt + "]";
	}
	
}
