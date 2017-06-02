package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;

public class Battleship extends Ship {
	public Battleship(int x, int y, Direction direction) {
		super(x, y, direction);
	}

	@Override
	public int getSize() {
		return CONSTANTS.BATTLESHIP_SIZE;
	}
}
