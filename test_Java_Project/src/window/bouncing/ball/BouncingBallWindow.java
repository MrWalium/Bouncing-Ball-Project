package window.bouncing.ball;

import java.awt.*;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class BouncingBallWindow {
	private JFrame frame;
	private ImageIcon img;
	//private double frameHeightChangeSpeed;
	public BouncingBallWindow() {
		frame = new JFrame("Bouncing Ball");
		frame.setSize(400, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		img = new ImageIcon("C:\\Users\\wmari\\Downloads\\red_ball_image.png");
		frame.setIconImage(img.getImage());
		
		int x = frame.getLocation().x;
		int y = frame.getLocation().y;
		Shape panel = new Shape(frame, x, y);
		frame.add(panel);
		frame.setVisible(true);
		
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(frame.getSize().height);
			if(frame.getSize().height < 130) {
				frame.setSize(frame.getSize().width, 130);
			}
			panel.move(frame);
		}
	}
}
