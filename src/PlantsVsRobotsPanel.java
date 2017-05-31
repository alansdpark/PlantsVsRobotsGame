import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlantsVsRobotsPanel extends JPanel {
	// Sprites
	private BufferedImage sunIcon;
	private Image finalSunIcon;
	
	private BufferedImage shovelIcon;
	private Image finalShovelIcon;
	
	// Peashooter
	private BufferedImage peaShooterIcon;
	private Image finalPeaShooterIcon;
	
	// Sunflower
	private BufferedImage sunFlowerIcon;
	private Image finalSunFlowerIcon;
	
	// Walnut
	private BufferedImage walnutIcon;
	private Image finalWalnutIcon;
	
	// JStuff
	// Have label show if focus is currently in window
	private JLabel waveLabel = new JLabel("CURRENT WAVE");
	private JLabel sunAmount = new JLabel("Suns: 0");
	private JLabel currentWave = new JLabel("Wave: 0");
	private JLabel sunflowerPrice = new JLabel("50");
	private JLabel peashooterPrice = new JLabel("100");
	private JLabel walnutPrice = new JLabel("50");
	
	private JLabel gameOverLabel = new JLabel("Waves completed: ");
	
	// Colors
	private Color backgroundColor = new Color(60, 179, 0);
	private Color barColor = new Color(232, 172, 80);
	private Color plantDividerColor = new Color(133, 99, 0);
	private Color sideWalkColor = new Color(129, 124, 124);
	private Color lawnMowerSidewalkColor = new Color(192, 192, 192);
	private Color whichColor;
	private Color noAffordColor = new Color(242, 77, 77);
	private Color yesAffordColor = new Color(0, 204, 204);
	private Color selectedPlantColor = new Color(0, 255, 0);
	
	// Testing
	private Wave wave = new Wave(this);
	private ArrayList<Pea> peas = new ArrayList<Pea>();
	private ArrayList<Peashooter> peaShooters = new ArrayList<Peashooter>();
	private ArrayList<Sun> suns = new ArrayList<Sun>();
	private int waveInt = 0;
	private ArrayList<Sun> sunsBySunflowers = new ArrayList<Sun>();
	private Affordable[] affordableSquares = new Affordable[9];
	private Plant[] selectPlants = new Plant[9];
	private ArrayList<Sunflower> sunflowersArrayList = new ArrayList<Sunflower>();
	private ArrayList<Walnut> walnuts = new ArrayList<Walnut>();
	private int eightyAdd;
	private int price;
	private int thisI;
	private PlantableGrid plantableGrid = new PlantableGrid();
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	private InstantiateRobots instantiateRobots = new InstantiateRobots(this);
	private boolean gameOver;
	private Lawnmower[] lawnmowers = new Lawnmower[] {
			new Lawnmower(this, 0, 120),
			new Lawnmower(this, 0, 220),
			new Lawnmower(this, 0, 320),
			new Lawnmower(this, 0, 420),
			new Lawnmower(this, 0, 520)
	};
	
	public PlantsVsRobotsPanel() {
		gameOver = false;
		this.setBackground(backgroundColor);
		this.setLayout(null);
		sunAmount.setBounds(5, 40, 100, 100);
		currentWave.setBounds(5, 22, 100, 100);
		sunflowerPrice.setBounds(110, 40, 100, 100);
		peashooterPrice.setBounds(185, 40, 100, 100); // xPos + 80, keep yPos at 40
		waveLabel.setBounds(350, 590, 100, 100);
		walnutPrice.setBounds(270, 40, 100, 100);
		gameOverLabel.setBounds(400, 300, 600, 400);
		this.add(waveLabel);
		this.add(sunAmount);
		this.add(currentWave);
		this.add(sunflowerPrice);
		this.add(peashooterPrice);
		this.add(walnutPrice);
		whichColor = noAffordColor;
		eightyAdd = 0;
		for (int i = 0; i < affordableSquares.length; i++) {
			thisI = i;
			switch (thisI) { // sunflower(0) = 100, peashooter (1) = 200,
				case 0: 
					price = 50;
					break;
				
				case 1:
					price = 100;
					break;
				case 2:
					price = 50;
					break;
				case 8:
					price = 0;
					break;
					
				default:
					price = 800;
					System.out.println("Could not find a price so defaulted to 800 suns");
			}
			eightyAdd += 80;
			affordableSquares[i] = new Affordable(whichColor, eightyAdd, 80, price);
		}
		for (int i = 0; i < selectPlants.length; i++) {
			int selectPlantsXPos;
			switch (i) {
				case 0:
					selectPlantsXPos = 80;
					break;
				case 1:
					selectPlantsXPos = 160;
					break;
				case 2:
					selectPlantsXPos = 240;
					break;
				case 3:
					selectPlantsXPos = 320;
					break;
				case 4:
					selectPlantsXPos = 400;
					break;
				case 5:
					selectPlantsXPos = 480;
					break;
				case 6:
					selectPlantsXPos = 560;
					break;
				case 7:
					selectPlantsXPos = 640;
					break;
				case 8:
					selectPlantsXPos = 720;
					break;
				default:
					selectPlantsXPos = 1000;
					System.out.println("Could not find selectPlantsXPos for this plant. Setting default to 1000.");
			}
			this.selectPlants[i] = new Plant(selectPlantsXPos, i);
		}
		try {
			sunIcon = ImageIO.read(new File("Sun.png"));
			shovelIcon = ImageIO.read(new File("Shovel.png"));
			peaShooterIcon = ImageIO.read(new File("Peashooter.png"));
			sunFlowerIcon = ImageIO.read(new File("Sunflower.png"));
			walnutIcon = ImageIO.read(new File("WalnutV2.png"));
		}
		catch (IOException e) {
			System.out.println("Could not load a sprite! PlantsVsRobotsPanel class.");
		}
		finalSunIcon = sunIcon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		finalShovelIcon = shovelIcon.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
		finalPeaShooterIcon = peaShooterIcon.getScaledInstance(50, 65, Image.SCALE_DEFAULT);
		finalSunFlowerIcon = sunFlowerIcon.getScaledInstance(60, 75, Image.SCALE_DEFAULT);
		finalWalnutIcon = walnutIcon.getScaledInstance(60, 72, Image.SCALE_DEFAULT);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(barColor);
		g.fillRect(0, 0, 800, 100);
		
		for (int i = 0; i < this.affordableSquares.length; i++) {
			this.affordableSquares[i].draw(g);
		}
		
		g.setColor(sideWalkColor);
		g.fillRect(700, 100, 50, 500);
		g.setColor(lawnMowerSidewalkColor);
		g.fillRect(0, 100, 50, 500);
		
		for (int i = 0; i < this.plantableGrid.getGameGrid().length; i++) {
			for (int j = 0; j < this.plantableGrid.getGameGrid()[0].length; j++) {
				this.plantableGrid.getGameGrid()[i][j].draw(g);
			}
		}
		
		// Plants Divider
		g.setColor(plantDividerColor);
		for (int i = 0; i < 720; i += 80) {
			g.drawLine(80 + i, 0, 80 + i, 100);
		}
		
		// Icons
		g.drawImage(finalSunIcon, 10, 10, null);
		g.drawImage(finalShovelIcon, 725, 3, null);
		g.drawImage(finalSunFlowerIcon, 90, 5, null); // 95, 5
		g.drawImage(finalPeaShooterIcon, 175, 5, null); // 170, 5
		g.drawImage(finalWalnutIcon, 250, 5, null);
		
		for (int i = 0; i < this.getSunflowerArrayList().size(); i++) {
			this.getSunflowerArrayList().get(i).draw(g);
		}
		
		for (int i = 0; i < this.getPeaShooters().size(); i++) {
			this.getPeaShooters().get(i).draw(g);
		}
		
		for (int i = 0; i < this.getWalnuts().size(); i++) {
			this.getWalnuts().get(i).draw(g);
		}
		
		for (int i = 0; i < this.getRobots().size(); i++) {
			this.getRobots().get(i).draw(g);
		}
		
		for (int i = 0; i < this.lawnmowers.length; i++) {
			this.lawnmowers[i].draw(g);
		}
		
		// PROJECTILES AND SUNS ALWAYS ON BOTTOM
		for (int i = 0; i < peas.size(); i++) {
			peas.get(i).draw(g);
		}
		
		for (Sun s : suns) {
			s.draw(g);
		}
		for (Sun s : sunsBySunflowers) {
			s.draw(g);
		}
		g.setColor(Color.WHITE);
		g.fillRect(0, 600, 800, 78);
		wave.draw(g);
		if (gameOver) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 700);
			gameOverLabel.setForeground(Color.WHITE);
			this.add(gameOverLabel);
		}
	}
	
	public void setWavesCompletedLabel(int s) {
		gameOverLabel.setText("Waves completed: " + s);
	}
	
	public void setGameOver(boolean b) {
		gameOver = b;
	}
	
	public ArrayList<Walnut> getWalnuts() {
		return walnuts;
	}
	
	public void addWalnut(Walnut w) {
		walnuts.add(w);
	}
	
	public ArrayList<Robot> getRobots() {
		return robots;
	}
	public void addRobots(Robot r) {
		robots.add(r);
	}
	
	public PlantableGrid getPlantableGrid() {
		return this.plantableGrid;
	}
	
	public Affordable[] getAffordableSquares() {
		return this.affordableSquares;
	}
	
	public Color getColorsAfford(int w) {
		// 0 is noAfford, 1 is yesAfford
		if (w == 2) {
			return this.selectedPlantColor;
		}
		else if (w == 0) {
			return noAffordColor;
		} else {
			return yesAffordColor;
		}
	}
	public void addPeas(Pea p) {
		this.peas.add(p);
	}
	public ArrayList<Pea> getPeas() {
		return peas;
	}
	
	public void changeColor(Color c) {
		whichColor = c;
	}
	
	public JLabel getSunsLabel() {
		return sunAmount;
	}
	
	public void setSunAmount(int s) {
		this.sunAmount.setText("Suns: " + s);
	}
	
	public void setWave(int w) {
		this.currentWave.setText("Wave: " + w);
	}
	
	public int getWave() {
		return waveInt;
	}
	
	public void removeSunFromList(int index) {
		suns.remove(index);
	}
	
	public void addSunToList(Sun s) {
		suns.add(s);
	}
	public ArrayList<Sun> getListOfSuns() {
		return suns;
	}
	
	public ArrayList<Sun> getListOfSunsSunflowers() {
		return sunsBySunflowers;
	}
	public void addSunOfSunflowers(Sun s) {
		this.sunsBySunflowers.add(s);
	}
	
	public void removeSunFromSunflowerList(int index) {
		this.sunsBySunflowers.remove(index);
	}
	
	public Plant[] getPlantsArray() {
		return this.selectPlants;
	}
	public ArrayList<Sunflower> getSunflowerArrayList() {
		return this.sunflowersArrayList;
	}
	public void addSunflowerToArrayList(Sunflower s) {
		this.sunflowersArrayList.add(s);
	}
	
	public Wave getWaveClass() {
		return this.wave;
	}
	public void addPeashooters(Peashooter p) {
		this.peaShooters.add(p);
	}
	public ArrayList<Peashooter> getPeaShooters() {
		return this.peaShooters;
	}
	public Lawnmower[] getLawnmowers() {
		return this.lawnmowers;
	}
	public InstantiateRobots getInstantiateRobots() {
		return this.instantiateRobots;
	}
}
