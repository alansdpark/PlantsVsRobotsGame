import java.awt.Color;
import java.awt.Graphics;

public class PlantableGrid {
	private PlantableSpot[][] gameGrid = new PlantableSpot[5][8];
	public PlantableGrid() {
		for (int i = 0; i < gameGrid.length; i++) {
			for (int j = 0; j < gameGrid[0].length; j++) {
				gameGrid[i][j] = new PlantableSpot(i, j);
			}
		}
	}
	public PlantableSpot getSpot(int x, int y) {
		return gameGrid[x][y];
	}
	public PlantableSpot[][] getGameGrid() {
		return gameGrid;
	}
}
