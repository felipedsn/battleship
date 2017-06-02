package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;

public class Submarine extends Ship {
	public Submarine(int x, int y, Direction direction) {
		super(x, y, direction);
	}

	@Override
	public int getSize() {
		return CONSTANTS.SUBMARINE_SIZE;
	}
}
