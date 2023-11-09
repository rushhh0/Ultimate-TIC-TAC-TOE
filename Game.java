import java.util.Scanner;

public class Game {
	Scanner input = new Scanner(System.in);
	// Two player game(computer or human)
	private Players[] players = new Players[2];
	// Keep track of each cell board winner
	private cellBoard winCellBoard = new cellBoard();
	// Outerboard variable
	private OuterBoard board;
	// Players's mark
	private String[] marks = { "X", "O" };

	private int totalMovesMade; // Varibale for total number of moves made in entire game
	private int gameScoreToWin = 3; // For outerboard
	private int currentPlayerIndex = -1; // Keep track of player's turn and position

	private String[] BoardOverWon = new String[9]; // 9 boards with length of 9 (0 to 8)

	// Constructor
	public Game() {
		setPlayers();
		setBoard();
	}

	public void start() {

		initEmptyBoards(); // Initialise all the boards for the players before first turn is made

		do {
			switchPlayers(); // Switch player's turn
			printCurrentPlayerIndex(); // Print the mark where user decided to make the move
			// switchPlayers(); // Switch player's turn

			// First move
			if (totalMovesMade == 0) {
				board.setFreeBoardNum(players[this.currentPlayerIndex].isPlayerComputer());
			}

			// Get row and col index to make sure if the board is full
			getIndex();

			/*
			 * Checking if board is not full, then let user notify what board number they
			 * are currently sent by the last player, then letting them know what available
			 * moves they have to play their turn in the board and sends the next player to
			 * the correct board
			 */
			if (!board.isFull()) {
				printCurrentBoardNumber();
				// Show available moves
				board.availableMoves();
				board.setCellBoardNum(players[this.currentPlayerIndex].isPlayerComputer());
			}
			// Get row and column location for 2D array given square and cellboard numbers
			getIndex();

			// Check if the board is full, prints the message that move is unavailable and
			// reset the moce and ask player to play again
			if (board.isFull()) {
				System.out.println("Unavailable to place this piece the board is full");
				board.setFreeBoardNum(players[this.currentPlayerIndex].isPlayerComputer());
				printCurrentBoardNumber(); // Print selected board num
				board.setFreeBoardNum(players[this.currentPlayerIndex].isPlayerComputer());
				getIndex();
			}
			// Reset squareNummber for computer if invalid
			if (players[this.currentPlayerIndex].isPlayerComputer()) {
				while (board.resetCellNumber(
						players[this.currentPlayerIndex].pickRowContent(board.getMaxRowLength(),
								board.getMinRowLength(), board.getCellBoardNum()),
						players[this.currentPlayerIndex].pickColContent(board.getMaxColLength(),
								board.getMinColLength(), board.getCellBoardNum()))) {
					getIndex();
				}
			}
			// Print selected cellboard number
			printSelectedCellBoardNum();

			// Makes a valid move, using mark, row, col, and if player is computer/human
			if (board.makeMove(players[this.currentPlayerIndex].getMark(),
					players[this.currentPlayerIndex].pickRowContent(board.getMaxRowLength(), board.getMinRowLength(),
							board.getCellBoardNum()),
					players[this.currentPlayerIndex].pickColContent(board.getMaxColLength(), board.getMinColLength(),
							board.getCellBoardNum()),
					players[this.currentPlayerIndex].isPlayerComputer())) {
				;
			}

			// Marking the cellboard when the move is made
			else {
				// Switch turn
				switchPlayers();
				// Print baord
				board.printMyBoard();
				System.out.println("");
				// Print winners of the cellboard
				printBoardResults();
				System.out.println("");
				continue; // switch to the same player that made invalid
			}
			// Count valid move
			totalMovesMade++;

			// Cell board winner, board hasn't been won yet
			if (cellBoardWin() && !winCellBoard.isWon(board.getFreeBoardNum())) {
				// Mark a board win
				winCellBoard.flaggingBoard(board.getFreeBoardNum(), players[this.currentPlayerIndex].getMark());
				// Print board
				board.printMyBoard();
				// Set cell board as winner, store player's mark at board index
				BoardOverWon[board.getFreeBoardNum()] = players[this.currentPlayerIndex].getMark();
			}
			// No winner on any of the boards
			else {
				// Print the board and continue game
				board.printMyBoard();
			}

			System.out.println("");
			// Print winners of boards
			printBoardResults();
			System.out.println("");
			// Set selected square as cellboard number for next turn
			board.setFreeBoardNum(board.getCellBoardNum());
			// End loop if entire game is finished (tie or won)
		} while (!gameOver());
	}

	// Setting up the 9x9 board
	private void setBoard() {
		this.board = new OuterBoard(9, 9);
	}

	// Set players in game
	private void setPlayers() {
		for (int i = 0; i < players.length; i++) {
			AIplayer player = new AIplayer("Player " + i + 1, marks[i]);
			players[i] = player;
		}
	}

	// As per the user pick setting up the player signs
	public void setPlayers(Players player1, Players player2) {
		players[0] = player1;
		players[1] = player2;
	}

	// Switches turns
	private void switchPlayers() {
		if (this.currentPlayerIndex != 0) {
			this.currentPlayerIndex = 0;
		} else {
			this.currentPlayerIndex = 1;
		}
	}

	// Initialising the boards to "-"
	private void initEmptyBoards() {
		if (totalMovesMade == 0) {
			for (int i = 0; i < BoardOverWon.length; i++) {
				BoardOverWon[i] = "-";
			}
		}
	}

	// Prints the current player's turn with their sign next to it
	private void printCurrentPlayerIndex() {
		System.out.println("Current player is : " + players[this.currentPlayerIndex].getMark());
	}

	// Prints the cell board # the player is about to make move in
	private void printCurrentBoardNumber() {
		System.out.println("Current Board : " + board.getFreeBoardNum());
	}

	// Prints the number of the cell board location where the last player made the
	// move
	private void printSelectedCellBoardNum() {
		System.out.println("Move Played : " + board.getCellBoardNum());
		System.out.println("");
	}

	// Print the board results, like if the board is won or tied
	private void printBoardResults() {
		for (int i = 0; i < BoardOverWon.length; i++) {

			if (BoardOverWon[i].equals("X") || BoardOverWon[i].equals("O")) {
				System.out.println("The Board#" + i + " winner is " + BoardOverWon[i]);
			} else if (board.isFull() == true) {
				System.out.println("The Board#" + i + " tied ");
			} else {

			}

		}
	}

	// Using board number, set boundaries to focus on each board
	private void getIndex() {
		board.setMaxRowLength(2 + (board.getFreeBoardNum() / 3) * 3);
		board.setMinRowLength((board.getFreeBoardNum() / 3) * 3);
		board.setMaxColLength(2 + ((board.getFreeBoardNum() % 3) * 3));
		board.setMinColLength((board.getFreeBoardNum() % 3) * 3);
	}

	// Checking all the board from 0 to 8
	private boolean cellBoardWin() {
		if (checkCellBoardRows(board.getMinRowLength())) {
			return true;
		} else if (checkCellBoardCols(board.getMinColLength())) {
			return true;
		} else if (checkCellBoardDiagonal1(board.getMinRowLength(), board.getMaxColLength())) {
			return true;
		} else if (checkCellBoardDiagonal2(board.getMinRowLength(), board.getMinColLength())) {
			return true;
		} else {
			return false;
		}
	}

	// Checks for row win
	private boolean checkCellBoardRows(int row) {
		for (; row <= board.getMaxRowLength(); row++) {
			if (checkCellBoardRow(row, board.getMinColLength())) {
				return true;
			}
		}
		return false;
	}

	// Check by traversing cols of row
	private boolean checkCellBoardRow(int row, int col) {
		int counter = 0;
		for (; col <= board.getMaxColLength(); col++) {
			if (board.getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				counter++;
			}
		}
		if (counter == this.gameScoreToWin) {
			return true;
		}
		return false;
	}

	// Check for col win
	private boolean checkCellBoardCols(int col) {
		for (; col <= board.getMaxColLength(); col++) {
			if (checkCellBoardCol(col, board.getMinRowLength())) {
				return true;
			}
		}
		return false;
	}

	// Check by traversing rows of col
	private boolean checkCellBoardCol(int col, int row) {
		int counter = 0;
		for (; row <= board.getMaxRowLength(); row++) {
			if (board.getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				counter++;
			}
		}
		if (counter == this.gameScoreToWin) {
			return true;
		}
		return false;
	}

	// Bottom left to top right win
	private boolean checkCellBoardDiagonal1(int row, int col) {
		int counter = 0;
		for (; row <= board.getMaxRowLength() && col >= board.getMinColLength(); row++, col--) {
			if (board.getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				counter++;
			}
		}
		if (counter == this.gameScoreToWin) {
			return true;
		}
		return false;
	}

	// Top left to bottom right win
	private boolean checkCellBoardDiagonal2(int row, int col) {
		int counter = 0;
		for (; col <= board.getMaxColLength() && row <= board.getMaxRowLength(); col++, row++) {
			if (board.getMark(row, col).equals(players[currentPlayerIndex].getMark())) {
				counter++;
			}
		}
		if (counter == this.gameScoreToWin) {
			return true;
		}
		return false;
	}

	private boolean gameOver() {
		// Check if any player won the outerboard, and print the total moves made
		// thrughout the game
		if (winCellBoard.isWinnerInCellBoard(players[currentPlayerIndex].getMark())) {
			System.out.println(this.marks[this.currentPlayerIndex] + " Won!");
			System.out.println("Total moves made in the game: " + totalMovesMade);
			return true;
			// If game tied
		} else if (winCellBoard.isFull() || board.outerBoardCompletelyFull()) {
			System.out.println("Tie!");
			System.out.println("Total moves made in the game: " + totalMovesMade);
			return true;
		}
		return false; // continue to game
	}
}