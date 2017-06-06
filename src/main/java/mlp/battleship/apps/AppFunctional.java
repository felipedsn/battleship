package mlp.battleship.apps;

import java.io.IOException;

import mlp.battleship.board.Board;
import mlp.battleship.board.GridPrinter;

public class AppFunctional {
	public static void main(String[] args) throws IOException {
		Board board = new Board();
		GridPrinter.printGrid(board.getGrid());
	}
}
