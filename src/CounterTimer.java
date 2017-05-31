import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class CounterTimer implements ActionListener {
	private Timer timer;
	private int count;
	public CounterTimer() {
		count = 0;
		timer = new Timer(1000, this);
	}
	
	public void startTimer() {
		timer.start();
	}
	
	public void stopTimer() {
		timer.stop();
	}
	public int getCount() {
		return count;
	}
	
	public void resetCount() {
		count = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;
	}
}
