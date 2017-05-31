import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Wave implements ActionListener {
	private Color currentWaveColor;
	private Color done = new Color(255, 51, 51);
	private Color almostDone = new Color(255, 255, 51);
	private Color normal = new Color(51, 255, 51);
	private Timer waveTimer;
	private int xWidth;
	private PlantsVsRobotsPanel panel;
	private int difficulty;
	public Wave(PlantsVsRobotsPanel panel) {
		difficulty = 3;
		this.panel = panel;
		currentWaveColor = normal;
		xWidth = 800;
		waveTimer = new Timer(500, this);
	}
	public int getDiff() {
		return difficulty;
	}
	public void setDiff(int s) {
		difficulty = s;
	}
	public void draw(Graphics g) {
		g.setColor(currentWaveColor);
		g.fillRect(0, 600, xWidth, 78);
		g.setColor(Color.black);
		g.drawRect(0, 600, 800, 78);
	}
	public void setColor(Color c) {
		currentWaveColor = c;
	}
	public void startWave() { // Change difficulty level from 1-10 waves, adding more and stronger zombies etc. 
		waveTimer.start();
	}
	public void stopWave() {
		waveTimer.stop();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (xWidth > 0) {
			xWidth -= difficulty;
			if (xWidth <= 150) {
				setColor(done);
			}
			else if (xWidth <= 350) {
				setColor(almostDone);
			}
			else {
				setColor(normal);
			}
			panel.repaint();
		}
		else {
			this.stopWave();
		}
	}
	public int getXWidth() {
		return this.xWidth;
	}
	public void resetXWidth() {
		xWidth = 800;
	}
	public void setXWidth(int s) {
		xWidth = s;
	}
}
