
public class PeriodicWithAnonymousThread extends Base {
	private int period;
	private Thread t;
	
	public PeriodicWithAnonymousThread(final int period) {
		this.period = period;
		t = new Thread() {
			public void run() {
				try {
					while (!Thread.interrupted()) {
						System.out.print(period);
						System.out.print(", ");
						Thread.sleep(period);
					}
				} catch (InterruptedException e) {
					// Requested to stop
				}
				System.out.println("Thread stopped.");
			}
		};
	}

}
