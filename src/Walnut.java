import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Walnut extends GameCharacter {
	private PlantsVsRobotsPanel panel;
	private int x;
	private int y;
	private BufferedImage walnutImage;
	private Image finalWalnutImage;
	private int pSRow;
	private int pSCol;
	public Walnut(PlantsVsRobotsPanel panel, int x, int y, int pSRow, int pSCol) {
		this.pSRow = pSRow;
		this.pSCol = pSCol;
		this.panel = panel;
		this.x = x;
		this.y = y;
		this.setHealth(75);
		try {
			walnutImage = ImageIO.read(new File("WalnutV2.png"));
		}
		catch(IOException e) {
			System.out.println("Could not load Walnut.png!");
		}
		finalWalnutImage = walnutImage.getScaledInstance(60, 72, Image.SCALE_DEFAULT);
	}
	public void draw(Graphics g) {
		g.drawImage(finalWalnutImage, x, y, null);
	}
	
	public boolean isColliding(int x, int y) {
		if (x >= this.x && x <= this.x + 60 && y >= this.y && y <= this.y + 72) {
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
