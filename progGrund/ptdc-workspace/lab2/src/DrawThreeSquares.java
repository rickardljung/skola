import se.lth.cs.ptdc.window.SimpleWindow;
import se.lth.cs.ptdc.square.Square;

public class DrawThreeSquares {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
		
		Square sq = new Square(300, 300, 200);
		sq.draw(w);
		
		sq=new Square(360, 280, 200);
		sq.draw(w);
		
		sq=new Square(220, 380, 200);
		sq.draw(w);
	}
}
