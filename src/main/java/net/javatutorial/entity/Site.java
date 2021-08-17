package net.javatutorial.entity;

import java.sql.Timestamp;

public class Site {
	private String siteId;
    private String siteName;
    private int dayShiftManpower;
    private int nightShiftManpower;
    private Timestamp createdDt;
    private Timestamp lastModifiedDt;
    
	public Site(String siteId, String siteName, int dayShiftManpower, int nightShiftManpower, Timestamp createdDt,
			Timestamp lastModifiedDt) {
		super();
		this.siteId = siteId;
		this.siteName = siteName;
		this.dayShiftManpower = dayShiftManpower;
		this.nightShiftManpower = nightShiftManpower;
		this.createdDt = createdDt;
		this.lastModifiedDt = lastModifiedDt;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
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
	 * @return the dayShiftManpower
	 */
	public int getDayShiftManpower() {
		return dayShiftManpower;
	}

	/**
	 * @param dayShiftManpower the dayShiftManpower to set
	 */
	public void setDayShiftManpower(int dayShiftManpower) {
		this.dayShiftManpower = dayShiftManpower;
	}

	/**
	 * @return the nightShiftManpower
	 */
	public int getNightShiftManpower() {
		return nightShiftManpower;
	}

	/**
	 * @param nightShiftManpower the nightShiftManpower to set
	 */
	public void setNightShiftManpower(int nightShiftManpower) {
		this.nightShiftManpower = nightShiftManpower;
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
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", dayShiftManpower=" + dayShiftManpower
				+ ", nightShiftManpower=" + nightShiftManpower + ", createdDt=" + createdDt + ", lastModifiedDt="
				+ lastModifiedDt + "]";
	}
}
