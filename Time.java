
// package mastermind;

import java.util.*;

public class Time {

		// Attribute
    int iniTime, finalTime;
	Calendar c;

		// Constructor
	public Time() {
		this.iniTime = 0;
		this.finalTime = 0;
	}

		// Getter && Setter
    public int GetTime() {
		return (finalTime - iniTime);
	}
    // public void SetTime(double newTime) {  iniTime; }

		// Method
	public void StartTime(){
		System.out.println("Start Crono");
		c = Calendar.getInstance();
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) + ", Second: " + c.get(Calendar.SECOND) );
		this.iniTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) );
	}

	public void StopTime(){
		System.out.println("Stop Crono");
		c = Calendar.getInstance();
		System.out.println( "Minute: " + c.get(Calendar.MINUTE) + ", Second: " + c.get(Calendar.SECOND) );
		this.finalTime = ( c.get(Calendar.MINUTE)*60 + c.get(Calendar.SECOND) );
	}

		// Test Method
	/*
	public static void main(String[] args) {
		Time t = new Time();
		t.StartTime();
		try { Thread.sleep(10000); }
		catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
		t.StopTime();

		System.out.println(t.GetTime());
	}
	*/
}
