import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Pea implements ActionListener {
	private PlantsVsRobotsPanel panel;
	private Timer timer;
	private BufferedImage pea;
	private Image finalPea;
	private int xPos;
	private int yPos;
	
	public Pea(PlantsVsRobotsPanel panel, int x, int y) {
		this.panel = panel;
		try {
			pea = ImageIO.read(new File("Pea.png"));
		}
		catch(IOException e) {
			System.out.println("Could not load Pea.png !");
		}
		finalPea = pea.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		xPos = x;
		yPos = y;
		timer = new Timer(30, this);
		timer.start();
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public void draw(Graphics g) {
		g.drawImage(finalPea, xPos, yPos, null);
	}
	
	public void stopTimer() {
		this.timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		xPos += 5;
		panel.repaint();
	}
}
