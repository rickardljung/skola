public class PI {
	// Current PI parameters
	private PIParameters par;
	private double I, e, u, v;
	
	// Constructor
	public PI(String name) {
		par = new PIParameters();
		par.K = 1.0;
		par.Ti = 0.0;
		par.Tr = 10.0;
		par.Beta = 1.0;
		par.H = 0.1;
		par.integratorOn = false;
		I = 0.0;
		e = 0.0;
		u = 0.0;
		v = 0.0;
		new PIGUI(this, par, name);
		setParameters(par);
	}
	
	// Calculates the control signal v.
	// Called from BeamRegul.
	public synchronized double calculateOutput(double y, double yref) {
		e = yref - y;
		v = par.K * (par.Beta * yref - y) + I;
		return v;
	}
	
	// Updates the controller state.
	// Should use tracking-based anti-windup
	// Called from BeamRegul.
	public synchronized void updateState(double u) {
		if(par.integratorOn) {
		I = I + ((par.K * par.H)/par.Ti)*e + (par.H/par.Tr)*(u - v);
		} else {
			I = 0.0;
		}
		
	}
	
	// Returns the sampling interval expressed as a long.
	// Note: Explicit type casting needed
	public synchronized long getHMillis() {
		return (long) (par.H * 1000.0);
	}
	
	// Sets the PIParameters.
	// Called from PIGUI.
	// Must clone newParameters.
	public synchronized void setParameters(PIParameters newParameters) {
		par = (PIParameters)newParameters.clone();
		if(!par.integratorOn) {
			I = 0.0;
		}
	}
}