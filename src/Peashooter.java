import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Peashooter extends GameCharacter implements ActionListener {
	private BufferedImage peaShooter;
	private Image finalPeaShooter;
	private int xPos;
	private int yPos;
	private PlantsVsRobotsPanel panel;
	private Timer timer;
	private int pSRow;
	private int pSCol;
	public Peashooter(PlantsVsRobotsPanel panel, int x, int y, int pSRow, int pSCol) {
		this.pSRow = pSRow;
		this.pSCol = pSCol;
		this.panel = panel;
		this.xPos = x;
		this.yPos = y;
		try {
			peaShooter = ImageIO.read(new File("Peashooter.png"));
		}
		catch(IOException e) {
			System.out.println("Could not load 'Peashooter.png'!");
		}
		finalPeaShooter = peaShooter.getScaledInstance(50, 65, Image.SCALE_DEFAULT);
		this.setHealth(35);
		timer = new Timer(1500, this);
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void draw(Graphics g) {
		g.drawImage(finalPeaShooter, xPos, yPos, null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		panel.addPeas(new Pea(panel, xPos + 15, yPos + 5));
		panel.repaint();
	}
	
	public boolean isColliding(int x, int y) {
		if (x >= this.xPos && x <= this.xPos + 50 && y >= this.yPos + 10 && y <= this.yPos + 80) {
			return true;
		}
		return false;
	}
	
	public int getPSRow() {
		return this.pSRow;
	}
	public int getPSCol() {
		return this.pSCol;
	}
}
