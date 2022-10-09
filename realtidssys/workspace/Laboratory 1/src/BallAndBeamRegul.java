import SimEnvironment.*;

//BallAndBeamRegul class to be written by you
public class BallAndBeamRegul extends Thread {
	private ReferenceGenerator ref;
	private PI PIController;
	private PID PIDController;

	// IO interface declarations
	private AnalogSource analogInAngle;
	private AnalogSource analogInPosition;
	private AnalogSink analogOut;
	private AnalogSink analogRef;

	private double uMin = -10.0;
	private double uMax = 10.0;
	private double angleMin = -8.0;
	private double angleMax = 8.0;

	public BallAndBeamRegul(ReferenceGenerator refgen, BallAndBeam bb,
			int priority) {
		ref = refgen;
		ref = refgen;
		PIController = new PI("pi");
		PIDController = new PID("pid");
		analogInAngle = bb.getSource(1);
		analogInPosition = bb.getSource(0);
		analogOut = bb.getSink(0);
		analogRef = bb.getSink(1);
		setPriority(priority);
	}

	public void run() {
		long t = System.currentTimeMillis();
		double angleRef;
		while (!isInterrupted()) {
			double localRef = ref.getRef();
			double yPosition = analogInPosition.get();
			System.out.println("yPosition: " + yPosition);

			synchronized (PIDController) {
				angleRef = pidLimit(PIDController.calculateOutput(yPosition, localRef), angleMin, angleMax);
				PIDController.updateState(angleRef);
			}

			double yAngle = analogInAngle.get();
			System.out.println("yAngle: " + yAngle);

			synchronized (PIController) {
				double u = piLimit(PIController.calculateOutput(yAngle, angleRef), uMin,uMax);
				analogOut.set(u);
				PIController.updateState(u);
				System.out.println("Detta är u: " + u);
			}
			
			t = t + PIController.getHMillis();
			long duration = t - System.currentTimeMillis();
			if (duration > 0) {
				try {
					sleep(duration);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private double piLimit(double u, double uMin, double uMax) {
		if (u < uMin) {
			u = uMin;
		}
		else if (u > uMax) {
			u = uMax;
		}
		return u;
	}

	private double pidLimit(double angleRef, double angleMin, double angleMax) {
		if (angleRef < angleMin) {
			angleRef = angleMin;
		}
		else if (angleRef > angleMax) {
			angleRef = angleMax;
		}
		return angleRef;
	}
}