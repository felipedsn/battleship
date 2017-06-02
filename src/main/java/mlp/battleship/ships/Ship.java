package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;

public abstract class Ship {
	private int x;
	private int y;
	private Direction direction;
	
	public Ship(int x, int y, Direction direction) {
		this.setX(x);
		this.setY(y);
		this.setDirection(direction);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		
		if(x > CONSTANTS.BOARD_DIMENSION){
			this.x = CONSTANTS.BOARD_DIMENSION;
		} else if (x < 0) {
			this.x = 0;
		} else {
			this.x = x;
		}
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		
		if(y > CONSTANTS.BOARD_DIMENSION){
			this.y = CONSTANTS.BOARD_DIMENSION;
		} else if (y < 0) {
			this.y = 0;
		} else {
			this.y = y;
		}
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public abstract int getSize();
}
