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
	private int lastHeight;
	private int lastWidth;
	private double mass;
	private int centeredX;
	private int centeredY;
	private int currentX;
	private int currentY;
	//private int width;
	//private int height;
	//private Color c;
	
	
	public Shape(JFrame frame, int x, int y) {
		super();
		if(frame != null) {
			Dimension size = frame.getSize();
			this.x = size.width/2-50;//150
			this.y = size.height/2-60;//100
		} else {
			this.x = 150;
			this.y = 100;
		}
		mass = 1.7;
		//frame = frame != null ? : ;
		velocity[0] = 0;
		velocity[1] = 0;
		
		timeStartFall = System.currentTimeMillis();
		//this.fall = true;
		this.inelasticCollisionPercent = 0.8;
		centeredX = x;
		centeredY = y;
		currentX = x;
		currentY = y;
		//this.width = width;
		//this.height = height;
		//this.c = c;
	}
	
	public void move(JFrame frame) {
		Dimension size = frame.getSize();
		if(frame.getLocation().x != centeredX && frame.getLocation().x != currentX) {
			x += currentX - frame.getLocation().x;
			currentX = frame.getLocation().x;
		} else if (frame.getLocation().y != centeredY && frame.getLocation().y != currentY) {
			y += currentY - frame.getLocation().y;
			currentY = frame.getLocation().y;
		}
		
		/*
		 * double variable drag
		 * density of air affected by temperature, pressure, and specific gas constant (287.26 joules per kilogram per kelvin for air); It can
		 * also be effected by water vapor
		 * density could be 1.225 for 15 C and pressure unknown, 1.293 for 273 K (-0.15 C) at pressure 101.325
		 * drag = 1/2 * density (of air) * v squared * drag coefficient (greater = more drag, less = less drag) * area
		*/
		double dragX = 0.5 * 1.225 * Math.pow(velocity[0] * ((System.currentTimeMillis() - timeStartFall)/1000.0), 2) * 0.47;
		double dragY = 0.5 * 1.225 * Math.pow(velocity[1] * ((System.currentTimeMillis() - timeStartFall)/1000.0), 2) * 0.47;
		
		
		//System.out.println(dragX + " " + velocity[0]);
		if(velocity[0] <= 0) {
			velocity[0] -= dragX;
		} else {
			velocity[0] += dragX;
		}
		velocity[1] += ((System.currentTimeMillis() - timeStartFall)/1000.0 * 9.8 * mass - dragY)/mass;
		//System.out.println("Drag: " + drag);
		//System.out.println("Gravity: " + (((System.currentTimeMillis() - timeStartFall)/1000.0 * 9.8 * mass - drag)/mass));
		
		if(y+velocity[1] > size.height-124) {
			int speed = (int) ((Math.abs(lastHeight-size.height) / (System.currentTimeMillis() - timeStartFall)) * (1000.0 / (System.currentTimeMillis() - timeStartFall)));
			//System.out.println(speed);
			velocity[1] = velocity[1] * -inelasticCollisionPercent - speed;
			y = size.height-124;
		} else if(y+velocity[1] < 0) {
			int speed = (int) ((Math.abs(lastHeight-size.height) / (System.currentTimeMillis() - timeStartFall)) * (1000.0 / (System.currentTimeMillis() - timeStartFall)));
			velocity[1] *= -inelasticCollisionPercent - speed;
			y = 0;
		}
		
		if(x+velocity[0] > size.width-100) {
			int speed = (int) ((Math.abs(lastWidth-size.width) / (System.currentTimeMillis() - timeStartFall)) * (1000.0 / (System.currentTimeMillis() - timeStartFall)));
			//System.out.println(speed);
			//System.out.println(lastWidth-size.width);
			velocity[0] = velocity[0] * -inelasticCollisionPercent - speed;
			x = size.width-100;
		} else if(x+velocity[0] < 0) {
			//int speed = (int) ((Math.abs(lastWidth-size.width) / (System.currentTimeMillis() - timeStartFall)) * (1000.0 / (System.currentTimeMillis() - timeStartFall)));
			//System.out.println(speed);
			velocity[0] = velocity[0] * -inelasticCollisionPercent;// - speed;
			x = 0;
		}
		
		int newX = (int)Math.round(velocity[0]) + x;
		int newY = (int)Math.round(velocity[1]) + y;
		
		
		//printStats(newX, newY, size);
		setXY(newX, newY);
		
		lastHeight = size.height;
		lastWidth = size.width;
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
