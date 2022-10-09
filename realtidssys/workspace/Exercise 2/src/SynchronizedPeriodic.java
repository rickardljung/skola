public class SynchronizedPeriodic extends Thread {
	private int period;
	private Screen s;

	public SynchronizedPeriodic(int period) {
		this.period = period;
		this.s = s;
	}

	public void run() {
		try {
			setPriority(7);
			while (!Thread.interrupted()) {
				synchronized(this) {
				System.out.print(period);
				System.out.print(", ");
				Thread.sleep(period);
				}
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread stopped");
	}
	
	
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			SynchronizedPeriodic p = new SynchronizedPeriodic(Integer.parseInt(args[i]));
			p.start();			
		}
		System.out.println(Thread.activeCount());

	}
	
	

}

