
// package mastermind;

import java.util.*;

public class Time {

		// Attribute
    double iniTime, finalTime;

		// Constructor
	public Time() {
		this.iniTime = 0.0;
		this.finalTime = 0.0;
	}

		// Getter && Setter
    public double GetTime() {
		return (finalTime-iniTime);
	}
    // public void SetTime(double newTime) {  iniTime; }

		// Method
	public void StartTime(){
		System.out.println("Start Crono");
		this.iniTime = 1.0;
	}

	public void StopTime(){
		System.out.println("Stop Crono");
		this.finalTime = 4.0;
	}

	public static void main(String[] args) {
		Time t = new Time();
		t.StartTime();
		t.StopTime();
		System.out.println(t.GetTime());

	    Calendar c = Calendar.getInstance();
	    System.out.println("Time: "+ c.HOUR +" "+c.MINUTE+" "+c.SECOND+" "+c.MILLISECOND);
	}
}
