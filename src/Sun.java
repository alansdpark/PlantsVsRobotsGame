import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sun {
	private BufferedImage sunSprite;
	private Image newSprite;
	private int droppingY;
	private int xPos;
	private int initialY;
	private boolean doneGoingUp;
	private int countTimeAtEnd;
	private PlantsVsRobotsPanel panel;
	
	public Sun(PlantsVsRobotsPanel panel, int xPosition, int yPos) {
		this.panel = panel;
		this.countTimeAtEnd = 0;
		this.doneGoingUp = false;
		droppingY = yPos;
		initialY = yPos;
		xPos = xPosition;
		try {
			sunSprite = ImageIO.read(new File("Sun.png"));
		}
		catch(IOException e) {
			System.out.println("Could not load SunSprite01.png");
		}
		newSprite = sunSprite.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
	}
	
	public void draw(Graphics g) {
		g.drawImage(newSprite, xPos, droppingY, null);
	}
	
	public boolean isColliding(int x, int y) {
		if (x >= xPos && x <= xPos + 50 && y >= droppingY && y <= droppingY + 50) {
			return true;
		}
		return false;
	}
	
	// GETTERS AND SETTERS
	
	public int getInitialY() {
		return this.initialY;
	}
	public boolean getDone() {
		return this.doneGoingUp;
	}
	
	public void setDone(boolean d) {
		this.doneGoingUp = d;
	}
	public void setDroppingY(int s) {
		droppingY = s;
	}
	
	public int getDroppingY() {
		return droppingY;
	}
	
	public int getCountTimeAtEnd() {
		return this.countTimeAtEnd;
	}
	
	public void setCountTimeAtEnd(int s) {
		this.countTimeAtEnd = s;
	}
}
