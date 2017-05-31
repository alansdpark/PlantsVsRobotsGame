import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Lawnmower implements ActionListener {
	private BufferedImage lawnmowerImage;
	private Image finalLawnmowerImage;
	private Timer timer;
	private PlantsVsRobotsPanel panel;
	private int x;
	private int y;
	private boolean hasStarted;
	public Lawnmower(PlantsVsRobotsPanel panel, int x, int y) {
		hasStarted = false;
		this.panel = panel;
		this.x = x;
		this.y = y;
		try {
			lawnmowerImage = ImageIO.read(new File("Lawnmower.png"));
		}
		catch (IOException e) {
			System.out.println("Could not load Lawnmower.png");
		}
		finalLawnmowerImage = lawnmowerImage.getScaledInstance(54, 42, Image.SCALE_DEFAULT);
		timer = new Timer(30, this);
	}
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	public void draw(Graphics g) {
		g.drawImage(finalLawnmowerImage, x, y, null);
	}
	
	public void startTimer() {
		if (hasStarted == false) {
			timer.start();
		}
		else {
			System.out.println("Lawnmower has already been used!");
		}
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void setHasStarted(boolean b) {
		hasStarted = b;
	}
	
	public boolean getHasStarted() {
		if (hasStarted) {
			return true;
		}
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.x += 7;
		panel.repaint();
		if (this.x >= 1000) {
			timer.stop();
		}
	}
}
