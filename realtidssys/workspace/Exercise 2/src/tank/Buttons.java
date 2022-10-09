package tank;

import SimEnvironment.*;

public class Buttons extends Thread {
	private Regul regul;
	private SquareWave square;

	// Inputs and outputs
	private DigitalButtonIn onInput;
	private DigitalButtonIn offInput;
	private DigitalButtonIn incInput;
	private DigitalButtonIn decInput;

	// Constructor
	public Buttons(Regul regul, SquareWave square, int priority, Box b) {
		this.regul = regul;
		this.square = square;
		setPriority(priority);

		onInput = b.getOnButtonInput();
		offInput = b.getOffButtonInput();
		incInput = b.getIncButtonInput();
		decInput = b.getDecButtonInput();
	}

	// run method
	public void run() {
		final int p = 10;
		final double delta = (10.0 / (60.0 * 1000.0)) * p;
		try {
			while (!interrupted()) {
				if (onInput.get()) {
					regul.turnOn();
				}
				if (offInput.get()) {
					regul.turnOff();
				}
				if (incInput.get()) {
					square.incAmp(delta);
				}
				if (decInput.get()) {
					square.decAmp(delta);
				}				
				sleep(p);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
