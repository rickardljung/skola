// PID class to be written by you
public class PID {
	// Current PID parameters
	private PIDParameters p;
	
	private double I;
	private double D;
	private double e;
	private double v;
	private double ad,bd;
	private double oldY;
	private double y;
	
	// Constructor
	public PID (String name){
		p = new PIDParameters();
		
		p.K = -0.1;
		p.Ti = 0.0;
		p.Tr = 10.0;
		p.Td = 0.5;
		p.Beta = 1.0;
		p.H = 0.05;
		p.integratorOn = false;
		p.N = 5.0;
		ad = p.Td/(p.Td + p.N*p.H);
		bd = p.K*ad*p.N;
		new PIDGUI(this, p, name);
		setParameters(p);
		this.I = 0.0;
		this.v = 0.0;
		this.e = 0.0;	
	}

	// Calculates the control signal v.
	public synchronized double calculateOutput(double newY, double yref){
		y = newY;
		e = yref-y;
		D = ad*D -bd*(y- oldY);
		v = p.K*(p.Beta*yref - y) + I + D;
		
		return v;
	}
	
	// Updates the controller state.
	// Should use tracking-based anti-windup
	// Called from BeamRegul.
	public synchronized void updateState(double u){
		if(p.integratorOn){
			I = I + (p.K*p.H/p.Ti)*e + (p.H/p.Tr)*(u - v);
		}else{
			I = 0.0;
		}
		oldY = y;
	}
	
	// Returns the sampling interval expressed as a long.
	// Note: Explicit type casting needed
	public synchronized long getHMillis(){
		return (long) (p.H*1000.0);
	}
	
	// Sets the PIParameters.
	// Called from PIGUI.
	// Must clone newParameters.
	public synchronized void setParameters(PIDParameters newParameters){
		p = (PIDParameters) newParameters.clone();
		if (!p.integratorOn) {
			I = 0.0;
		}
	}
	
}