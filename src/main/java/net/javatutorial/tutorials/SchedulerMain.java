package net.javatutorial.tutorials;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

public class SchedulerMain {

    
    public static void main(String[] args) throws Exception {
//    	String message = VMSArchiveManagerDAO.moveVisitor();
//    	System.out.println("Visitor Records Moved: " + message);
    	// get the current date
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		// get the calendar last day of this month
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// check to see if the given date really is the last day of this month
		System.out.println( "check to see if the given date really is the last day of this month");
		System.out.println( cal.get(Calendar.DAY_OF_MONTH) == lastDay);
		if(cal.get(Calendar.DAY_OF_MONTH) == lastDay) {
			System.out.println( "it is the last day of the month, so batch job will do todhod db clean up");
			String line;
			try 
			{ 
				URL url = new URL( "https://k11-dev-todhod.herokuapp.com/archiveTodHod" ); 
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
				line = in.readLine(); 

				System.out.println( "TOD HOD Records emailed: " + line );	

				in.close(); 
			}
			catch (Exception e)
			{ 
				e.printStackTrace(); 
			} 
		}
    	
    }

}
