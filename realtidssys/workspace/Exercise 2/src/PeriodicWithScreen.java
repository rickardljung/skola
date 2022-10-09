public class PeriodicWithScreen extends Thread {
	private int period;
	private Screen s;

	public PeriodicWithScreen(int period, Screen s) {
		this.period = period;
		this.s = s;
	}

	public void run() {
		try {
			setPriority(7);
			while (!Thread.interrupted()) {
				s.writePeriodic(period);
				Thread.sleep(period);
				}
				
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread stopped");
	}
	
	
	public static void main(String[] args) {
		Screen s = new Screen();
		for(int i = 0; i < args.length; i++) {
			PeriodicWithScreen p = new PeriodicWithScreen(Integer.parseInt(args[i]), s);
			p.start();			
		}
		System.out.println(Thread.activeCount());

	}
	
	

}
