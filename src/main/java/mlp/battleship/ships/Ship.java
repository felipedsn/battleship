package mlp.battleship.ships;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;
import mlp.battleship.enums.ShipType;

public abstract class Ship {
	private int line;
	private int column;
	private Direction direction;
	
	public Ship(int line, int column, Direction direction) {
		this.setColumn(column);
		this.setLine(line);
		this.setDirection(direction);
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		
		if(column > CONSTANTS.BOARD_DIMENSION){
			this.column = CONSTANTS.BOARD_DIMENSION;
		} else if (column < 0) {
			this.column = 0;
		} else {
			this.column = column;
		}
	}
	
	public int getLine() {
		return line;
	}
	
	public void setLine(int line) {
		
		if(line > CONSTANTS.BOARD_DIMENSION){
			this.line = CONSTANTS.BOARD_DIMENSION;
		} else if (line < 0) {
			this.line = 0;
		} else {
			this.line = line;
		}
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public abstract int getSize();
	
	public abstract ShipType getType();
}
