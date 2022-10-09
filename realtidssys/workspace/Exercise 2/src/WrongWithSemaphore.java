import se.lth.control.realtime.*;


public class WrongWithSemaphore extends Thread {
	private int ti = 0;
	Semaphore mutex;
	
	public WrongWithSemaphore() {
		mutex = new Semaphore(1);
	}
	
	public void run() {
		int e;
		int u;
		int loops = 0;
		
		

		setPriority(4);

		try {
			e = 10;
			while (!Thread.interrupted()) {
				mutex.take();
				if (ti != 0) {
					u = e / ti;
				} else {
					u = 0;
				}
				mutex.give();
				loops++;
			}
		} catch (Exception ex) {
			System.out.println("Terminated after " + loops
					+ " iterations with " + ex);
			System.exit(1);
		}
	}

	public void setTi(int ti) {
		mutex.take();
		this.ti = ti;
		mutex.give();
	}

	public static void main(String[] args) {
		WrongWithSemaphore w1 = new WrongWithSemaphore();
		w1.start();
		int i = 0;
		try {
			while (!Thread.interrupted()) {
				w1.setTi(0);
				Thread.sleep(1);
				w1.setTi(2);
				Thread.sleep(1);
				i++;
				if (i > 100) {
					System.out.println("Main thread is alive");
					i = 0;
				}
			}
		} catch (InterruptedException e) {
			// Requested to stop
		}
		System.out.println("Thread stopped.");
	}
}