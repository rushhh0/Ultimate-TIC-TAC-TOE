import java.util.Scanner;
/* Ultimate Tic-Tac-Toe game
* Introduction
* Name: Rushi Trivedi
* Class: Computer Science II 2336.003
* Professor : Mehra Nouroz Borazjany 
* 
* Analysis
* The game starts with a welcome message, and prints sets of intrustictions and rules for the game. Once the user reads the rules 
* they can begin the game by choosing the mode of player 1 and player 2. Game can be played between: 
* (1) manual (human) player vs manual (human) player
* (2) manual (human) player vs AI computer player
* (3) AI computer player vs AI computer player
* If the game involves manual player, user has an option to take first turn or go second. Player 1 will be by default assigned mark X 
* and player 2 will be default O. The game also notifies the current playermark, the current board they are gonna play in, available moves 
* on the specific cell board and the move played by the current player will be displayed too for the next player. After each move board 
* will be updated and printed below. Game will terminate if one of the player wins or the board is is full completely.

* Design
* My approach for the ultimate tic-tac-toe is same as I used in phases, I used the same logic for the ultimate outer board and 
* each cell board, each cell board I am initializing it a number from 0 to 8. Player 1 alwyas picks the first board where they 
* want to put the first piece on the board. I use 2D array to form the board and take care of the index of the move made. Automatically 
* turns are switch after the move. Game accepts all the valid moves and prints a message when invalid move is made and reset's the 
* move to the same player. Main (Driver) set the players and assign the mark has the first call to the game class. Player class 
* is an abstract class and ManualPlayer and AI player implemts it. Player modes are being asked in the main method. Game class 
* follows the rules of ultimate tic-tac-toe game, basically OuterBoard (9x9). In OuterBoard 2D array takes care of all the rows 
* and columns and I also offsetted the boundary of the board so it limits from invalid move on the virtual board. Its the class 
* where I check for the next available moves, and check if the board is full and set-up my board and print after every move. 
* Where the Game class takes care of the outer board winner and checks for row, column and both diagonals, cellBoard class takes 
* care of each individual cell boards. (Note: I use different variables for both check outer and inner, but the logic is still same). 
* Both boards are codependent on Game class, and forms a compostion relationship. 
* 
* */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Players[] players = new Players[2];

		int playerPick = 0;

		// Introduction to the modes
		System.out.println("Choose the player modes: ");
		System.out.println("1 - Manual Player");
		System.out.println("2 - AI Computer Player");
		playerPick = sc.nextInt();

		// Check for pick
		if (playerPick != 1 && playerPick != 2) {
			System.out.println("invalid input try again !!!");
			System.out.println("Choose Player 1:");
			System.out.println("1 - Manual Player");
			System.out.println("2 - AI Computer Player");
			sc.next();
		}

		// Check for pick
		if (playerPick == 1) {
			// Assign X to Manual (human) Player 1
			players[0] = new ManualPlayer("Player 1", "X");
		} else if (playerPick == 2) {
			// Assign X to AI Player 1
			players[0] = new AIplayer("Player 1", "X");
		}
		// Pick for player 2 mode
		System.out.println("Choose the player modes: ");
		System.out.println("1 - Manual Player");
		System.out.println("2 - AI Computer Player");
		playerPick = sc.nextInt();

		// Invalid pick
		if (playerPick != 1 && playerPick != 2) {
			System.out.println("invalid input try again");
			System.out.println("Choose Player 2:");
			System.out.println("1 - Manual Player");
			System.out.println("2 - AI Computer Player");
			sc.next();
		}

		// Check for pick
		// Set default sign O to the Player 2
		if (playerPick == 1) {
			players[1] = new ManualPlayer("Player 2", "O");
		} else {
			players[1] = new AIplayer("Player 2", "O");
		}

		// Referencing to an object of the game class
		// Not a real object just a reference to a class instance
		Game gameStart = new Game();
		gameStart.setPlayers(players[0], players[1]);
		// Game start call
		gameStart.start();
		// Final Call
		System.out.println("Game Over !!!");
	}
}