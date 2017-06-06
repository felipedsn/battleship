package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;
import mlp.battleship.enums.ShipType;

public class Battleship extends Ship {
	public Battleship(int x, int y, Direction direction) {
		super(x, y, direction);
	}

	@Override
	public int getSize() {
		return CONSTANTS.BATTLESHIP_SIZE;
	}

	@Override
	public ShipType getType() {
		return ShipType.BATTLESHIP;
	}
}
