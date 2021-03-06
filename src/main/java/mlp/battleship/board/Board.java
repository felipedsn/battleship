package mlp.battleship.board;

import java.util.ArrayList;
import java.util.List;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;
import mlp.battleship.enums.PositionStatus;
import mlp.battleship.ships.Ship;

public class Board {
	private PositionStatus[][] 	grid			= new PositionStatus[CONSTANTS.BOARD_DIMENSION][CONSTANTS.BOARD_DIMENSION];
	private List<Ship> 			aliveShips		= new ArrayList<Ship>();
	private List<Ship> 			sunkShips		= new ArrayList<Ship>();
	
	public Board() {
		initGrid();
	}

	public PositionStatus[][] getGrid() {
		return grid;
	}

	public void setGrid(PositionStatus[][] grid) {
		this.grid = grid;
	}
	
	private void initGrid(){
		for(int i = 0; i < CONSTANTS.BOARD_DIMENSION; i++) {
			for(int j = 0; j < CONSTANTS.BOARD_DIMENSION; j++) {
				grid[i][j] = PositionStatus.WATER;
			}
		}
	}
	
	public void addShip(Ship ship) {
		addShipToGrid(ship);
		aliveShips.add(ship);
	}
	
	private void addShipToGrid(Ship ship) {
		int line = ship.getLine();
		int column = ship.getColumn();
		
		for(int i = 0; i < ship.getSize(); i++) {
			if(ship.getDirection() == Direction.HORIZONTAL) {
				grid[line][column + i] = PositionStatus.SHIP;
			} else {
				grid[line + i][column] = PositionStatus.SHIP;
			}
		}
	}
	
	public boolean isValidPosition(Ship ship) {
		int line = ship.getLine();
		int column = ship.getColumn();
		Direction direction = ship.getDirection();
		/**
		 * Check if the ship fits in the grid.
		 */
		if(direction == Direction.HORIZONTAL) {
			if((column + ship.getSize()) >= CONSTANTS.BOARD_DIMENSION) {
				return false;
			}
		} else {
			if((line + ship.getSize()) >= CONSTANTS.BOARD_DIMENSION) {
				return false;
			}
		}
		/**
		 * Check if there is no overlapping.
		 */
		for(int i = 0; i < ship.getSize(); i++){
			if(direction == Direction.HORIZONTAL) {
				if(grid[line][column + i] == PositionStatus.SHIP){
					return false;
				}
			} else {
				if(grid[line + i][column] == PositionStatus.SHIP){
					return false;
				}
			}
		}
		return true;
	}
	
	public void addGuess(int line, int column) {
		switch(grid[line][column]) {
		case WATER:
			grid[line][column] = PositionStatus.MISS;
			break;
		case HIT:
			grid[line][column] = PositionStatus.HIT;
			break;
		case MISS:
			grid[line][column] = PositionStatus.MISS;
			break;
		case SHIP:
			grid[line][column] = PositionStatus.HIT;
			break;
		default:
			break;
		}
	}
	
	public Ship hasSunkShip() {
		
		for(Ship ship : aliveShips) {
			int line = ship.getLine();
			int column = ship.getColumn();
			Direction direction = ship.getDirection();
			boolean foundSunkShip = true;
			
			for(int i = 0; i < ship.getSize(); i++){
				if(direction == Direction.HORIZONTAL) {
					if(grid[line][column + i] != PositionStatus.HIT) {
						foundSunkShip = false;
						break;
					}
				} else {
					if(grid[line + i][column] != PositionStatus.HIT) {
						foundSunkShip = false;
						break;
					}
				}
			}
			if(foundSunkShip) {
				aliveShips.remove(ship);
				sunkShips.add(ship);
				return ship;
			}
		}
		return null;
	}
	
	public boolean hasAliveShip() {
		return !aliveShips.isEmpty();
	}
}
