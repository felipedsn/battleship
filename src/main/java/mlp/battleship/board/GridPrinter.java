package mlp.battleship.board;

import java.io.IOException;

import mlp.battleship.constants.CONSTANTS;
import mlp.battleship.enums.PositionStatus;

public class GridPrinter {
	
	public static void printGrid(PositionStatus[][] grid, String resultMessage) throws IOException, InterruptedException {
		StringBuilder board = new StringBuilder();
		
		clearConsole();
		printLegend();
		if(resultMessage != null) {
			System.out.println(resultMessage);
		}
		
		board.append("\t1  2  3  4  5  6  7  8  9  10 11 12 13 14 15\n");
		for(int i = 0; i < CONSTANTS.BOARD_DIMENSION; i++) {
			board.append(i+1).append("\t");
			for(int j = 0; j < CONSTANTS.BOARD_DIMENSION; j++) {
				board.append(grid[i][j].getGridCode()).append("  ");
			}
			board.append("\n");
		}
		System.out.println(board.toString());
	}
	
	private static void printLegend() {		
		System.out.println(String.format("[%s] - Water\n[%s] - Hit\n[%s] - Miss\n", 
							PositionStatus.WATER.getGridCode(), 
							PositionStatus.HIT.getGridCode(), 
							PositionStatus.MISS.getGridCode()));		
	}
	
	private static void clearConsole() throws IOException, InterruptedException {
	    final String os = System.getProperty("os.name");
	
	    if (os.contains("Windows"))
	    {
	    	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    }
	    else
	    {
	    	System.out.print("\033[H\033[2J");  
	        System.out.flush();  
	    }
	}
}
