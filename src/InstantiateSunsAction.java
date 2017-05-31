import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class InstantiateSunsAction implements ActionListener {
	private PlantsVsRobotsPanel panel = new PlantsVsRobotsPanel();
	private Timer timer;
	
	public InstantiateSunsAction(PlantsVsRobotsPanel panel) {
		this.panel = panel;
		timer = new Timer(0, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.setDelay((int)Math.random() * 7500 + 16500);
		System.out.println("Instantiated Sun");
		panel.addSunToList(new Sun(panel, whereToDropSun(), 100));
		panel.repaint();
	}
	
	public int whereToDropSun() {
		int whereToDrop = (int)(Math.random() * 600) + 50;
		return whereToDrop;
	}
}
