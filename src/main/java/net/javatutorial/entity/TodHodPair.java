/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.javatutorial.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodHodPair {
    private String enternricfin;
    private String shift;
    private String timestampAsStr;
    private Timestamp timestamp;
    private String securityofficername;
    private String todDateAsStr;
    private Date todDate;
    private String todTimeAsStr;
    private String hodDateAsStr;
    private Date hodDate;
    private String hodTimeAsStr;
	private String areyoutodhod;
    private String dutysite;
    private String standbyremark;
    
    public TodHodPair(String enternricfin, String shift, String timestampAsStr, Timestamp timestamp,
			String securityofficername, String todDateAsStr, Date todDate, String todTimeAsStr, String hodDateAsStr,
			Date hodDate, String hodTimeAsStr, String areyoutodhod, String dutysite, String standbyremark) {
		super();
		this.enternricfin = enternricfin;
		this.shift = shift;
		this.timestampAsStr = timestampAsStr;
		this.timestamp = timestamp;
		this.securityofficername = securityofficername;
		this.todDateAsStr = todDateAsStr;
		this.todDate = todDate;
		this.todTimeAsStr = todTimeAsStr;
		this.hodDateAsStr = hodDateAsStr;
		this.hodDate = hodDate;
		this.hodTimeAsStr = hodTimeAsStr;
		this.areyoutodhod = areyoutodhod;
		this.dutysite = dutysite;
		this.standbyremark = standbyremark;
	}

    
	public TodHodPair(String enternricfin, String shift, String timestampAsStr, String securityofficername,
			String todDateAsStr, String todTimeAsStr, String hodDateAsStr, String hodTimeAsStr,
			String dutysite, String standbyremark) {
		super();
		this.enternricfin = enternricfin;
		this.shift = shift;
		this.timestampAsStr = timestampAsStr;
		this.securityofficername = securityofficername;
		this.todDateAsStr = todDateAsStr;
		this.todTimeAsStr = todTimeAsStr;
		this.hodDateAsStr = hodDateAsStr;
		this.hodTimeAsStr = hodTimeAsStr;
		this.dutysite = dutysite;
		this.standbyremark = standbyremark;
	}


    

    
    public String getEnternricfin() {
        return enternricfin;
    }

    public void setEnternricfin(String enternricfin) {
        this.enternricfin = enternricfin;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getTimestampAsStr() {
        return timestampAsStr;
    }

    public void setTimestampAsStr(String timestampAsStr) {
        this.timestampAsStr = timestampAsStr;
    }

    public Timestamp getTimestamp() {
        Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date parsedDate = dateFormat.parse(timestampAsStr);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI TIMESTAMP CONVERSION FAILED IN TO.TODHODDETAILS: " + e);
        }
        return timestamp;
    }

    public void setTimestamp(String timestampAsStr) {
         try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date parsedDate = dateFormat.parse(timestampAsStr);
            Timestamp timestampp = new java.sql.Timestamp(parsedDate.getTime());
            this.timestamp = timestampp;
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI TIMESTAMP CONVERSION FAILED IN TO.TODHODDETAILS: " + e);
        }
    }

    public String getSecurityofficername() {
        return securityofficername;
    }

    public void setSecurityofficername(String securityofficername) {
        this.securityofficername = securityofficername;
    }

    public String getTodDateAsStr() {
        return todDateAsStr;
    }

    public void setTodDateAsStr(String todDateAsStr) {
        this.todDateAsStr = todDateAsStr;
    }

    public Date getTodDate() {
        Date todDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            todDate = dateFormat.parse(todDateAsStr);
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI DATE CONVERSION FAILED IN TO.TODHODPAIR: " + e);
        }
        return todDate;
    }

    public void setTodDate(String todDateAsStr) {
        Date todDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            todDate = dateFormat.parse(todDateAsStr);
            this.todDate = todDate;
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI DATE CONVERSION FAILED IN TO.TODHODPAIR: " + e);
        }
        
    }

    public String getTodTimeAsStr() {
        return todTimeAsStr;
    }

    public void setTodTimeAsStr(String todTimeAsStr) {
        this.todTimeAsStr = todTimeAsStr;
    }
/////////
    public String getHodDateAsStr() {
        return hodDateAsStr;
    }

    public void setHodDateAsStr(String hodDateAsStr) {
        this.hodDateAsStr = hodDateAsStr;
    }

    public Date getHodDate() {
        Date hodDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            hodDate = dateFormat.parse(hodDateAsStr);
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI DATE CONVERSION FAILED IN TO.TODHODPAIR: " + e);
        }
        return hodDate;
    }

    public void setHodDate(String hodDateAsStr) {
        Date hodDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            hodDate = dateFormat.parse(hodDateAsStr);
            this.hodDate = hodDate;
        } catch(Exception e) { //this generic but you can control another types of exception
            System.out.println("SHANGERI DATE CONVERSION FAILED IN TO.TODHODPAIR: " + e);
        }
        
    }

    public String getHodTimeAsStr() {
        return hodTimeAsStr;
    }

    public void setHodTimeAsStr(String hodTimeAsStr) {
        this.hodTimeAsStr = hodTimeAsStr;
    }
    
    public String getAreyoutodhod() {
        return areyoutodhod;
    }

    public void setAreyoutodhod(String areyoutodhod) {
        this.areyoutodhod = areyoutodhod;
    }

    public String getDutysite() {
        return dutysite;
    }

    public void setDutysite(String dutysite) {
        this.dutysite = dutysite;
    }

    public String getStandbyremark() {
        return standbyremark;
    }

    public void setStandbyremark(String standbyremark) {
        this.standbyremark = standbyremark;
    }

	@Override
	public String toString() {
		return "TodHodPair [enternricfin=" + enternricfin + ", shift=" + shift + ", timestampAsStr=" + timestampAsStr
				+ ", timestamp=" + timestamp + ", securityofficername=" + securityofficername + ", todDateAsStr="
				+ todDateAsStr + ", todDate=" + todDate + ", todTimeAsStr=" + todTimeAsStr + ", hodDateAsStr="
				+ hodDateAsStr + ", hodDate=" + hodDate + ", hodTimeAsStr=" + hodTimeAsStr + ", areyoutodhod="
				+ areyoutodhod + ", dutysite=" + dutysite + ", standbyremark=" + standbyremark + "]";
	}

    
            
    
}
