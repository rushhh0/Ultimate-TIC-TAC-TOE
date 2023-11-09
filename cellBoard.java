
public class cellBoard {
	// 2D array for 3x3 board
	private int scoreToWinGame = 3;
	private int rowLength = 3;
	private int colLength = 3;
	private String[][] cellBoardWinner;

	cellBoard() {
		cellBoardWinner = new String[rowLength][colLength];

		// initializing each cell as '-'
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				cellBoardWinner[i][j] = "-";
			}
		}
	}

	// flagging board and returning 1 if there is a winner else returning 0
	public void flaggingBoard(int boardNumber, String mark) {
		// To find the position in 2D array, also passing the board number as parameter
		int row = returnCellBoardWinnerRow(boardNumber);
		int col = returnCellBoardWinnerCol(boardNumber);
		// Checks for gameover and also sets the winner if returned value is 1
		cellBoardWinner[row][col] = mark;
	}

	private int returnCellBoardWinnerRow(int boardNumber) {
		if (boardNumber <= 2) {
			return 0;
		} else if (boardNumber <= 5) {
			return 1;
		}
		return 2;
	}

	private int returnCellBoardWinnerCol(int boardNumber) {
		if (boardNumber == 0 || boardNumber == 3 || boardNumber == 6) {
			return 0;
		} else if (boardNumber == 1 || boardNumber == 4 || boardNumber == 7) {
			return 1;
		}
		return 2;
	}

	// Check if the board is full
	public boolean isFull() {
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				// Check if there are any boards which are not filled currently
				if (isAvailable(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	// Using the default 3x3 rule for winner check

	public boolean isWon(int boardNumber) {
		// Gets position in 2D array
		int row = returnCellBoardWinnerRow(boardNumber);
		int col = returnCellBoardWinnerCol(boardNumber);
		return !isAvailable(row, col);
	}

	// Checking if the position has "X" or "O" marked already, if not return it is
	// available to make move
	private boolean isAvailable(int row, int col) {
		return !cellBoardWinner[row][col].equals("X") && !cellBoardWinner[row][col].equals("O");
	}

	// Detencts winner
	public boolean isWinnerInCellBoard(String mark) {
		if (checkAllRows(mark)) {
			return true;
		} else if (checkAllColumns(mark)) {
			return true;
		} else if (checkDiagonal1(mark)) {
			return true;
		} else if (checkDiagonal2(mark)) {
			return true;
		} else {
			return false;
		}
	}

	// Check for winner by row
	private boolean checkAllRows(String mark) {
		for (int row = 0; row < this.rowLength; row++) {
			if (checkRow(row, mark)) {
				return true;
			}
		}
		return false;
	}

	// Traversing columns of rows
	private boolean checkRow(int row, String mark) {
		int counter = 0;
		for (int col = 0; col < this.rowLength; col++) {
			if (cellBoardWinner[row][col].equals(mark)) {
				counter++;
			}
		}
		if (counter == scoreToWinGame) {
			return true;
		}
		return false;
	}

	// Check for winner by column
	private boolean checkAllColumns(String mark) {
		for (int col = 0; col < this.colLength; col++) {
			if (checkCol(col, mark)) {
				return true;
			}
		}
		return false;
	}

	// Traversing rows of columns
	private boolean checkCol(int col, String mark) {
		int counter = 0;
		for (int row = 0; row < this.rowLength; row++) {
			if (cellBoardWinner[row][col].equals(mark)) {
				counter++;
			}
		}
		if (counter == this.scoreToWinGame) {
			return true;
		}
		return false;
	}

	// Right to Left: top right to bottom left win
	private boolean checkDiagonal1(String mark) {
		int counter = 0;
		for (int row = 0, col = this.rowLength - 1; row < this.colLength && col >= 0; row++, col--) {
			if (cellBoardWinner[row][col].equals(mark)) {
				counter++;
			}
		}
		if (counter == this.scoreToWinGame) {
			return true;
		}
		return false;
	}

	// Left to Right: top left to bottom right win
	private boolean checkDiagonal2(String mark) {
		int counter = 0;
		for (int col = 0, row = 0; col < colLength && row < this.colLength; col++, row++) {
			if (cellBoardWinner[row][col].equals(mark)) {
				counter++;
			}
		}
		if (counter == this.scoreToWinGame) {
			return true;
		}
		return false;
	}
}
