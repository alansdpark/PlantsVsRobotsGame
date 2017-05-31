import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PlantableSpot {
	private int x;
	private int y;
	private boolean occupied;
	private BufferedImage grass01;
	private BufferedImage grass02;
	private Image finalgrass01;
	private Image finalgrass02;
	private Image thisGrass;
	private int firstGrass;
	private int graphicsX;
	private int graphicsY;
	private GameCharacter occupyingChar;
	public PlantableSpot(int x, int y) {
		occupyingChar = null;
		this.x = x;
		this.y = y;
		occupied = false;
		try {
			grass01 = ImageIO.read(new File("Grass01Updated2.png"));
			grass02 = ImageIO.read(new File("Grass02Updated2.png"));
		}
		catch(IOException e) {
			System.out.println("Could not load grass01 and grass02.png!");
		}
		finalgrass01 = grass01.getScaledInstance(80, 100, Image.SCALE_DEFAULT);
		finalgrass02 = grass02.getScaledInstance(80, 100, Image.SCALE_DEFAULT);
		
		// Add 100 to Y, and 80 to X. Initial X = 50, Initial Y = 100.
		switch (x) {
			case 0:
				graphicsY = 100;
				break;
			case 1:
				graphicsY = 200;
				break;
			case 2:
				graphicsY = 300;
				break;
			case 3:
				graphicsY = 400;
				break;
			case 4:
				graphicsY = 500;
				break;
			default:
				System.out.println("Should not be here, graphicsY default set to 0.");
				graphicsY = 0;
		}
		switch (y) {
			case 0:
				graphicsX = 50;
				thisGrass = finalgrass01;
				break;
			case 1:
				graphicsX = 130;
				thisGrass = finalgrass02;
				break;
			case 2:
				graphicsX = 210;
				thisGrass = finalgrass01;
				break;
			case 3:
				graphicsX = 290;
				thisGrass = finalgrass02;
				break;
			case 4:
				graphicsX = 370;
				thisGrass = finalgrass01;
				break;
			case 5:
				graphicsX = 450;
				thisGrass = finalgrass02;
				break;
			case 6:
				graphicsX = 530;
				thisGrass = finalgrass01;
				break;
			case 7:
				graphicsX = 610;
				thisGrass = finalgrass02;
				break;
			default:
				System.out.println("Should not be here, graphicsX default set to 0.");
				graphicsX = 0;
				thisGrass = finalgrass01;
		}
	}
	public void draw(Graphics g) {
		g.drawImage(thisGrass, graphicsX, graphicsY, null); // draw at position.
	}
	public void occupySpot(GameCharacter c) {
		this.occupyingChar = c;
		occupied = true;
	}
	public boolean isOccupied() {
		if (occupied) {
			return true;
		}
		return false;
	}
	
	public GameCharacter getChar() {
		return this.occupyingChar;
	}
	public void deletePlantInSpot() { // If user uses shovel tool, or plant is eaten
		System.out.println("Deleted occupying plant!");
		occupied = false;
	}
	public boolean isColliding(int x, int y) {
		if (x >= graphicsX + 10 && x <= graphicsX + 70 && y >= graphicsY + 10 && y <= graphicsY + 90) {
			return true;
		}
		return false;
	}
	
	public int[] getPosition() { // 0 = leftX 1 = rightX 2 = topY 3 = bottomY
		int[] xAndY = new int[4];
		xAndY[0] = graphicsX + 10;
		xAndY[1] = graphicsX + 70;
		xAndY[2] = graphicsY + 10;
		xAndY[3] = graphicsY + 90;
		return xAndY;
	}
}
