import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.square.Square;

public class PrintSquaresClicks {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "PrintSquaresClicks");
		
		Square sq = new Square(0, 0, 50);
		
		int x=0;
		int y=0;
		
		while (true) {
			
			
			w.waitForMouseClick();
			sq.erase(w);
			
			sq.move(w.getMouseX()-x, w.getMouseY()-y);
			sq.draw(w);
			
			x=w.getMouseX();
			y=w.getMouseY();
			
			
			
		}
	}
}
