import java.util.Scanner;

public class OuterBoard {
	// Used to read the user input from the console
	Scanner input = new Scanner(System.in);
	// Macro board of the game
	// 9x9 board

	// Variables for rowsize and column size
	private int outerBoardRow;
	private int outerBoardCol;
	private String[][] outerBoard;

	// Outerboard has datafields of CellBoard and Square number
	private int numOfSquare;
	private int numOfBoard;

	// Setting the border for the outer board
	private int maxRowLength;
	private int minRowLength;
	private int maxColLength;
	private int minColLength;

	// setters and getters method
	public void setMaxRowLength(int maxRowLength) {
		this.maxRowLength = maxRowLength;
	}

	public int getMaxRowLength() {
		return maxRowLength;
	}

	public void setMinRowLength(int minRowLength) {
		this.minRowLength = minRowLength;
	}

	public int getMinRowLength() {
		return minRowLength;
	}

	public void setMaxColLength(int maxColLength) {
		this.maxColLength = maxColLength;
	}

	public int getMaxColLength() {
		return maxColLength;
	}

	public void setMinColLength(int minColLength) {
		this.minColLength = minColLength;
	}

	public int getMinColLength() {
		return minColLength;
	}

	// Constructor with defaault size 9x9
	OuterBoard() {
		this(9, 9);
	}

	// Setting up border for the outer board
	OuterBoard(int rowLength, int colLength) {
		this.setSize(rowLength, colLength);
	}

	// setting the size for row and col
	private void setSize(int rowSize, int colSize) {
		this.outerBoardRow = rowSize;
		this.outerBoardCol = colSize;
		setUp(); // Setting up the ultimate board
	}

	// Intializing the outer board
	void setUp() {
		this.outerBoard = new String[9][9]; // defining 2D array for board

		// Initialize entire 2D array
		int numOfSquare = 0;

		// for loop for the generation of 2D array
		for (int i = 0; i < outerBoard.length; i++) {
			// Resets the row number 3 and 6 with the num of square = 0
			if (i != 0 && i % 3 == 0)
				numOfSquare = 0;
			// For generation of col
			for (int j = 0; j < outerBoard[i].length; j++) {
				if (j != 0 && j % 3 == 0)
					numOfSquare = numOfSquare - 3;

				// toString method used with Integer wrapper class with increasing num of square
				// in parameters
				// Sets the initialize with the range from 0 to 8
				outerBoard[i][j] = Integer.toString(numOfSquare++);
			}
		}

		System.out.println(
				"                     WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!!                                             ");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("");
		printMyBoard();
	}

	public void printMyBoard() {
		// Print array by row
		int numOfBoard = 0;

		// For loop which generates the row
		for (int i = 0; i < outerBoard.length; i++) {
			// if not the row of multiple of 3 then it will reset the num of board with -= 3
			if (i % 3 != 0)
				numOfBoard = numOfBoard - 3;

			// printing row with 9 cols
			for (int j = 0; j < outerBoard[i].length; j++) {
				// Cols of 3 means a row of 1 sub-board has been printed
				// if col of 3 means that the row 1 of mini board has to be printed

				if (j % 3 == 0) {
					System.out.print("CELLBOARD#" + numOfBoard++ + " | ");
				}
//				if(j % 3 == 0) {
//					System.out.print("|");
//				}

				// prints the piece
				System.out.print(outerBoard[i][j] + " | ");

			}

			// prints the next row
			System.out.println();
		}
	}

	// To make move on the board
	public boolean makeMove(String mark, int row, int col, boolean isCompletelyFull) {

		// Check whether move made is available
		if (isAvailable(row, col) && !isFull()) {
			outerBoard[row][col] = mark;
			return true;
		}
		// Check if the board is full entirely or not.
		// If there is a winner for the board and there are empty squares it will still
		// be available.
		else if (isFull()) {
			return true;
		}
		// Range of square is from 0 to 8
		System.out.println("Invalid move try again");
		return false;
	}

	// Method created to see if there are any moves available in the rows and cols
	public void availableMoves() {
		int rowMin = getMinRowLength();
		int rowMax = getMaxRowLength();
		int colMin = getMinColLength();
		int colMax = getMaxColLength();

		System.out.print("Available Moves on the Board: ");
		for (; rowMin <= rowMax; rowMin++) {
			for (int col = colMin; col <= colMax; col++) {
				if (isAvailable(rowMin, col)) {
					System.out.print(outerBoard[rowMin][col] + " ");
				}
			}
		}
		System.out.println("");
	}

	// Actualing checking
	private boolean isAvailable(int row, int col) {
		return Character.isDigit(outerBoard[row][col].charAt(0));
	}

	// Check if the cellboard is full or not
	public boolean isFull() {
		int rowMin = getMinRowLength();
		int rowMax = getMaxRowLength();
		int colMin = getMinColLength();
		int colMax = getMaxColLength();

		// for loop to go through row and cols
		for (; rowMin <= rowMax; rowMin++) {
			for (int col = colMin; col <= colMax; col++) {

				// checks with isAvailable method if there is any place left
				if (isAvailable(rowMin, col)) {
					return false; // Returns false that Board is not full
				}
			}
		}
		return true; // Will return true if the Board is full
	}

	// This methods checks if the entire board is full
	public boolean outerBoardCompletelyFull() {

		for (int i = 0; i < outerBoardRow; i++) {
			for (int j = 0; j < outerBoardCol; j++) {

				// Checks if any position is equal to X or O
				if (!outerBoard[i][j].equals("X") && !outerBoard[i][j].equals("O")) {
					return false;
				}
			}
		}
		return true;
	}

	// This method Returns mark to the board
	public String getMark(int row, int col) {
		return outerBoard[row][col];
	}

	// Computer player randomly picks a number from the range
	public void setFreeBoardNum(boolean isCompu) {
		if (isCompu) {
			numOfBoard = (int) (Math.random() * 9);
		} else {
			System.out.print("Please select a valid board: ");
			while (!input.hasNext("[012345678]")) {
				System.out.println("invalid input try again");
				System.out.print("Please select a valid board: ");
				input.next();

			}
			numOfBoard = input.nextInt();
		}
	}

	// Overloading setter for setting numOfBoard to numofSquare
	public void setFreeBoardNum(int boardNum) {
		this.numOfBoard = boardNum;
	}

	public int getFreeBoardNum() {
		return numOfBoard;
	}

	// Reset cellboard for computer
	public boolean resetCellNumber(int row, int col) {
		if (!isAvailable(row, col) && !isFull()) {
			setCellBoardNum(true);
			return true;
		}
		return false;
	}

	public void setCellBoardNum(boolean isCompu) {
		// If player is computer
		if (isCompu) {
			numOfSquare = (int) (Math.random() * 9); // Pick a random board from 0 to 9
		} else { // If player is human
			System.out.print("Please select a valid square on the selected board: ");
			while (!input.hasNext("[012345678]")) {
				System.out.println("invalid input try again");
				System.out.print("Please select a valid square on the selected board: ");
				input.next();

			}
			numOfSquare = input.nextInt();
		}
	}

	public int getCellBoardNum() {
		return numOfSquare;
	}

}
