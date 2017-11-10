
// package mastermind;

import java.util.*;

public class Time {

		// Attribute
    double iniTime, finalTime;
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
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) +
							", Second: " + c.get(Calendar.SECOND) +
							", MillSecond: " + c.get(Calendar.MILLISECOND));
		this.iniTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) + (float)c.get(Calendar.MILLISECOND) / 1000 );
	}

	public void stopTime(){
		System.out.println("Stop_Crono");
		c = Calendar.getInstance();
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) +
							", Second: " + c.get(Calendar.SECOND) +
							", MillSecond: " + c.get(Calendar.MILLISECOND));
		this.finalTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) + (float)c.get(Calendar.MILLISECOND) / 1000 );
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
