import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SimpleGUI{
	private JLabel label;
	
	public SimpleGUI() {
		JFrame frame = new JFrame("SimpleGUI");
		JPanel pane = new JPanel();
		JButton button = new JButton("Button");
		 label = new JLabel("Jag heter korv");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("actionPerformed" + Thread.currentThread().getPriority());
				label.setText("Do not press!");
			}			
		});
		
		System.out.println(Thread.currentThread().getPriority());
		
		
		pane.add(button);
		pane.add(label);
		frame.add(pane);
		
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
