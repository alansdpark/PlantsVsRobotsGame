import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class PlantsVsRobotsListener implements ActionListener, MouseListener, KeyListener {
	
	private PlantsVsRobotsPanel panel;
	private int fallingSpeed = 2;
	private int upSpeed = 3;
	private int currentAmountOfSuns;
	private boolean alreadySelected = false;
	private CounterTimer counterTimer = new CounterTimer();
	private int currentWave;
	private int walkSpeed;
	
	public PlantsVsRobotsListener(PlantsVsRobotsPanel panel) {
		currentWave = 0;
		walkSpeed = 1;
		this.currentAmountOfSuns = 0;
		panel.addMouseListener(this);
		panel.addKeyListener(this);
		this.panel = panel;
		Timer timer = new Timer(30, this);
		timer.start();
		counterTimer.startTimer();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (counterTimer.getCount() >= 20) {
			currentWave += 1;
			if (currentWave > 2) {
				panel.getWaveClass().setDiff(2);
				panel.getInstantiateRobots().setDiff(2);
				panel.getInstantiateRobots().setTimerDelay(2);
			}
			if (currentWave > 4) {
				panel.getWaveClass().setDiff(1);
				panel.getInstantiateRobots().setDiff(3);
				panel.getInstantiateRobots().setTimerDelay(3);
			}
			panel.setWave(currentWave);
			panel.addRobots(new Robot(panel, 800, panel.getInstantiateRobots().getRandomRow(), true, walkSpeed));
			panel.getInstantiateRobots().startTimer();
			panel.getWaveClass().startWave();
			panel.repaint();
			counterTimer.stopTimer();
			counterTimer.resetCount();	
		}
		if (panel.getWaveClass().getXWidth() <= 0) { // If wave is over...
			panel.setWavesCompletedLabel(currentWave - 1);
			panel.getInstantiateRobots().stopTimer();
			panel.getWaveClass().stopWave();
			panel.getWaveClass().setColor(Color.ORANGE);
			panel.getWaveClass().resetXWidth();
			counterTimer.startTimer();
		}
		
		for (int i = 0; i < panel.getListOfSuns().size(); i++) {
			if (panel.getListOfSuns().get(i).getDroppingY() < 555) {
				panel.getListOfSuns().get(i).setDroppingY(panel.getListOfSuns().get(i).getDroppingY() + fallingSpeed);
			}
			if (panel.getListOfSuns().get(i).getDroppingY() >= 555) {
				if (panel.getListOfSuns().get(i).getCountTimeAtEnd() >= 50) {
					System.out.println("Did not catch sun in time...");
					panel.removeSunFromList(i);
				} else {
					panel.getListOfSuns().get(i).setCountTimeAtEnd(panel.getListOfSuns().get(i).getCountTimeAtEnd() + 1);
				}
			}
			panel.repaint();
		}
		for (int i = 0; i < panel.getListOfSunsSunflowers().size(); i++) {
			if(panel.getListOfSunsSunflowers().get(i).getDone() == true) {
				if (panel.getListOfSunsSunflowers().get(i).getDroppingY() < 555) {
					panel.getListOfSunsSunflowers().get(i).setDroppingY(panel.getListOfSunsSunflowers().get(i).getDroppingY() + fallingSpeed);
					panel.repaint();
				}
				if (panel.getListOfSunsSunflowers().get(i).getDroppingY() >= 555) {
					if (panel.getListOfSunsSunflowers().get(i).getCountTimeAtEnd() >= 50) {
						System.out.println("Did not catch sun in time...");
						panel.removeSunFromSunflowerList(i);
					} else {
						panel.getListOfSunsSunflowers().get(i).setCountTimeAtEnd(panel.getListOfSunsSunflowers().get(i).getCountTimeAtEnd() + 1);
					}
				}
			} else {
				if (panel.getListOfSunsSunflowers().get(i).getDroppingY() >= panel.getListOfSunsSunflowers().get(i).getInitialY() - 20) {
					panel.getListOfSunsSunflowers().get(i).setDroppingY(panel.getListOfSunsSunflowers().get(i).getDroppingY() - upSpeed);
					panel.repaint();
				} else {
					// done = true
					panel.getListOfSunsSunflowers().get(i).setDone(true);
				}
			}
			
		}
		for (int i = 0; i < panel.getPeas().size(); i++) {
			if (panel.getPeas().get(i).getX() + 20 >= 700) {
				panel.getPeas().get(i).stopTimer();
				panel.getPeas().remove(i);
			}
		}
		
		// TODO - Need to change back to red if can't afford
		for (int i = 0; i < panel.getAffordableSquares().length; i++) {
			if (panel.getAffordableSquares()[i].getColor() != panel.getColorsAfford(2)) {
				if (panel.getAffordableSquares()[i].getPrice() <= this.currentAmountOfSuns) {
					panel.getAffordableSquares()[i].changeColor(panel.getColorsAfford(1));
					panel.repaint();
				}
				else {
					panel.getAffordableSquares()[i].changeColor(panel.getColorsAfford(0));
					panel.repaint();
				}
			}
		}
		
		for (int i = 0; i < panel.getRobots().size(); i++) {
			panel.getRobots().get(i).startTimer();
			if (panel.getRobots().get(i).getX() <= -50) {
				panel.setGameOver(true);
				panel.repaint();
				System.out.println("Game over! You lose!");
			}
			if (panel.getRobots().get(i).getX() <= 50) {
				int currRow = panel.getRobots().get(i).getRow();
				panel.getLawnmowers()[currRow].startTimer();
				panel.getLawnmowers()[currRow].setHasStarted(true);
			}
			for (int j = 0; j < panel.getLawnmowers().length; j++) {
				int xLawn = panel.getLawnmowers()[j].getX() + 27;
				if (panel.getRobots().get(i).isColliding(xLawn, panel.getLawnmowers()[j].getY() + 30)) {
					panel.getRobots().get(i).reduceHealth(100);
					System.out.println("Ran over robot with lawnmower!");
				}
			}
			for (int j = 0; j < panel.getPeas().size(); j++) {
				int xPea = panel.getPeas().get(j).getX() + 20;
				int yPea = panel.getPeas().get(j).getY() + 10;
				if (panel.getRobots().get(i).isColliding(xPea, yPea)) {
					panel.getPeas().get(j).stopTimer();
					panel.getPeas().remove(j);
					System.out.println("Removed Pea!");
					panel.getRobots().get(i).reduceHealth(1);
				}
			}
		}
		// CHECKING HEALTH OF ALL GAMECHARACTERS
		int posToDeleteRow = -1;
		int posToDeleteCol = -1;
		for (int i = 0; i < panel.getRobots().size(); i++) {
			if (panel.getRobots().get(i).getHealth() <= 0) {
				panel.getRobots().get(i).stopTimer();
				panel.getRobots().remove(i);
				System.out.println("Killed robot!");
			}
		}
		for (int i = 0; i < panel.getSunflowerArrayList().size(); i++) {
			if (panel.getSunflowerArrayList().get(i).getHealth() <= 0) {
				posToDeleteRow = panel.getSunflowerArrayList().get(i).getPSRow();
				posToDeleteCol = panel.getSunflowerArrayList().get(i).getPSCol();
				panel.getSunflowerArrayList().get(i).stopTimer();
				panel.getSunflowerArrayList().remove(i);
				System.out.println("Sunflower was eaten!");
				panel.getPlantableGrid().getGameGrid()[posToDeleteRow][posToDeleteCol].deletePlantInSpot();
				posToDeleteRow = -1;
				posToDeleteCol = -1;
			}
		}
		for (int i = 0; i < panel.getPeaShooters().size(); i++) {
			if (panel.getPeaShooters().get(i).getHealth() <= 0) {
				posToDeleteRow = panel.getPeaShooters().get(i).getPSRow();
				posToDeleteCol = panel.getPeaShooters().get(i).getPSCol();
				panel.getPeaShooters().get(i).stopTimer();
				panel.getPeaShooters().remove(i);
				System.out.println("Peashooter was eaten!");
				panel.getPlantableGrid().getGameGrid()[posToDeleteRow][posToDeleteCol].deletePlantInSpot();
				posToDeleteRow = -1;
				posToDeleteCol = -1;
			}
		}
		for (int i = 0; i < panel.getWalnuts().size(); i++) {
			if (panel.getWalnuts().get(i).getHealth() <= 0) {
				posToDeleteRow = panel.getWalnuts().get(i).getPSRow();
				posToDeleteCol = panel.getWalnuts().get(i).getPSCol();
				panel.getWalnuts().remove(i);
				System.out.println("Walnut was eaten!");
				panel.getPlantableGrid().getGameGrid()[posToDeleteRow][posToDeleteCol].deletePlantInSpot();
				posToDeleteRow = -1;
				posToDeleteCol = -1;
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int xPos = e.getX();
		int yPos = e.getY();
		for (int i = 0; i < panel.getListOfSuns().size(); i++) {
			if (panel.getListOfSuns().get(i).isColliding(xPos, yPos)) {
				panel.removeSunFromList(i);
				panel.repaint();
				this.currentAmountOfSuns += 25;
				panel.setSunAmount(this.currentAmountOfSuns);
			}
		}
		for (int i = 0; i < panel.getListOfSunsSunflowers().size(); i++) {
			if (panel.getListOfSunsSunflowers().get(i).isColliding(xPos, yPos)) {
				panel.removeSunFromSunflowerList(i);
				panel.repaint();
				this.currentAmountOfSuns += 25;
				panel.setSunAmount(this.currentAmountOfSuns);
			}
		}
		// TODO - Change selected plant and unselect
		for (int i = 0; i < panel.getPlantsArray().length; i++) {
			if (panel.getPlantsArray()[i].selected(xPos, yPos)) {
				if (panel.getAffordableSquares()[i].getColor() == panel.getColorsAfford(1)) {
					if (alreadySelected == false) {
						panel.getAffordableSquares()[i].changeColor(panel.getColorsAfford(2));
						panel.repaint();
						System.out.println("Name of selected plant is: " + panel.getPlantsArray()[i].getName());
						alreadySelected = true;
					}
					else {
						System.out.println("Already selected a different plant! ");
					}
				}
				else if (panel.getAffordableSquares()[i].getColor() == panel.getColorsAfford(2)) {
					panel.getAffordableSquares()[i].changeColor(panel.getColorsAfford(1));
					panel.repaint();
					System.out.println("Unselected plant!");
					alreadySelected = false;
				}
				else {
					alreadySelected = false;
					System.out.println("Cannot afford plant!");
				}
			}
		}
		// Get x and y position and create object based on selectedPlant at that location
		boolean canPlant = false;
		int iPos = 0;
		int jPos = 0;
		int xPosToDraw = 0;
		int yPosToDraw = 0;
		if (xPos > 50 && xPos < 700 && yPos > 100) { // Cannot place plant outside of lawn
			for (int i = 0; i < panel.getPlantableGrid().getGameGrid().length; i++) {
				for (int j = 0; j < panel.getPlantableGrid().getGameGrid()[0].length; j++) {
					if (panel.getPlantableGrid().getGameGrid()[i][j].isColliding(xPos, yPos)) {
						iPos = i;
						jPos = j;
						System.out.println("Clicked on plantable spot");
						if (panel.getPlantableGrid().getGameGrid()[i][j].isOccupied()) {
							// if selected plant is a shovel, then you can delete plant here.
							if (panel.getAffordableSquares()[8].getColor() == panel.getColorsAfford(2)) {
								panel.getPlantableGrid().getGameGrid()[iPos][jPos].getChar().reduceHealth(100);
								panel.getPlantableGrid().getGameGrid()[iPos][jPos].deletePlantInSpot();
								panel.getAffordableSquares()[8].changeColor(panel.getColorsAfford(1));
								panel.repaint();
								alreadySelected = false;
								canPlant = false;
							}
							else {
								System.out.println("Cannot plant here! Already occupied by another plant!");
							}
						}
						else {
							canPlant = true;
						}
					}
				}
			}
			if (canPlant) {
				xPosToDraw = panel.getPlantableGrid().getGameGrid()[iPos][jPos].getPosition()[0]; 
				yPosToDraw = panel.getPlantableGrid().getGameGrid()[iPos][jPos].getPosition()[2];
				if (panel.getAffordableSquares()[0].getColor() == panel.getColorsAfford(2)) {
					panel.addSunflowerToArrayList(new Sunflower(panel, xPosToDraw, yPosToDraw, iPos, jPos));
					panel.repaint();
					panel.getAffordableSquares()[0].changeColor(panel.getColorsAfford(1));
					this.currentAmountOfSuns -= 50;
					panel.setSunAmount(currentAmountOfSuns);
					alreadySelected = false;
					panel.getPlantableGrid().getGameGrid()[iPos][jPos].occupySpot(panel.getSunflowerArrayList().get(panel.getSunflowerArrayList().size() - 1));
				}
				if (panel.getAffordableSquares()[1].getColor() == panel.getColorsAfford(2)) {
					panel.addPeashooters(new Peashooter(panel, xPosToDraw + 2, yPosToDraw + 2, iPos, jPos));
					panel.repaint();
					panel.getAffordableSquares()[1].changeColor(panel.getColorsAfford(1));
					this.currentAmountOfSuns -= 100;
					panel.setSunAmount(currentAmountOfSuns);
					alreadySelected = false;
					panel.getPlantableGrid().getGameGrid()[iPos][jPos].occupySpot(panel.getPeaShooters().get(panel.getPeaShooters().size() - 1));
				}
				if (panel.getAffordableSquares()[2].getColor() == panel.getColorsAfford(2)) {
					panel.addWalnut(new Walnut(panel, xPosToDraw, yPosToDraw, iPos, jPos));
					panel.repaint();
					panel.getAffordableSquares()[2].changeColor(panel.getColorsAfford(1));
					this.currentAmountOfSuns -= 50;
					panel.setSunAmount(currentAmountOfSuns);
					alreadySelected = false;
					panel.getPlantableGrid().getGameGrid()[iPos][jPos].occupySpot(panel.getWalnuts().get(panel.getWalnuts().size() - 1));
				}
				canPlant = false;
			}
			
		}
		else {
			System.out.println("Cannot place plant here!");
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		panel.requestFocusInWindow();
		System.out.println("Has Focus in Window Now");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Key Number: " + e.getKeyCode()); // b == 66
		if (e.getKeyCode() == 66) {
			this.currentAmountOfSuns = 900;
			panel.setSunAmount(currentAmountOfSuns);
			panel.repaint();
		}
		if (e.getKeyCode() == 78) {
			panel.getWaveClass().setXWidth(15);
			panel.repaint();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}