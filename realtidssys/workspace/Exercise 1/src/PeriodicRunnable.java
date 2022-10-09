public class PeriodicRunnable extends Base implements Runnable {
	private int period;
	
	public PeriodicRunnable(int period) {
		this.period = period;		
	}

	public void run() {
		try {
			Thread.currentThread().setPriority(7);
			System.out.println(Thread.currentThread().getPriority());
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
	
	public static void main(String[] args) {
		PeriodicRunnable pb = new PeriodicRunnable(1000);
		Thread t = new Thread(pb);
		t.start();

	}

}
