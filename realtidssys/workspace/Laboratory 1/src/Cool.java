import java.awt.Button;

import javax.swing.*;


public class Cool extends JFrame {
	public Cool(){
		setVisible(true);
		add(new Button("Cool Knapp"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	public static void main(String[] args) {
		new Cool();
	}
}
