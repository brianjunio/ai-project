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
		boolean isLegalMove = false;
		
		System.out.println("Enter 1 to move first or 2 to move second.");
		int ans = input.nextInt();
		input.nextLine();
				
		game.populateBoard(gameBoard);
		game.printBoard(gameBoard);
		
		//Main game loop
		
		if(ans == 1){
			while(gameEnd == 0){
				moveArray = player.RequestMove(input);
				piece = gameBoard[moveArray[0]][moveArray[1]];
				isLegalMove = game.legalMove(piece, gameBoard, moveArray[0], moveArray[1], moveArray[2], moveArray[3]);
				while(!isLegalMove){
					System.out.println("Invalid Move.");
					moveArray = player.RequestMove(input);
					piece = gameBoard[moveArray[0]][moveArray[1]];
					isLegalMove = game.legalMove(piece, gameBoard, moveArray[0], moveArray[1], moveArray[2], moveArray[3]);
				}

				game.movePiece(moveArray, gameBoard, "human");
				game.printBoard(gameBoard);
				if(game.checkForWinner(gameBoard) != 0){
					gameEnd = game.checkForWinner(gameBoard);
					break;
				}				
				c.makeAMove(gameBoard);
				game.printBoard(gameBoard);
				if(game.checkForWinner(gameBoard) != 0){
					gameEnd = game.checkForWinner(gameBoard);
					break;
				}
				
			}
		}
		else{
			while(gameEnd == 0){
				c.makeAMove(gameBoard);
				game.printBoard(gameBoard);
				if(game.checkForWinner(gameBoard) != 0){
					gameEnd = game.checkForWinner(gameBoard);
					break;
				}
				moveArray = player.RequestMove(input);
				piece = gameBoard[moveArray[0]][moveArray[1]];
				isLegalMove = game.legalMove(piece, gameBoard, moveArray[0], moveArray[1], moveArray[2], moveArray[3]);
				while(!isLegalMove){
					System.out.println("Invalid Move.");
					moveArray = player.RequestMove(input);
					piece = gameBoard[moveArray[0]][moveArray[1]];
					isLegalMove = game.legalMove(piece, gameBoard, moveArray[0], moveArray[1], moveArray[2], moveArray[3]);
				}

				game.movePiece(moveArray, gameBoard, "human");
				game.printBoard(gameBoard);
				if(game.checkForWinner(gameBoard) != 0){
					gameEnd = game.checkForWinner(gameBoard);
					break;
				}				
			}
		}

		if(gameEnd == -1){
			System.out.println("Human wins!");
		}
		else{
			System.out.println("Computer wins!");
		}
		input.close();
	}
	

}
