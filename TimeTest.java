
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeTest {

	Time t;
	String message;

	@Before
	public void setUp(){
		t = new Time();
		message = "Start Test && Initialization";
		System.out.println(message);
	}

	@After
	public void finTest(){
		message = "Fin Test";
		System.out.println(message);
	}

		// Esperas expected -> Tu defines la exepción
	// @Test(expected=IllegalCoinException.class)
		// Expected FileNotFoundExceptio
			// Puedes cambiar el comportamiento si salta Expected
	@Test
	public void testStartTimeEqual0() {//throws FileNotFoundException {
		// Equal0 -> Que es lo que quieres testear
		System.out.println("Test - StartTimee()");
		message = "Hi!" + "Robert"; // HardCoder
			// Comparar con lo que te da....
		assertEquals(message,"hi");
	}

	@Test
	public void testStopTime() {
		System.out.println("Test - StopTime()");
		message = "Hi!" + "Robert";
		assertEquals(message,"hi");
	}

	@Test
	public void testGetTime() {
		System.out.println("Test - GetTime()");
		message = "Hi!" + "Robert";
		assertEquals(message,"hi");
	}

	@Test
	public void testConstructora() {
		System.out.println("Test - Constructora()");
		message = "Hi!" + "Robert";
		assertEquals(message,"hi");
	}

	public static void main(String[] args) {
		TimeTest tt = new TimeTest();
		tt.setUp();
		tt.testStartTimeEqual0();
		tt.testStopTime();
		tt.testGetTime();
		tt.testConstructora();
		tt.finTest();
	}
}
