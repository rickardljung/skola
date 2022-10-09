package tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OpCom extends Thread {
	private Regul regul;

	// Constructor
	public OpCom(Regul regul, int priority) {
		this.regul = regul;
		setPriority(priority);

	}

	// run method
	public void run() {

		while (!interrupted()) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("K = ");
			try {
				regul.setK(Double.parseDouble(in.readLine()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Not a number.");
			}
		}

	}
}
