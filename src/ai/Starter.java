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
		char[] moveArray = null;
		
		System.out.println("Enter 1 to move first or 2 to move second.");
		int ans = input.nextInt();
		input.nextLine();
				
		game.populateBoard(gameBoard);
		game.printBoard(gameBoard);
		
		//Main game loop.
		if(ans == 1){
			while(gameEnd == 0){
				moveArray = player.RequestMove(input);
				while(!(game.legalMove(moveArray, gameBoard, "human", -1, -1)))
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
