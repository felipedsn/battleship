package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;

public class Mine extends Ship {
	public Mine(int x, int y, Direction direction) {
		super(x, y, direction);
	}

	@Override
	public int getSize() {
		return CONSTANTS.MINE_SIZE;
	}
}
