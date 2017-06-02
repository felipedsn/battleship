package mlp.battleship;

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
		int x = ship.getX();
		int y = ship.getY();
		
		for(int i = 0; i < ship.getSize(); i++) {
			if(ship.getDirection() == Direction.HORIZONTAL) {
				grid[x + i][y] = PositionStatus.SHIP;
			} else {
				grid[x][y + i] = PositionStatus.SHIP;
			}
		}
	}
	
	public boolean isValidPosition(Ship ship) {
		int x = ship.getX();
		int y = ship.getY();
		Direction direction = ship.getDirection();
		/**
		 * Check if the ship fits in the grid.
		 */
		if(direction == Direction.HORIZONTAL) {
			if((x + ship.getSize()) >= CONSTANTS.BOARD_DIMENSION) {
				return false;
			}
		} else {
			if((y + ship.getSize()) >= CONSTANTS.BOARD_DIMENSION) {
				return false;
			}
		}
		/**
		 * Check if there is no overlapping.
		 */
		for(int i = 0; i < ship.getSize(); i++){
			if(direction == Direction.HORIZONTAL) {
				if(grid[x + i][y] == PositionStatus.SHIP){
					return false;
				}
			} else {
				if(grid[x][y + i] == PositionStatus.SHIP){
					return false;
				}
			}
		}
		return true;
	}
	
	public void addGuess(int x, int y) {
		switch(grid[x][y]) {
		case WATER:
			grid[x][y] = PositionStatus.MISS;
			break;
		case HIT:
			grid[x][y] = PositionStatus.HIT;
			break;
		case MISS:
			grid[x][y] = PositionStatus.MISS;
			break;
		case SHIP:
			grid[x][y] = PositionStatus.HIT;
			break;
		default:
			break;
		}
	}
	
	public Ship hasSunkShip() {
		boolean foundSunkShip = true;
		
		for(Ship ship : aliveShips) {
			int x = ship.getX();
			int y = ship.getY();
			Direction direction = ship.getDirection();
			
			for(int i = 0; i < ship.getSize(); i++){
				if(direction == Direction.HORIZONTAL) {
					if(grid[x + i][y] != PositionStatus.HIT) {
						foundSunkShip = false;
						break;
					}
				} else {
					if(grid[x][y + i] != PositionStatus.HIT) {
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
	
	public boolean sunkAllShips() {
		return aliveShips.isEmpty();
	}
}
