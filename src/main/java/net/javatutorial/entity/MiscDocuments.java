package net.javatutorial.entity;

import java.io.InputStream;
import java.sql.Timestamp;

public class MiscDocuments {
	private String documentId;
	private String employeeId;
	private InputStream document;
	private String description;
	private String createdBy;
    private String lastModifiedBy;
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	/**
	 * @param documentId
	 * @param employeeId
	 * @param document
	 * @param description
	 * @param createdBy
	 * @param lastModifiedBy
	 */
	public MiscDocuments(String documentId, String employeeId, InputStream document, String description,
			String createdBy, String lastModifiedBy) {
		super();
		this.documentId = documentId;
		this.employeeId = employeeId;
		this.document = document;
		this.description = description;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
	}
	
	/**
	 * @param documentId
	 * @param employeeId
	 * @param document
	 * @param description
	 * @param createdBy
	 * @param lastModifiedBy
	 * @param createdDt
	 * @param lastModifiedDt
	 */
	public MiscDocuments(String documentId, String employeeId, InputStream document, String description,
			String createdBy, String lastModifiedBy, Timestamp createdDt, Timestamp lastModifiedDt) {
		super();
		this.documentId = documentId;
		this.employeeId = employeeId;
		this.document = document;
		this.description = description;
		this.createdBy = createdBy;
		this.lastModifiedBy = lastModifiedBy;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}
	/**
	 * @param documentId the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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
	 * @return the document
	 */
	public InputStream getDocument() {
		return document;
	}
	/**
	 * @param document the document to set
	 */
	public void setDocument(InputStream document) {
		this.document = document;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
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
		return "MiscDocuments [documentId=" + documentId + ", employeeId=" + employeeId + ", document=" + document
				+ ", description=" + description + ", createdBy=" + createdBy + ", lastModifiedBy=" + lastModifiedBy
				+ ", createdDt=" + createdDt + ", lastModifiedDt=" + lastModifiedDt + "]";
	}
}
