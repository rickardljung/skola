public class PeriodicWithInnerThread extends Base {
	private int period;
	private PeriodicThread pt;

	public PeriodicWithInnerThread(int period) {
		this.period = period;
		pt = new PeriodicThread();
	}
	
	public void start() {
		pt.start();
	}

	private class PeriodicThread extends Thread {
		public void run() {
			try {
				while (!Thread.interrupted()) {
					System.out.print(period);
					System.out.print(", ");
					Thread.sleep(period);

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread stopped");
		}

	}
	
	public static void main(String[] args) {
		PeriodicRunnable pb = new PeriodicRunnable(1000);
		Thread t = new Thread(pb);
		t.start();

	}

}
