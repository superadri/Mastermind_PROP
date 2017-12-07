import java.awt.EventQueue;

public class MainMastermind {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CtrlPresentacio ctrlP = new CtrlPresentacio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
