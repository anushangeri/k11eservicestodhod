package net.javatutorial.entity;

import java.util.Date;
import java.util.Map;

public class Payslip {
	private String idNo;
	private String name;
	private String current_dt;
	
	private int dayCount;
	private double dayPay;
	
	private int nightCount;
	private double nightPay;
	
	private double kpiPay;
	private double phMcAdhocPay;
	private double siteICPay;
	
	private int fescCount;	
	private double fescPay;
	
	private int swvCount;	
	private double swvPay;
	
	private int ddjvCount;	
	private double ddjvPay;
	
	private int alpsCount;	
	private double alpsPay;
	
	private int delgroCount;	
	private double delgroPay;
	
	private int expandDNCount;	
	private double expandDNPay;
	
	private int cnwDNCount;		
	private double cnwPay;
	
	private int jlogCount;		
	private double jlogCPPay;
	
	private int tvDormCount;		
	private double tvDormPay;
	
	private double mbikePay;
	private double bonusPay;
	private double uniformAllowancePay;
	private double grossPay;
	private double advancedPay;
	private double deductionPay;
	private double balancePay;
	
	private Map<Date, Double> paymentsMade;

	/**
	 * @param idNo
	 * @param name
	 * @param current_dt
	 * @param dayCount
	 * @param dayPay
	 * @param nightCount
	 * @param nightPay
	 * @param kpiPay
	 * @param phMcAdhocPay
	 * @param siteICPay
	 * @param fescCount
	 * @param fescPay
	 * @param swvCount
	 * @param swvPay
	 * @param ddjvCount
	 * @param ddjvPay
	 * @param alpsCount
	 * @param alpsPay
	 * @param delgroCount
	 * @param delgroPay
	 * @param expandDNCount
	 * @param expandDNPay
	 * @param cnwDNCount
	 * @param cnwPay
	 * @param jlogCount
	 * @param jlogCPPay
	 * @param tvDormCount
	 * @param tvDormPay
	 * @param mbikePay
	 * @param bonusPay
	 * @param uniformAllowancePay
	 * @param grossPay
	 * @param advancedPay
	 * @param deductionPay
	 * @param balancePay
	 * @param paymentsMade
	 */
	public Payslip(String idNo, String name, String current_dt, int dayCount, double dayPay, int nightCount,
			double nightPay, double kpiPay, double phMcAdhocPay, double siteICPay, int fescCount, double fescPay,
			int swvCount, double swvPay, int ddjvCount, double ddjvPay, int alpsCount, double alpsPay, int delgroCount,
			double delgroPay, int expandDNCount, double expandDNPay, int cnwDNCount, double cnwPay, int jlogCount,
			double jlogCPPay, int tvDormCount, double tvDormPay, double mbikePay, double bonusPay,
			double uniformAllowancePay, double grossPay, double advancedPay, double deductionPay, double balancePay,
			Map<Date, Double> paymentsMade) {
		super();
		this.idNo = idNo;
		this.name = name;
		this.current_dt = current_dt;
		this.dayCount = dayCount;
		this.dayPay = dayPay;
		this.nightCount = nightCount;
		this.nightPay = nightPay;
		this.kpiPay = kpiPay;
		this.phMcAdhocPay = phMcAdhocPay;
		this.siteICPay = siteICPay;
		this.fescCount = fescCount;
		this.fescPay = fescPay;
		this.swvCount = swvCount;
		this.swvPay = swvPay;
		this.ddjvCount = ddjvCount;
		this.ddjvPay = ddjvPay;
		this.alpsCount = alpsCount;
		this.alpsPay = alpsPay;
		this.delgroCount = delgroCount;
		this.delgroPay = delgroPay;
		this.expandDNCount = expandDNCount;
		this.expandDNPay = expandDNPay;
		this.cnwDNCount = cnwDNCount;
		this.cnwPay = cnwPay;
		this.jlogCount = jlogCount;
		this.jlogCPPay = jlogCPPay;
		this.tvDormCount = tvDormCount;
		this.tvDormPay = tvDormPay;
		this.mbikePay = mbikePay;
		this.bonusPay = bonusPay;
		this.uniformAllowancePay = uniformAllowancePay;
		this.grossPay = grossPay;
		this.advancedPay = advancedPay;
		this.deductionPay = deductionPay;
		this.balancePay = balancePay;
		this.paymentsMade = paymentsMade;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the current_dt
	 */
	public String getCurrent_dt() {
		return current_dt;
	}

	/**
	 * @param current_dt the current_dt to set
	 */
	public void setCurrent_dt(String current_dt) {
		this.current_dt = current_dt;
	}

	/**
	 * @return the dayCount
	 */
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount the dayCount to set
	 */
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * @return the dayPay
	 */
	public double getDayPay() {
		return dayPay;
	}

	/**
	 * @param dayPay the dayPay to set
	 */
	public void setDayPay(double dayPay) {
		this.dayPay = dayPay;
	}

	/**
	 * @return the nightCount
	 */
	public int getNightCount() {
		return nightCount;
	}

	/**
	 * @param nightCount the nightCount to set
	 */
	public void setNightCount(int nightCount) {
		this.nightCount = nightCount;
	}

	/**
	 * @return the nightPay
	 */
	public double getNightPay() {
		return nightPay;
	}

	/**
	 * @param nightPay the nightPay to set
	 */
	public void setNightPay(double nightPay) {
		this.nightPay = nightPay;
	}

	/**
	 * @return the kpiPay
	 */
	public double getKpiPay() {
		return kpiPay;
	}

	/**
	 * @param kpiPay the kpiPay to set
	 */
	public void setKpiPay(double kpiPay) {
		this.kpiPay = kpiPay;
	}

	/**
	 * @return the phMcAdhocPay
	 */
	public double getPhMcAdhocPay() {
		return phMcAdhocPay;
	}

	/**
	 * @param phMcAdhocPay the phMcAdhocPay to set
	 */
	public void setPhMcAdhocPay(double phMcAdhocPay) {
		this.phMcAdhocPay = phMcAdhocPay;
	}

	/**
	 * @return the siteICPay
	 */
	public double getSiteICPay() {
		return siteICPay;
	}

	/**
	 * @param siteICPay the siteICPay to set
	 */
	public void setSiteICPay(double siteICPay) {
		this.siteICPay = siteICPay;
	}

	/**
	 * @return the fescCount
	 */
	public int getFescCount() {
		return fescCount;
	}

	/**
	 * @param fescCount the fescCount to set
	 */
	public void setFescCount(int fescCount) {
		this.fescCount = fescCount;
	}

	/**
	 * @return the fescPay
	 */
	public double getFescPay() {
		return fescPay;
	}

	/**
	 * @param fescPay the fescPay to set
	 */
	public void setFescPay(double fescPay) {
		this.fescPay = fescPay;
	}

	/**
	 * @return the swvCount
	 */
	public int getSwvCount() {
		return swvCount;
	}

	/**
	 * @param swvCount the swvCount to set
	 */
	public void setSwvCount(int swvCount) {
		this.swvCount = swvCount;
	}

	/**
	 * @return the swvPay
	 */
	public double getSwvPay() {
		return swvPay;
	}

	/**
	 * @param swvPay the swvPay to set
	 */
	public void setSwvPay(double swvPay) {
		this.swvPay = swvPay;
	}

	/**
	 * @return the ddjvCount
	 */
	public int getDdjvCount() {
		return ddjvCount;
	}

	/**
	 * @param ddjvCount the ddjvCount to set
	 */
	public void setDdjvCount(int ddjvCount) {
		this.ddjvCount = ddjvCount;
	}

	/**
	 * @return the ddjvPay
	 */
	public double getDdjvPay() {
		return ddjvPay;
	}

	/**
	 * @param ddjvPay the ddjvPay to set
	 */
	public void setDdjvPay(double ddjvPay) {
		this.ddjvPay = ddjvPay;
	}

	/**
	 * @return the alpsCount
	 */
	public int getAlpsCount() {
		return alpsCount;
	}

	/**
	 * @param alpsCount the alpsCount to set
	 */
	public void setAlpsCount(int alpsCount) {
		this.alpsCount = alpsCount;
	}

	/**
	 * @return the alpsPay
	 */
	public double getAlpsPay() {
		return alpsPay;
	}

	/**
	 * @param alpsPay the alpsPay to set
	 */
	public void setAlpsPay(double alpsPay) {
		this.alpsPay = alpsPay;
	}

	/**
	 * @return the delgroCount
	 */
	public int getDelgroCount() {
		return delgroCount;
	}

	/**
	 * @param delgroCount the delgroCount to set
	 */
	public void setDelgroCount(int delgroCount) {
		this.delgroCount = delgroCount;
	}

	/**
	 * @return the delgroPay
	 */
	public double getDelgroPay() {
		return delgroPay;
	}

	/**
	 * @param delgroPay the delgroPay to set
	 */
	public void setDelgroPay(double delgroPay) {
		this.delgroPay = delgroPay;
	}

	/**
	 * @return the expandDNCount
	 */
	public int getExpandDNCount() {
		return expandDNCount;
	}

	/**
	 * @param expandDNCount the expandDNCount to set
	 */
	public void setExpandDNCount(int expandDNCount) {
		this.expandDNCount = expandDNCount;
	}

	/**
	 * @return the expandDNPay
	 */
	public double getExpandDNPay() {
		return expandDNPay;
	}

	/**
	 * @param expandDNPay the expandDNPay to set
	 */
	public void setExpandDNPay(double expandDNPay) {
		this.expandDNPay = expandDNPay;
	}

	/**
	 * @return the cnwDNCount
	 */
	public int getCnwDNCount() {
		return cnwDNCount;
	}

	/**
	 * @param cnwDNCount the cnwDNCount to set
	 */
	public void setCnwDNCount(int cnwDNCount) {
		this.cnwDNCount = cnwDNCount;
	}

	/**
	 * @return the cnwPay
	 */
	public double getCnwPay() {
		return cnwPay;
	}

	/**
	 * @param cnwPay the cnwPay to set
	 */
	public void setCnwPay(double cnwPay) {
		this.cnwPay = cnwPay;
	}

	/**
	 * @return the jlogCount
	 */
	public int getJlogCount() {
		return jlogCount;
	}

	/**
	 * @param jlogCount the jlogCount to set
	 */
	public void setJlogCount(int jlogCount) {
		this.jlogCount = jlogCount;
	}

	/**
	 * @return the jlogCPPay
	 */
	public double getJlogCPPay() {
		return jlogCPPay;
	}

	/**
	 * @param jlogCPPay the jlogCPPay to set
	 */
	public void setJlogCPPay(double jlogCPPay) {
		this.jlogCPPay = jlogCPPay;
	}

	/**
	 * @return the tvDormCount
	 */
	public int getTvDormCount() {
		return tvDormCount;
	}

	/**
	 * @param tvDormCount the tvDormCount to set
	 */
	public void setTvDormCount(int tvDormCount) {
		this.tvDormCount = tvDormCount;
	}

	/**
	 * @return the tvDormPay
	 */
	public double getTvDormPay() {
		return tvDormPay;
	}

	/**
	 * @param tvDormPay the tvDormPay to set
	 */
	public void setTvDormPay(double tvDormPay) {
		this.tvDormPay = tvDormPay;
	}

	/**
	 * @return the mbikePay
	 */
	public double getMbikePay() {
		return mbikePay;
	}

	/**
	 * @param mbikePay the mbikePay to set
	 */
	public void setMbikePay(double mbikePay) {
		this.mbikePay = mbikePay;
	}

	/**
	 * @return the bonusPay
	 */
	public double getBonusPay() {
		return bonusPay;
	}

	/**
	 * @param bonusPay the bonusPay to set
	 */
	public void setBonusPay(double bonusPay) {
		this.bonusPay = bonusPay;
	}

	/**
	 * @return the uniformAllowancePay
	 */
	public double getUniformAllowancePay() {
		return uniformAllowancePay;
	}

	/**
	 * @param uniformAllowancePay the uniformAllowancePay to set
	 */
	public void setUniformAllowancePay(double uniformAllowancePay) {
		this.uniformAllowancePay = uniformAllowancePay;
	}

	/**
	 * @return the grossPay
	 */
	public double getGrossPay() {
		return grossPay;
	}

	/**
	 * @param grossPay the grossPay to set
	 */
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	/**
	 * @return the advancedPay
	 */
	public double getAdvancedPay() {
		return advancedPay;
	}

	/**
	 * @param advancedPay the advancedPay to set
	 */
	public void setAdvancedPay(double advancedPay) {
		this.advancedPay = advancedPay;
	}

	/**
	 * @return the deductionPay
	 */
	public double getDeductionPay() {
		return deductionPay;
	}

	/**
	 * @param deductionPay the deductionPay to set
	 */
	public void setDeductionPay(double deductionPay) {
		this.deductionPay = deductionPay;
	}

	/**
	 * @return the balancePay
	 */
	public double getBalancePay() {
		return balancePay;
	}

	/**
	 * @param balancePay the balancePay to set
	 */
	public void setBalancePay(double balancePay) {
		this.balancePay = balancePay;
	}

	/**
	 * @return the paymentsMade
	 */
	public Map<Date, Double> getPaymentsMade() {
		return paymentsMade;
	}

	/**
	 * @param paymentsMade the paymentsMade to set
	 */
	public void setPaymentsMade(Map<Date, Double> paymentsMade) {
		this.paymentsMade = paymentsMade;
	}

	@Override
	public String toString() {
		return "Payslip [idNo=" + idNo + ", name=" + name + ", current_dt=" + current_dt + ", dayCount=" + dayCount
				+ ", dayPay=" + dayPay + ", nightCount=" + nightCount + ", nightPay=" + nightPay + ", kpiPay=" + kpiPay
				+ ", phMcAdhocPay=" + phMcAdhocPay + ", siteICPay=" + siteICPay + ", fescCount=" + fescCount
				+ ", fescPay=" + fescPay + ", swvCount=" + swvCount + ", swvPay=" + swvPay + ", ddjvCount=" + ddjvCount
				+ ", ddjvPay=" + ddjvPay + ", alpsCount=" + alpsCount + ", alpsPay=" + alpsPay + ", delgroCount="
				+ delgroCount + ", delgroPay=" + delgroPay + ", expandDNCount=" + expandDNCount + ", expandDNPay="
				+ expandDNPay + ", cnwDNCount=" + cnwDNCount + ", cnwPay=" + cnwPay + ", jlogCount=" + jlogCount
				+ ", jlogCPPay=" + jlogCPPay + ", tvDormCount=" + tvDormCount + ", tvDormPay=" + tvDormPay
				+ ", mbikePay=" + mbikePay + ", bonusPay=" + bonusPay + ", uniformAllowancePay=" + uniformAllowancePay
				+ ", grossPay=" + grossPay + ", advancedPay=" + advancedPay + ", deductionPay=" + deductionPay
				+ ", balancePay=" + balancePay + ", paymentsMade=" + paymentsMade + "]";
	}
	
}
