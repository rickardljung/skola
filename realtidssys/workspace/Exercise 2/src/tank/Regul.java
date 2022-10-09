package tank;

import SimEnvironment.*;

public class Regul extends Thread {
	// Analog inputs and outputs
	private AnalogSource yIn;
	private AnalogSink uOut;
	private AnalogSink rOut;

	// Box lamp outputs
	private DigitalButtonOut onButtonLamp;
	private DigitalButtonOut offButtonLamp;

	// Internal Monitors
	private ParameterMonitor paramMon = new ParameterMonitor();
	private ReferenceMonitor refMon = new ReferenceMonitor();
	private OnMonitor onMon = new OnMonitor();

	// Constructor
	// Here the internal monitor objects should be created and
	// the inputs and outputs should be initialized.
	public Regul(int priority, Box b, FirstOrderProcess proc) {
		setPriority(priority);

		onButtonLamp = b.getOnButtonLamp();
		offButtonLamp = b.getOffButtonLamp();
		onButtonLamp.set(isOn());
		offButtonLamp.set(isOn());

		yIn = proc.getSource(0);
		uOut = proc.getSink(0);
		rOut = proc.getSink(1);

	}

	// Public method to set K. Should not be synchronized.
	public void setK(double K) {
		paramMon.setK(K);
	}

	// Public method to set the reference. Should not be synchronized.
	public void setRef(double ref) {
		refMon.setRef(ref);
	}

	// Method to check if the controller is on. Should be private
	// since it is only called from Regul itself.
	private boolean isOn() {
		return onMon.isOn();
	}

	// Public methods to turn off and on the controller
	// Should not be synchronized. Should update the button lamps
	public void turnOff() {
		onMon.setOn(false);
		boolean isOn = isOn();
		onButtonLamp.set(isOn);
		offButtonLamp.set(!isOn);
	}

	public void turnOn() {
		onMon.setOn(true);
		boolean isOn = isOn();
		onButtonLamp.set(isOn);
		offButtonLamp.set(!isOn);

	}

	// Class definition for internal ParameterMonitor
	private class ParameterMonitor {
		private double K = 1.0;

		// Synchronized access methods. K should always be non-negative.
		public synchronized double getK() {
			return K;
		}

		public synchronized void setK(double K) {
			if (K >= 0) {
				this.K = K;
			} else {
				System.out.println("Illegal K value ignored: " + K);
			}
		}
	}

	// Class definition for internal ReferenceMonitor
	private class ReferenceMonitor {
		private double ref = 0.0;

		// Synchronized access methods
		public synchronized double getRef() {
			return ref;
		}

		public synchronized void setRef(double ref) {
			this.ref = ref;
		}
	}

	// Class definition for internal OnMonitor
	private class OnMonitor {
		private boolean on = false;

		// Synchronized access methods
		public synchronized boolean isOn() {
			return on;
		}

		public synchronized void setOn(boolean on) {
			this.on = on;
		}
	}

	// Run method
	public void run() {
		try {
			while (!interrupted()) {
				double y = yIn.get();
				double r = refMon.getRef();
				double K = paramMon.getK();
				 
				double u = 0.0;
				if (isOn()) {
					u = K * (r - y);
				}
				uOut.set(u);
				rOut.set(r);

				sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
