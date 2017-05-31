
public class Plant {
	private int xPos;
	private String name;
	public Plant(int x, int i) {
		xPos = x;
		switch(i) {
			case 0:
				name = "Sunflower";
				break;
			case 1:
				name = "Peashooter";
				break;
			case 2:
				name = "Walnut";
				break;
			default:
				name = "No name";
				System.out.println("Could not find name of plant, setting name to 'No name'.");
		}
	}
	public boolean selected(int x, int y) { // every selector is 80 pixels wide
		if (x >= xPos + 5 && x <= xPos - 5 + 80 && y >= 0 && y <= 100) { // offset by 5 pixels on each side so you cannot select more than one plant.
			return true;
		}
		return false;
	}
	public String getName() {
		return name;
	}
}
