package window.bouncing.ball;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class BouncingBallWindow {
	private JFrame frame;
	private ImageIcon img;
	public BouncingBallWindow() {
		frame = new JFrame("Bouncing Ball");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		img = new ImageIcon("C:\\Users\\wmari\\Downloads\\red_ball_image.png");
		frame.setIconImage(img.getImage());
		
		Shape panel = new Shape(frame);
		frame.add(panel);
		frame.setVisible(true);
		
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			panel.move(frame);
		}
		
	}
}
