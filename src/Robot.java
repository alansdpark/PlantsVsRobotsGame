import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Robot extends GameCharacter implements ActionListener {
	// Needs to constantly change x position to left
	// Needs to do damage (can use interface).
	private BufferedImage robotPng;
	private PlantsVsRobotsPanel panel;
	private Image finalrobotPng;
	private BufferedImage flagRobotPng;
	private Image finalflagrobotPng;
	private int x;
	private int row;
	private boolean isFlagRobot;
	private Timer timer;
	private int y;
	private boolean isEating;
	private GameCharacter gameC;
	private int walkSpeed;
	
	public Robot(PlantsVsRobotsPanel panel, int x, int row, boolean isFlag, int walkSpeed) {
		this.walkSpeed = walkSpeed;
		this.panel = panel;
		isFlagRobot = isFlag;
		this.x = x;
		this.row = row;
		switch (row) {
			case 0:
				y = 100;
				break;
			case 1:
				y = 200;
				break;
			case 2:
				y = 300;
				break;
			case 3:
				y = 400;
				break;
			case 4:
				y = 500;
				break;
			default:
				y = 100;
				System.out.println("Should not be here!");
		}
		try {
			robotPng = ImageIO.read(new File("Robot.png"));
			flagRobotPng = ImageIO.read(new File("Flag Robot.png"));
		}
		catch (IOException e) {
			System.out.println("Could not load Robot.png!");
		}
		finalrobotPng = robotPng.getScaledInstance(50, 80, Image.SCALE_DEFAULT);
		finalflagrobotPng = flagRobotPng.getScaledInstance(60, 100, Image.SCALE_DEFAULT);
		timer = new Timer(100, this);
		isEating = false;
		gameC = null;
	}
	public void draw(Graphics g) {
		if (isFlagRobot) {
			g.drawImage(finalflagrobotPng, x, y, null);
		} else  {
			g.drawImage(finalrobotPng, x, y, null);
		}
	}
	public void eat(GameCharacter c) {
		if (c.getHealth() <= 1) {
			isEating = false;
			gameC = null;
		}
		c.reduceHealth(1);
		System.out.println("Eating...");
	}
	public void walk() {
		this.x -= walkSpeed;
	}
	public void startTimer() {
		timer.start();
	}
	public void stopTimer() {
		timer.stop();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// put this underneath if
		for (int i = 0; i < panel.getSunflowerArrayList().size(); i++) {
			if (panel.getSunflowerArrayList().get(i).isColliding(this.x, this.y + 50)) {
				isEating = true;
				gameC = panel.getSunflowerArrayList().get(i);
				eat(gameC);
			}
		}
		for (int i = 0; i < panel.getPeaShooters().size(); i++) {
			if (panel.getPeaShooters().get(i).isColliding(this.x, this.y + 50)) {
				isEating = true;
				gameC = panel.getPeaShooters().get(i);
				eat(gameC);
			}
		}
		for (int i = 0; i < panel.getWalnuts().size(); i++) {
			if (panel.getWalnuts().get(i).isColliding(this.x, this.y + 50)) {
				isEating = true;
				gameC = panel.getWalnuts().get(i);
				eat(gameC);
			}
		}
		if (!isEating) {
			gameC = null;
			walk();
		}
	}
	
	public boolean isColliding(int x, int y) {
		if (x >= this.x && x <= this.x + 50 && y >= this.y && y <= this.y + 100) {
			return true;
		}
		return false;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getX() {
		return this.x;
	}
	public void setWalkSpeed(int s) {
		walkSpeed = s;
	}
	public int getWalkSpeed() {
		return walkSpeed;
	}
}
