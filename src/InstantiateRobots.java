import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class InstantiateRobots implements ActionListener {
	private PlantsVsRobotsPanel panel;
	private Timer timer;
	private int randomRow;
	private boolean notFirst;
	private int diff;
	public InstantiateRobots(PlantsVsRobotsPanel panel) {
		diff = 1;
		notFirst = true;
		this.panel = panel;
		timer = new Timer(10000, this);
	}
	public void startTimer() {
		notFirst = true;
		timer.start();
	}
	public void stopTimer() {
		notFirst = true;
		timer.stop();
	}
	
	public int getRandomRow() {
		return (int)(Math.random() * 5);
	}
	
	public int getDiff() {
		return diff;
	}
	
	public void setDiff(int s) {
		diff = s;
	}
	
	public void setTimerDelay(int d) {
		if (d == 1) {
			timer.setDelay(10000);
		}
		if (d == 2) {
			timer.setDelay(5000);
		}
		if (d == 3) {
			timer.setDelay(2000);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		randomRow = (int)(Math.random() * 5);
		System.out.println("Instantiated Robot!");
		if (notFirst) {
			System.out.println("created first flag zombie");
			notFirst = false;
		} else {
			panel.addRobots(new Robot(panel, 800, randomRow, false, diff));
		}
	}
}
