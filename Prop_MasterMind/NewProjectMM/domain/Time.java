package domain;

import java.util.*;

public class Time {

		// Attribute
    double iniTime, finalTime;
	int minut;
	Calendar c;

		// Constructor
	public Time() {
		this.iniTime = 0;
		this.finalTime = 0;
	}

		// Getter && Setter
    public double getTime() {

		return (finalTime - iniTime);
	}
    // public void SetTime(double newTime) {  iniTime; }

		// Method
	public void startTime(){
		System.out.println("Start_Crono");
		c = Calendar.getInstance();
		/*
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) +
							", Second: " + c.get(Calendar.SECOND) +
							", MillSecond: " + c.get(Calendar.MILLISECOND));
		*/
		this.iniTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) + (float)c.get(Calendar.MILLISECOND) / 1000 );
		// System.out.println(this.iniTime);
	}

	public void stopTime(){
		System.out.println("Stop_Crono");
		c = Calendar.getInstance();
		/*
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) +
							", Second: " + c.get(Calendar.SECOND) +
							", MillSecond: " + c.get(Calendar.MILLISECOND));
		*/
		this.finalTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) + (float)c.get(Calendar.MILLISECOND) / 1000 );
		// System.out.println(this.finalTime);
	}

	static public void printTime(double totalSecs){
		int minute = (int)totalSecs/ 60;
		int seconds = (int)totalSecs - minute*60;
		int milli = (int)((totalSecs - seconds) * 1000);

		System.out.println( "Minute: " + minute +
							", Second: " + seconds +
							", MillSecond: " + milli);
	}

	/*
		// Test Method
	public static void main(String[] args) {
		Time t = new Time();
		t.startTime();

			// Delay 10s
		try { Thread.sleep(10000); }
		catch(InterruptedException ex) { Thread.currentThread().interrupt(); }

		t.stopTime();

		System.out.println(t.getTime());
	}
	*/
}
