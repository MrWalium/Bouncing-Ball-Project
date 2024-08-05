package window.bouncing.ball;

import java.awt.*;
import javax.swing.*;

public class Shape extends JPanel {
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private double[] velocity = new double[2];
	//private boolean fall;
	private long timeStartFall;
	private double inelasticCollisionPercent;
	//private int width;
	//private int height;
	//private Color c;
	
	
	public Shape(JFrame frame) {
		super();
		if(frame != null) {
			Dimension size = frame.getSize();
			this.x = size.width/2-50;//150
			this.y = size.height/2-60;//100
		} else {
			this.x = 150;
			this.y = 100;
		}
		//frame = frame != null ? : ;
		velocity[0] = 0;
		velocity[1] = 0;
		
		timeStartFall = System.currentTimeMillis();
		//this.fall = true;
		this.inelasticCollisionPercent = 0.8;
		//this.width = width;
		//this.height = height;
		//this.c = c;
	}
	
	public void move(JFrame frame) {
		Dimension size = frame.getSize();
			
		velocity[1] += (System.currentTimeMillis() - timeStartFall)/1000.0 * 9.8;
		
		if(y+velocity[1] > size.height-124) {
			velocity[1] *= -inelasticCollisionPercent;
			y = size.height-124;
		} else if(y+velocity[1] < 0) {
			velocity[1] *= -inelasticCollisionPercent;
			y = 0;
		}
		
		int newX = (int)Math.round(velocity[0]) + x;
		int newY = (int)Math.round(velocity[1]) + y;
		
		
		//printStats(newX, newY, size);
		setXY(newX, newY);
		
		timeStartFall = System.currentTimeMillis();
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		this.repaint();
	}
	
	public void printStats(int newX, int newY, Dimension size) {
		System.out.println("Window Height: " + size.height + "\nWindow Width: " + size.width);
		System.out.println("Ball position = (" + newX + ", " + newY + ")");
		System.out.println("Ball velocity = (" + velocity[0] + ", " + velocity[1] + ") | Velocity rounded = (" + (int)Math.round(velocity[0]) + ", " + (int)Math.round(velocity[1]) + ")");
		System.out.println("Last \'Frame\' Time (in Milliseconds): " + timeStartFall + "\nCurrent \'Frame\' Time (in Milliseconds): " + System.currentTimeMillis() + "\nTime Between Last \'Frame\' and Current One: " + (System.currentTimeMillis() - timeStartFall));
		System.out.println("Force of Gravity Based on Time: " + (((System.currentTimeMillis() - timeStartFall)/1000.0) * 9.8) + "\n");
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(5));
		
		int shade = 40;
		g2.setColor(new Color(shade, shade, shade));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawOval(x, y, 80, 80);
		g2.setColor(Color.RED);
		g2.fillOval(x, y, 80, 80);
		
	}
}
