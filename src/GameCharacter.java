
public class GameCharacter {
	private int hp;
	
	public GameCharacter() {
		hp = 10;
	}
	public void setHealth(int h) {
		hp = h;
	}
	public int getHealth() {
		return hp;
	}
	public void reduceHealth(int h) {
		hp -= h;
	}
}
