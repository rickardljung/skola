import SimEnvironment.*;

// BeamRegul class to be written by you
public class BeamRegul extends Thread {
	// IO interface declarations
	private AnalogSource analogIn;
	private AnalogSink analogOut;
	private AnalogSink analogRef;
	private ReferenceGenerator refgen;
	private PI pi;

	public BeamRegul(ReferenceGenerator refgen, Beam beam, int priority) {
		// ...
		// Code to initialize the IO
		this.refgen = refgen;
		analogIn = beam.getSource(0);
		analogOut = beam.getSink(0);
		analogRef = beam.getSink(1);
		setPriority(priority);
		pi = new PI("Beam!");
	}
	
	private double limit(double u, double umin, double umax) {
		if (u < umin) {
			u = umin;
		} else if (u > umax) {
			u = umax;
		} 
		return u;
	}

	public void run() {
		// ...
		// Code to perform IO
		double y = 0.0;
		double yref = 0.0;
		double u = 0.0;
		long duration;
		long t;
		try {
			while (!interrupted()) {
				t = System.currentTimeMillis();
				y = analogIn.get();
				yref = refgen.getRef();

				synchronized (pi) {
					u = pi.calculateOutput(y, yref);
					analogOut.set(limit(u, -10.0, 10.0));
					pi.updateState(u);
				}
				analogRef.set(yref);
				t = t + pi.getHMillis();
				duration = t - System.currentTimeMillis();
				if (duration > 0) {
					sleep(duration);
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}