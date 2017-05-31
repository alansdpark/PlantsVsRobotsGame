import java.awt.BorderLayout;

import javax.swing.JFrame;

public class PlantsVsRobotsMain {
	public static void main(String[] args) {
		JFrame window = new JFrame("Plants Vs Robots");
		PlantsVsRobotsPanel panel = new PlantsVsRobotsPanel();
		window.setContentPane(panel);
		PlantsVsRobotsListener listener = new PlantsVsRobotsListener(panel);
		InstantiateSunsAction instantiateSuns = new InstantiateSunsAction(panel);
		window.setSize(800, 700);
		window.setLocation(200, 30);
		window.setResizable(false);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
