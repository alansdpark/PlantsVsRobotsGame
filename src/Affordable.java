import java.awt.Color;
import java.awt.Graphics;

public class Affordable {
	private int price;
	private Color which;
	private int xPos;
	private int yPos;
	public Affordable(Color c, int x, int y, int p) {
		which = c;
		xPos = x;
		yPos = y;
		price = p;
	}
	public void draw(Graphics g) {
		g.setColor(which);
		g.fillRect(xPos, yPos, 80, 20);
	}
	public void changeColor(Color c) {
		this.which = c;
	}
	public Color getColor() {
		return which;
	}
	public int getPrice() {
		return price;
	}
}
