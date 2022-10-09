public class Periodic extends Thread {
	private int period;

	public Periodic(int period) {
		this.period = period;
	}

	public void run() {
		try {
			setPriority(7);
			System.out.println(getPriority()); 
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
		for(int i = 0; i < args.length; i++) {
			Periodic p = new Periodic(Integer.parseInt(args[i]));
			p.start();			
		}
		System.out.println(Thread.activeCount());

	}
	
	

}

