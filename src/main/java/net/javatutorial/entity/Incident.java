package net.javatutorial.entity;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class Incident {
	private String incidentId;
	
    private String officerOnDutyName;
    private String officerOnDutyId;
    private String officerOnDutyDesignation;
    
    private String reportingSite;
    //the reason it is save in string because it is easier to display later
    private String dateOfIncident;
    private String timeOfIncident;
    private String dateOfIncidentReported;
    
    private String partiesInvolved;
    private List<String> incidentCategory;
    
    private String howIncidentOccurred;
    private String whatIncidentOccurred;
    private String whyIncidentOccurred;
    
    private String declarationByOfficerOnDuty;
    private String declarationofSecurityImplications;
    
    private String signatureOfOfficerOnDuty;
    private String signatureOfOpsManagerOnDuty;
    
    private List<InputStream> images;
    
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
	/**
	 * @param incidentId
	 * @param officerOnDutyName
	 * @param officerOnDutyId
	 * @param officerOnDutyDesignation
	 * @param reportingSite
	 * @param dateOfIncident
	 * @param timeOfIncident
	 * @param dateOfIncidentReported
	 * @param partiesInvolved
	 * @param incidentCategory
	 * @param howIncidentOccurred
	 * @param whatIncidentOccurred
	 * @param whyIncidentOccurred
	 * @param declarationByOfficerOnDuty
	 * @param declarationofSecurityImplications
	 * @param signatureOfOfficerOnDuty
	 * @param signatureOfOpsManagerOnDuty
	 * @param images
	 * @param createdDt
	 * @param lastModifiedDt
	 */
	public Incident(String incidentId, String officerOnDutyName, String officerOnDutyId,
			String officerOnDutyDesignation, String reportingSite, String dateOfIncident, String timeOfIncident,
			String dateOfIncidentReported, String partiesInvolved, List<String> incidentCategory,
			String howIncidentOccurred, String whatIncidentOccurred, String whyIncidentOccurred,
			String declarationByOfficerOnDuty, String declarationofSecurityImplications,
			String signatureOfOfficerOnDuty, String signatureOfOpsManagerOnDuty, List<InputStream> images,
			Timestamp createdDt, Timestamp lastModifiedDt) {
		super();
		this.incidentId = incidentId;
		this.officerOnDutyName = officerOnDutyName;
		this.officerOnDutyId = officerOnDutyId;
		this.officerOnDutyDesignation = officerOnDutyDesignation;
		this.reportingSite = reportingSite;
		this.dateOfIncident = dateOfIncident;
		this.timeOfIncident = timeOfIncident;
		this.dateOfIncidentReported = dateOfIncidentReported;
		this.partiesInvolved = partiesInvolved;
		this.incidentCategory = incidentCategory;
		this.howIncidentOccurred = howIncidentOccurred;
		this.whatIncidentOccurred = whatIncidentOccurred;
		this.whyIncidentOccurred = whyIncidentOccurred;
		this.declarationByOfficerOnDuty = declarationByOfficerOnDuty;
		this.declarationofSecurityImplications = declarationofSecurityImplications;
		this.signatureOfOfficerOnDuty = signatureOfOfficerOnDuty;
		this.signatureOfOpsManagerOnDuty = signatureOfOpsManagerOnDuty;
		this.images = images;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	/**
	 * @param incidentId
	 * @param officerOnDutyName
	 * @param officerOnDutyId
	 * @param officerOnDutyDesignation
	 * @param reportingSite
	 * @param dateOfIncident
	 * @param timeOfIncident
	 * @param dateOfIncidentReported
	 * @param partiesInvolved
	 * @param incidentCategory
	 * @param howIncidentOccurred
	 * @param whatIncidentOccurred
	 * @param whyIncidentOccurred
	 * @param declarationByOfficerOnDuty
	 * @param declarationofSecurityImplications
	 * @param signatureOfOfficerOnDuty
	 * @param images
	 * @param createdDt
	 * @param lastModifiedDt
	 */
	public Incident(String incidentId, String officerOnDutyName, String officerOnDutyId,
			String officerOnDutyDesignation, String reportingSite, String dateOfIncident, String timeOfIncident,
			String dateOfIncidentReported, String partiesInvolved, List<String> incidentCategory,
			String howIncidentOccurred, String whatIncidentOccurred, String whyIncidentOccurred,
			String declarationByOfficerOnDuty, String declarationofSecurityImplications,
			String signatureOfOfficerOnDuty, List<InputStream> images, Timestamp createdDt, Timestamp lastModifiedDt) {
		super();
		this.incidentId = incidentId;
		this.officerOnDutyName = officerOnDutyName;
		this.officerOnDutyId = officerOnDutyId;
		this.officerOnDutyDesignation = officerOnDutyDesignation;
		this.reportingSite = reportingSite;
		this.dateOfIncident = dateOfIncident;
		this.timeOfIncident = timeOfIncident;
		this.dateOfIncidentReported = dateOfIncidentReported;
		this.partiesInvolved = partiesInvolved;
		this.incidentCategory = incidentCategory;
		this.howIncidentOccurred = howIncidentOccurred;
		this.whatIncidentOccurred = whatIncidentOccurred;
		this.whyIncidentOccurred = whyIncidentOccurred;
		this.declarationByOfficerOnDuty = declarationByOfficerOnDuty;
		this.declarationofSecurityImplications = declarationofSecurityImplications;
		this.signatureOfOfficerOnDuty = signatureOfOfficerOnDuty;
		this.images = images;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}
	/**
	 * @return the incidentId
	 */
	public String getIncidentId() {
		return incidentId;
	}
	/**
	 * @param incidentId the incidentId to set
	 */
	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}
	/**
	 * @return the officerOnDutyName
	 */
	public String getOfficerOnDutyName() {
		return officerOnDutyName;
	}
	/**
	 * @param officerOnDutyName the officerOnDutyName to set
	 */
	public void setOfficerOnDutyName(String officerOnDutyName) {
		this.officerOnDutyName = officerOnDutyName;
	}
	/**
	 * @return the officerOnDutyId
	 */
	public String getOfficerOnDutyId() {
		return officerOnDutyId;
	}
	/**
	 * @param officerOnDutyId the officerOnDutyId to set
	 */
	public void setOfficerOnDutyId(String officerOnDutyId) {
		this.officerOnDutyId = officerOnDutyId;
	}
	/**
	 * @return the officerOnDutyDesignation
	 */
	public String getOfficerOnDutyDesignation() {
		return officerOnDutyDesignation;
	}
	/**
	 * @param officerOnDutyDesignation the officerOnDutyDesignation to set
	 */
	public void setOfficerOnDutyDesignation(String officerOnDutyDesignation) {
		this.officerOnDutyDesignation = officerOnDutyDesignation;
	}
	/**
	 * @return the reportingSite
	 */
	public String getReportingSite() {
		return reportingSite;
	}
	/**
	 * @param reportingSite the reportingSite to set
	 */
	public void setReportingSite(String reportingSite) {
		this.reportingSite = reportingSite;
	}
	/**
	 * @return the dateOfIncident
	 */
	public String getDateOfIncident() {
		return dateOfIncident;
	}
	/**
	 * @param dateOfIncident the dateOfIncident to set
	 */
	public void setDateOfIncident(String dateOfIncident) {
		this.dateOfIncident = dateOfIncident;
	}
	/**
	 * @return the timeOfIncident
	 */
	public String getTimeOfIncident() {
		return timeOfIncident;
	}
	/**
	 * @param timeOfIncident the timeOfIncident to set
	 */
	public void setTimeOfIncident(String timeOfIncident) {
		this.timeOfIncident = timeOfIncident;
	}
	/**
	 * @return the dateOfIncidentReported
	 */
	public String getDateOfIncidentReported() {
		return dateOfIncidentReported;
	}
	/**
	 * @param dateOfIncidentReported the dateOfIncidentReported to set
	 */
	public void setDateOfIncidentReported(String dateOfIncidentReported) {
		this.dateOfIncidentReported = dateOfIncidentReported;
	}
	/**
	 * @return the partiesInvolved
	 */
	public String getPartiesInvolved() {
		return partiesInvolved;
	}
	/**
	 * @param partiesInvolved the partiesInvolved to set
	 */
	public void setPartiesInvolved(String partiesInvolved) {
		this.partiesInvolved = partiesInvolved;
	}
	/**
	 * @return the incidentCategory
	 */
	public List<String> getIncidentCategory() {
		return incidentCategory;
	}
	/**
	 * @param incidentCategory the incidentCategory to set
	 */
	public void setIncidentCategory(List<String> incidentCategory) {
		this.incidentCategory = incidentCategory;
	}
	/**
	 * @return the howIncidentOccurred
	 */
	public String getHowIncidentOccurred() {
		return howIncidentOccurred;
	}
	/**
	 * @param howIncidentOccurred the howIncidentOccurred to set
	 */
	public void setHowIncidentOccurred(String howIncidentOccurred) {
		this.howIncidentOccurred = howIncidentOccurred;
	}
	/**
	 * @return the whatIncidentOccurred
	 */
	public String getWhatIncidentOccurred() {
		return whatIncidentOccurred;
	}
	/**
	 * @param whatIncidentOccurred the whatIncidentOccurred to set
	 */
	public void setWhatIncidentOccurred(String whatIncidentOccurred) {
		this.whatIncidentOccurred = whatIncidentOccurred;
	}
	/**
	 * @return the whyIncidentOccurred
	 */
	public String getWhyIncidentOccurred() {
		return whyIncidentOccurred;
	}
	/**
	 * @param whyIncidentOccurred the whyIncidentOccurred to set
	 */
	public void setWhyIncidentOccurred(String whyIncidentOccurred) {
		this.whyIncidentOccurred = whyIncidentOccurred;
	}
	/**
	 * @return the declarationByOfficerOnDuty
	 */
	public String getDeclarationByOfficerOnDuty() {
		return declarationByOfficerOnDuty;
	}
	/**
	 * @param declarationByOfficerOnDuty the declarationByOfficerOnDuty to set
	 */
	public void setDeclarationByOfficerOnDuty(String declarationByOfficerOnDuty) {
		this.declarationByOfficerOnDuty = declarationByOfficerOnDuty;
	}
	/**
	 * @return the declarationofSecurityImplications
	 */
	public String getDeclarationofSecurityImplications() {
		return declarationofSecurityImplications;
	}
	/**
	 * @param declarationofSecurityImplications the declarationofSecurityImplications to set
	 */
	public void setDeclarationofSecurityImplications(String declarationofSecurityImplications) {
		this.declarationofSecurityImplications = declarationofSecurityImplications;
	}
	/**
	 * @return the signatureOfOfficerOnDuty
	 */
	public String getSignatureOfOfficerOnDuty() {
		return signatureOfOfficerOnDuty;
	}
	/**
	 * @param signatureOfOfficerOnDuty the signatureOfOfficerOnDuty to set
	 */
	public void setSignatureOfOfficerOnDuty(String signatureOfOfficerOnDuty) {
		this.signatureOfOfficerOnDuty = signatureOfOfficerOnDuty;
	}
	/**
	 * @return the signatureOfOpsManagerOnDuty
	 */
	public String getSignatureOfOpsManagerOnDuty() {
		return signatureOfOpsManagerOnDuty;
	}
	/**
	 * @param signatureOfOpsManagerOnDuty the signatureOfOpsManagerOnDuty to set
	 */
	public void setSignatureOfOpsManagerOnDuty(String signatureOfOpsManagerOnDuty) {
		this.signatureOfOpsManagerOnDuty = signatureOfOpsManagerOnDuty;
	}
	/**
	 * @return the images
	 */
	public List<InputStream> getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(List<InputStream> images) {
		this.images = images;
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
		return "Incident [incidentId=" + incidentId + ", officerOnDutyName=" + officerOnDutyName + ", officerOnDutyId="
				+ officerOnDutyId + ", officerOnDutyDesignation=" + officerOnDutyDesignation + ", reportingSite="
				+ reportingSite + ", dateOfIncident=" + dateOfIncident + ", timeOfIncident=" + timeOfIncident
				+ ", dateOfIncidentReported=" + dateOfIncidentReported + ", partiesInvolved=" + partiesInvolved
				+ ", incidentCategory=" + incidentCategory + ", howIncidentOccurred=" + howIncidentOccurred
				+ ", whatIncidentOccurred=" + whatIncidentOccurred + ", whyIncidentOccurred=" + whyIncidentOccurred
				+ ", declarationByOfficerOnDuty=" + declarationByOfficerOnDuty + ", declarationofSecurityImplications="
				+ declarationofSecurityImplications + ", signatureOfOfficerOnDuty=" + signatureOfOfficerOnDuty
				+ ", signatureOfOpsManagerOnDuty=" + signatureOfOpsManagerOnDuty + ", images=" + images + ", createdDt="
				+ createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
}
