package mlp.battleship.apps;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import mlp.battleship.board.Board;
import mlp.battleship.board.GridPrinter;
import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.Direction;
import mlp.battleship.ships.Battleship;
import mlp.battleship.ships.Mine;
import mlp.battleship.ships.Ship;
import mlp.battleship.ships.Submarine;

/**
 * Object-oriented version.
 */
public class AppOOP {
	
	public static void main(String[] args) throws IOException{
		Board board = new Board();
		
		createShips(board);
		while(board.hasAliveShip()) {
			GridPrinter.printGrid(board.getGrid());
			processGuess(board);
			if(!board.hasAliveShip()) {
				System.out.println("Congratulations, you sunk all the fleet!");
			}
		}
	}
	
	private static void createShips(Board board) {
		Ship ship;
		int line, column, i;
		Direction direction;
		Random rand = new Random();
		
		//Add Battleships
		System.out.println("Creating Battleships");
		for(i = 0; i < CONSTANTS.NUMBER_BATTLESHIPS; i++) {
			do {
				line = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				column = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				direction = (rand.nextInt(2) + 1) == 1 ? Direction.HORIZONTAL : Direction.VERTICAL;
				ship = new Battleship(column, line, direction);
			} while(!board.isValidPosition(ship));
			board.addShip(ship);
			System.out.println(String.format("Create Battleship %s", i + 1));
		}
		//Add Submarines
		for(i = 0; i < CONSTANTS.NUMBER_SUBMARINES; i++) {
			do {
				line = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				column = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				direction = (rand.nextInt(2) + 1) == 1 ? Direction.HORIZONTAL : Direction.VERTICAL;
				ship = new Submarine(column, line, direction);
			} while(!board.isValidPosition(ship));
			board.addShip(ship);
			System.out.println(String.format("Create Submarine %s", i + 1));
		}
		//Add Mines
		for(i = 0; i < CONSTANTS.NUMBER_MINES; i++) {
			do {
				line = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				column = rand.nextInt(CONSTANTS.BOARD_DIMENSION - 1);
				direction = (rand.nextInt(2) + 1) == 1 ? Direction.HORIZONTAL : Direction.VERTICAL;
				ship = new Mine(column, line, direction);
			} while(!board.isValidPosition(ship));
			board.addShip(ship);
			System.out.println(String.format("Create Mine %s", i + 1));
		}
		System.out.println("\n");
	}
	
	private static void processGuess(Board board) {
		
		Ship ship;
		int line, column;
		
		System.out.println("Give your shot!");
		// Get line
		line = getCoordinates("Line: ");
		//Get column
		column = getCoordinates("Column: ");
		
		
		board.addGuess(line, column);
		
		switch(board.getGrid()[line][column]) {
			case MISS:
				System.out.println("\nYou missed :(\n");
				break;
			case HIT:
				System.out.println("\nYou hit a ship!");
				break;
			default:
				break;
		}
		
		if((ship = board.hasSunkShip()) != null) {
			System.out.println(String.format("Good job, you sunk a %s!\n", ship.getType()));
		}
	}
	
	@SuppressWarnings("resource")
	private static int getCoordinates(String prompt) {
		Scanner input = new Scanner(System.in);
	    System.out.print(prompt);
	    while(true){
	        try {
	            return Integer.parseInt(input.next());
	        } catch(NumberFormatException ne) {
	            System.out.print("Invalid Input!\n"+prompt);
	        }
	    }
	}
}
