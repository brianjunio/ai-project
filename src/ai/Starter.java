package ai;
import java.util.Scanner;


public class Starter {

	public static void main(String[] args) {
		char[][] gameBoard = new char[7][9];
		int gameEnd = 0;
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		Player player = new Player();
		Computer c = new Computer(game);
		int[] moveArray = null;
		char piece = ' ';
		
		System.out.println("Enter 1 to move first or 2 to move second.");
		int ans = input.nextInt();
		input.nextLine();
				
		game.populateBoard(gameBoard);
		game.printBoard(gameBoard);
		
		//Main game loop.
		if(ans == 1){
			while(gameEnd == 0){
				moveArray = player.RequestMove(input);
				piece = gameBoard[moveArray[0]][moveArray[1]];
				while(!(game.legalMove(piece, gameBoard, moveArray[0], moveArray[1], moveArray[2], moveArray[3])));
				{
					System.out.println("Invalid move.");
					moveArray = player.RequestMove(input);
				}
				game.movePiece(moveArray, gameBoard, "human");
				game.printBoard(gameBoard);
				game.checkForWinner(gameBoard);
				c.makeAMove(gameBoard);
				game.printBoard(gameBoard);
				game.checkForWinner(gameBoard);
				//ask for move
				//check game over
				//make move
				//check game over
			}
		}
		else{
			while(gameEnd == 0){
				//make move
				//check game over
				//ask for move
				//check game over
			}
		}
		input.close();
	}
	

}
