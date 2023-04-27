package net.javatutorial.entity;

public class Manpower {
    private String siteName;
    private int requiredManpower;
    private int actualManpower;
    
	public Manpower(String siteName, int requiredManpower, int actualManpower) {
		super();
		this.siteName = siteName;
		this.requiredManpower = requiredManpower;
		this.actualManpower = actualManpower;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public int getRequiredManpower() {
		return requiredManpower;
	}

	public void setRequiredManpower(int requiredManpower) {
		this.requiredManpower = requiredManpower;
	}

	public int getActualManpower() {
		return actualManpower;
	}

	public void setActualManpower(int actualManpower) {
		this.actualManpower = actualManpower;
	}

	@Override
	public String toString() {
		return "Manpower [siteName=" + siteName + ", requiredManpower=" + requiredManpower + ", actualManpower="
				+ actualManpower + "]";
	}
	
}
