import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Sunflower extends GameCharacter implements ActionListener {
	
	private PlantsVsRobotsPanel panel;
	private BufferedImage sunflower;
	private Image finalSunflower;
	private BufferedImage sunflowerSun;
	private Image finalSunFlowerSun;
	private Image spriteToDraw;
	private int xPos;
	private int yPos;
	private Timer timer;
	private int pSRow;
	private int pSCol;
	
	public Sunflower(PlantsVsRobotsPanel panel, int x, int y, int pSRow, int pSCol) {
		this.pSRow = pSRow;
		this.pSCol = pSCol;
		this.panel = panel;
		xPos = x;
		yPos = y;
		try{
			sunflower = ImageIO.read(new File("Sunflower.png"));
			sunflowerSun = ImageIO.read(new File("Sunflower About to Drop Sun.png"));
		}
		catch (IOException e) {
			System.out.println("Sunflower class: Could not load sunflower.png or abouttodropsun.png");
		}
		finalSunflower = sunflower.getScaledInstance(60, 75, Image.SCALE_DEFAULT);
		finalSunFlowerSun = sunflowerSun.getScaledInstance(60, 75, Image.SCALE_DEFAULT);
		spriteToDraw = finalSunflower;
		this.setHealth(20);
		timer = new Timer(9000, this);
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	
	public void spawnSun() {
		panel.addSunOfSunflowers(new Sun(panel, this.xPos, this.yPos));
	}
	
	public void draw(Graphics g) {
		g.drawImage(spriteToDraw, xPos, yPos, null);
	}
	private void setThisImage(Image i) {
		spriteToDraw = i;
	}
	
	public boolean isColliding(int x, int y) {
		if (x >= xPos && x <= xPos + 60 && y >= yPos && y <= yPos + 75) {
			return true;
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setThisImage(finalSunFlowerSun);
		spawnSun();
		panel.repaint();
	}
	
	public int getPSRow() {
		return this.pSRow;
	}
	
	public int getPSCol() {
		return this.pSCol;
	}
}
