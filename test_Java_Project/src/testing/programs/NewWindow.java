package testing.programs;

import java.awt.*;
import javax.swing.*;

public class NewWindow {

	JFrame frame = new JFrame();
	JLabel label = new JLabel("Hello!");

	NewWindow() {

		label.setBounds(0, 0, 100, 50);
		label.setFont(new Font(null, Font.PLAIN, 25));
		label.setForeground(Color.white);
		frame.getContentPane().setBackground(Color.black);
		frame.add(label);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("C:\\Users\\wmari\\Downloads\\Screenshot.png");
		frame.setIconImage(img.getImage());
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}