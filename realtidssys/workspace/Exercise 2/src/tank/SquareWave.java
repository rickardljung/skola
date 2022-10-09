package tank;

public class SquareWave extends Thread {
	private Regul regul;
	private int sign = 1;

	private AmplitudeMonitor ampMon = new AmplitudeMonitor();

	// Internal AmplitudeMonitor class
	// Constructor not necessary
	private class AmplitudeMonitor {
		private double amp = 0.0;

		// Synchronized access methods. The amplitude should always be
		// non-negative.
		public synchronized double getAmp() {
			return amp;
		}

		public synchronized void setAmp(double amp) {
			this.amp = Math.max(amp, 0);

		}
	}

	// Constructor
	public SquareWave(Regul regul, int priority) {
		this.regul = regul;
		setPriority(priority);
		
		
	}

	// Public methods to decrease and increase the amplitude by delta. Called
	// from Buttons
	// Should be synchronized on ampMon. Should also call the setRef method in
	// Regul
	public void incAmp(double delta) {
		synchronized (ampMon) {
			ampMon.setAmp(ampMon.getAmp() + delta);
		}
		setRef();

	}

	private void setRef() {
		regul.setRef(sign * ampMon.getAmp());

	}

	public void decAmp(double delta) {
		incAmp(-delta);

	}

	// run method
	public void run() {
		try {

			while (!interrupted()) {
				sign = -sign;
				setRef();
				sleep(10000);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
