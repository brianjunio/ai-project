package ai;

import javax.xml.stream.events.EndDocument;

public class Game {
	
	
	public Game(){
		
	}
	
	/*Prints Board*/
	public void printBoard(char[][] board){
		System.out.println();
		for(int row = 0; row < 9; row++){
			System.out.print(9 - row + "  ");
			for(int col = 0; col < 7; col++){
				System.out.print(board[col][row] + " ");
			}
			if(row == 0){
				System.out.print("  (COMPUTER)");
			}
			if(row == 8){
				System.out.print("  (HUMAN)");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("   A B C D E F G");
		
	}

	public void populateBoard(char[][] board){
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 7; col++){
				
				//pawns
				if(row==3 && (col==0 || col==2 || col==4 || col==6)){
					board[col][row]='P';
				}
				else if(row==5 && (col==0 || col==2 || col==4 || col==6)) {
					board[col][row]='p';
				}
				
				//knights
				else if(row==0 && (col==1 || col==5)){
					board[col][row]='N';
				}
				else if(row==8 && (col==1 || col==5)){
					board[col][row]='n';
				}
				
				//rooks
				else if(row==0 && (col==2 || col==4)){
					board[col][row]='R';
				}
				else if(row==8 && (col==2 || col==4)){
					board[col][row]='r';
				}
				
				//bishops
				else if((row==1 || row==2) && col==3){
					board[col][row]='B';
				}
				else if((row==6 || row==7) && col==3){
					board[col][row]='b';
				}
				//kings
				else if(row==0 && col==3){
					board[col][row]='K';
				}
				else if(row==8 && col==3){
					board[col][row]='k';
				}
				
				//empty
				else{
					board[col][row]='-';
				}
			}
		}
	}
	
	public int checkForWinner(char[][] board){
		int result = 0;
		for(int col = 0; col < 7; col++){
			for(int row = 0; row <9; row++){
				if(board[col][row] == 'K'){
					result += 1;
				}
				if(board[col][row] == 'k'){
					result += -1;
				}
			}
		}
		return result;
	}
	
	
	
	public boolean legalMove(char piece, char[][] board, int startColumnValue, int startRowValue, int endColumnValue, int endRowValue)
	{
		boolean isMoveInBoard = false;
		boolean isLegalStartPos = false;
		boolean isLegalEndPos = false;
		boolean isLegalPieceMove = false;
		boolean explosion = false;
					
		isMoveInBoard = false;
		isLegalStartPos = false;
		isLegalEndPos = false;
		isLegalPieceMove = false;
		explosion = false;
		
		
		/*
			*  Determine if the move is within the board's range and using valid characters using ASCII arithmetic. Works for UPPER CASE ONLY.
			*/
		
		if(startColumnValue >= 0 && startColumnValue < 7 && startRowValue >= 0 && startRowValue < 9 && endColumnValue >= 0 && endColumnValue < 7 && endRowValue >= 0 && endRowValue < 9){
			isMoveInBoard = true;	
		}
		else{
			return false;
		}



		//Start position check.

		if(Character.isLetter(board[startColumnValue][startRowValue])){
			isLegalStartPos = true;
		}
		
		//End position check.
		if(board[startColumnValue][startRowValue] == '-' || (Character.isLowerCase(board[startColumnValue][startRowValue]) && Character.isUpperCase(board[endColumnValue][endRowValue])) || (Character.isUpperCase(board[startColumnValue][startRowValue]) && Character.isLowerCase(board[endColumnValue][endRowValue]))){
			isLegalEndPos = true;
		}

		
		//Explosion check
		if(startColumnValue == endColumnValue && startRowValue == endRowValue && piece != 'K'){
			explosion = true;
			return explosion;
		}
		
		/*Individual Piece check*/
		
		if(isMoveInBoard && isLegalStartPos && isLegalEndPos){

			if(piece == 'p'){
				//Checks if pawns only moves up a row in same column and if there is no piece in front of it
				if(endColumnValue == startColumnValue && endRowValue == (startRowValue - 1) && board[endColumnValue][endRowValue] == '-'){
					isLegalPieceMove = true;
				}
				
				//Checks if pawn is making capture move. Checks if its moving to the correct spots and it is not moving to an empty spot or capturing a friendly piece.
				else if((endColumnValue == startColumnValue + 1 || endColumnValue == startColumnValue - 1) && endRowValue == (startRowValue - 1) && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'b' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'n'){
					isLegalPieceMove = true;

				}
				//If it fulfills neither requirement, illegal move.
				else{
					isLegalPieceMove = false;
				}
			}
			
		
			if(piece == 'n'){
				//Check if knight is moving forward, then it checks if it is not capturing any friendly pieces
					System.out.println("enter");
					if((endColumnValue == startColumnValue + 2 && endRowValue == startRowValue - 1 ) || (endColumnValue == startColumnValue + 1 && endRowValue == startRowValue - 2) || (endColumnValue == startColumnValue - 1 && endRowValue == startRowValue - 2) || (endColumnValue == startColumnValue - 2 && endRowValue == startRowValue -1) && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'r'){
						isLegalPieceMove = true;
					}
					else if((endColumnValue == startColumnValue + 2 && endRowValue == startRowValue + 1 ) || (endColumnValue == startColumnValue + 1 && endRowValue == startRowValue + 2 ) || (endColumnValue == startColumnValue - 2 && endRowValue == startRowValue + 1 ) || (endColumnValue == startColumnValue - 1 && endRowValue == startRowValue + 2 ) && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'b' && board[endColumnValue][endRowValue] != 'n' && (board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B')){
						isLegalPieceMove = true;
					}
					else{
						isLegalPieceMove = false;
					}
				
			}
			if(piece == 'b'){
				//Moving forward left
				if(startRowValue > endRowValue && startColumnValue > endColumnValue && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					for(int i = 1; i < startRowValue - endRowValue; i++){
						if(board[startColumnValue - i][startRowValue - i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving forward right
				if(startRowValue > endRowValue && startColumnValue < endColumnValue && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					System.out.println("enter");
					for(int i = 1; i <= startRowValue - endRowValue; i++){
						if(board[startColumnValue + i][startRowValue - i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving backward left
				if(startRowValue < endRowValue && startColumnValue > endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					for(int i = 1; i < endRowValue - startRowValue; i++){
						if(board[startColumnValue - i][startRowValue + i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving backward right
				if(startRowValue < endRowValue && startColumnValue < endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					for(int i = 1; i < endRowValue - startRowValue; i++){
						if(board[startColumnValue + i][startRowValue + i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
										
			}
				
			
			if(piece == 'r'){
			//Rook moving forward. Checks if there are any pieces in path. If so, it is an invalid move.
			
				if(startColumnValue == endColumnValue && startRowValue > endRowValue && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'N' && board[endColumnValue][endRowValue] != 'B')
				{
					for(int i = startRowValue-1; i > endRowValue; i-- ){
						if(board[startColumnValue][i] != '-'){
							return false;
						}		
						isLegalPieceMove = true;
					}
				}
				
				
				//Rook Moving backward. Checks if there are pieces in path and if it is a capture move. If not, it is an invalid move.
				if(startColumnValue == endColumnValue && startRowValue < endRowValue && board[endColumnValue][endRowValue] != '-' && (board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N'))
				{
					for(int i = startRowValue+1; i < endRowValue; i-- ){
						if(board[startColumnValue][i] != '-'){
							return false;
						}		
						isLegalPieceMove = true;
					}
				}
				
				//Rook moving sideways in either direction. Only moves if it is a capture.
				if(startRowValue == endRowValue && board[endColumnValue][endRowValue] != '-' && (board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N'))
				{
					//Left to right
					if(startColumnValue < endColumnValue){
						for(int i = startColumnValue + 1; i < endColumnValue; i++ ){
							if(board[i][startRowValue] != '-'){
								return false;
							}		
							isLegalPieceMove = true;
						}
					}
					
					//Right to left
					if(startColumnValue > endColumnValue){
						for(int i = startColumnValue - 1; i > endColumnValue; i-- ){
							if(board[i][startRowValue] != '-'){
								return false;
							}		
							isLegalPieceMove = true;
						}
					}
				}
				
			}
			if(piece == 'k'){
				
				//Checks if king only moves up a row in same column diagonally forward
				if((endColumnValue == startColumnValue + 1 || endColumnValue == startColumnValue - 1 || endColumnValue == startColumnValue) && endRowValue == (startRowValue - 1) && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					return true;
				}

				else{
					return false;
				}
			}

				
			if(piece == 'P'){
				//Checks if pawns only moves up a row in same column and if there is no piece in front of it
				if(endColumnValue == startColumnValue && endRowValue == (startRowValue + 1) && board[endColumnValue][endRowValue] == '-'){
					isLegalPieceMove = true;
				}
				
				//Checks if pawn is making capture move. Checks if its moving to the correct spots and it is not moving to an empty spot or capturing a friendly piece.
				else if((endColumnValue == startColumnValue + 1 || endColumnValue == startColumnValue - 1) && endRowValue == (startRowValue + 1) && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'N'){
					isLegalPieceMove = true;

				}
				//If it fulfills neither requirement, illegal move.
				else{
					isLegalPieceMove = false;
				}
			}
					
				
			if(piece == 'N'){
			//Check if knight is moving forward, then it checks if it is not capturing any friendly pieces
			
				if((endColumnValue == startColumnValue + 2 && endRowValue == startRowValue - 1 ) || (endColumnValue == startColumnValue + 1 && endRowValue == startRowValue - 2) || (endColumnValue == startColumnValue - 1 && endRowValue == startRowValue - 2) || (endColumnValue == startColumnValue - 2 && endRowValue == startRowValue -1) && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'r'){
					isLegalPieceMove = true;
				}
				else if((endColumnValue == startColumnValue + 2 && endRowValue == startRowValue + 1 ) || (endColumnValue == startColumnValue + 1 && endRowValue == startRowValue + 2 ) || (endColumnValue == startColumnValue - 2 && endRowValue == startRowValue + 1 ) || (endColumnValue == startColumnValue - 1 && endRowValue == startRowValue + 2 ) && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'b' && board[endColumnValue][endRowValue] != 'n' && (board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B')){
					isLegalPieceMove = true;
				}
				else{
					isLegalPieceMove = false;
				}
			
			}

			if(piece == 'B'){						
				
				//Moving bottom right to top left
				if(startRowValue > endRowValue && startColumnValue > endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'N' && (board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'b')){
					for(int i = 1; i < startRowValue - endRowValue; i++){
						if(board[startColumnValue - i][startRowValue + i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving bottom left to top right
				if(startRowValue > endRowValue && startColumnValue < endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'N' && (board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'b')){
					for(int i = 1; i < startRowValue - endRowValue; i++){
						if(board[startColumnValue - i][startRowValue - i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving top right to bottom left if it is a capture move
				if(startRowValue < endRowValue && startColumnValue > endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'N'){
					for(int i = 1; i < endRowValue - startRowValue; i++){
						if(board[startColumnValue - i][startRowValue + i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
				
				//Moving top left to bottom right
				if(startRowValue < endRowValue && startColumnValue < endColumnValue && board[endColumnValue][endRowValue] != '-' && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'N'){
					for(int i = 1; i < endRowValue - startRowValue; i++){
						if(board[startColumnValue + i][startRowValue + i] != '-'){
							return false;
						}	
						isLegalPieceMove = true;
					}
				}
										
			}
					
				
			if(piece == 'R'){
				//Rook moving forward. Checks if there are any pieces in path. If so, it is an invalid move.
				
				if(startColumnValue == endColumnValue && startRowValue > endRowValue && board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'N' && board[endColumnValue][endRowValue] != 'B')
				{
					for(int i = startRowValue-1; i > endRowValue; i-- ){
						if(board[startColumnValue][i] != '-'){
							return false;
						}		
						isLegalPieceMove = true;
					}
				}
				
				//Rook Moving backward. Checks if there are pieces in path and if it is a capture move. If not, it is an invalid move.
				if(startColumnValue == endColumnValue && startRowValue < endRowValue && board[endColumnValue][endRowValue] != '-' && (board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N'))
				{
					for(int i = startRowValue+1; i < endRowValue; i-- ){
						if(board[startColumnValue][i] != '-'){
							return false;
						}		
						isLegalPieceMove = true;
					}
				}
				
				//Rook moving sideways in either direction. Only moves if it is a capture.
				if(startRowValue == endRowValue && board[endColumnValue][endRowValue] != '-' && (board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N'))
				{
					//Left to right
					if(startColumnValue < endColumnValue){
						for(int i = startColumnValue + 1; i < endColumnValue; i++ ){
							if(board[i][startRowValue] != '-'){
								return false;
							}		
							isLegalPieceMove = true;
						}
					}
					
					//Right to left
					if(startColumnValue > endColumnValue){
						for(int i = startColumnValue - 1; i > endColumnValue; i-- ){
							if(board[i][startRowValue] != '-'){
								return false;
							}		
							isLegalPieceMove = true;
						}
					}
				}
			}
				
			if(piece == 'K'){
				
				//Checks if king only moves up a row in same column diagonally forward
				if((endColumnValue == startColumnValue + 1 || endColumnValue == startColumnValue - 1 || endColumnValue == startColumnValue) && endRowValue == (startRowValue - 1) && board[endColumnValue][endRowValue] != 'p' && board[endColumnValue][endRowValue] != 'r' && board[endColumnValue][endRowValue] != 'k' && board[endColumnValue][endRowValue] != 'n' && board[endColumnValue][endRowValue] != 'b'){
					return true;
				}

				else{
					return false;
				}
			}
		}
		System.out.println(isLegalPieceMove);
		return isLegalPieceMove;
	}
			
			
		
	public int rankPiece(char piece){
		int rank = 0;
		if(piece == 'K')
			rank = -10;
		else if(piece == 'R')
			rank = -5;
		else if(piece == 'B')
			rank = -4;
		else if(piece == 'N')
			rank = -3;
		else if(piece == 'P')
			rank = -2;
		else if(piece == 'k')
			rank = 10;
		else if(piece == 'r')
			rank = 5;
		else if(piece == 'b')
			rank = 4;
		else if(piece == 'n')
			rank = 3;
		else if(piece == 'p')
			rank = 2;
		else
			rank = 0;
		return rank;
	}
	
	public void restoreExplode(int startColumnValue, int startRowValue, char piece, char[] dPieces, char[][] board){
		board[startColumnValue][startRowValue] = dPieces[0];
		if(startColumnValue == 0 && startRowValue != 8 && startRowValue != 0){
			board[startColumnValue][startRowValue+1]   = dPieces[1];
			board[startColumnValue][startRowValue-1]   = dPieces[2];
			board[startColumnValue+1][startRowValue+1] = dPieces[3];
			board[startColumnValue+1][startRowValue-1] = dPieces[4];
			board[startColumnValue+1][startRowValue]   = dPieces[5];
		}
		else if(startColumnValue == 6 && startRowValue != 8 && startRowValue != 0){
			board[startColumnValue][startRowValue+1] = dPieces[1];
			board[startColumnValue][startRowValue-1] = dPieces[2];
			board[startColumnValue-1][startRowValue+1] = dPieces[3];
			board[startColumnValue-1][startRowValue-1] = dPieces[4];
			board[startColumnValue-1][startRowValue] = dPieces[5];

		}
		else if(startRowValue == 0 && startColumnValue != 0 && startColumnValue != 6){
			board[startColumnValue][startRowValue+1] = dPieces[1];
			board[startColumnValue+1][startRowValue+1] = dPieces[2];
			board[startColumnValue-1][startRowValue+1] = dPieces[3];
			board[startColumnValue+1][startRowValue] = dPieces[4];
			board[startColumnValue-1][startRowValue] = dPieces[5];
		}
		else if(startRowValue == 0 && startColumnValue == 0){
			board[startColumnValue][startRowValue+1] = dPieces[1];
			board[startColumnValue+1][startRowValue+1] = dPieces[2];
			board[startColumnValue+1][startRowValue] = dPieces[3];
		}
		else if(startRowValue == 8 && startColumnValue == 6){
			board[startColumnValue][startRowValue+1] = dPieces[1];
			board[startColumnValue-1][startRowValue+1] = dPieces[2];
			board[startColumnValue-1][startRowValue] = dPieces[3];
		}
		else if(startRowValue == 8 && startColumnValue != 0 && startColumnValue != 6){
			board[startColumnValue][startRowValue-1] = dPieces[1];
			board[startColumnValue+1][startRowValue-1] = dPieces[2];
			board[startColumnValue-1][startRowValue-1] = dPieces[3];
			board[startColumnValue+1][startRowValue] = dPieces[4];
			board[startColumnValue-1][startRowValue] = dPieces[5];
		}
		else if(startRowValue == 8 && startColumnValue == 0){
			board[startColumnValue][startRowValue-1] = dPieces[1];
			board[startColumnValue+1][startRowValue-1] = dPieces[2];
			board[startColumnValue+1][startRowValue] = dPieces[3];
		}
		else if(startRowValue == 8 && startColumnValue == 6){
			board[startColumnValue][startRowValue-1] = dPieces[1];
			board[startColumnValue-1][startRowValue-1] = dPieces[2];
			board[startColumnValue-1][startRowValue] = dPieces[3];
		}
		else{
			board[startColumnValue][startRowValue+1] = dPieces[1];
			board[startColumnValue][startRowValue-1] = dPieces[2];
			board[startColumnValue+1][startRowValue+1] = dPieces[3];
			board[startColumnValue+1][startRowValue-1] = dPieces[4];
			board[startColumnValue+1][startRowValue] = dPieces[5];
			board[startColumnValue-1][startRowValue] = dPieces[6];
			board[startColumnValue-1][startRowValue+1] = dPieces[7];
			board[startColumnValue-1][startRowValue-1] = dPieces[8];
		}
		
	}
	

	public int[] explode(int startColumnValue, int startRowValue, int endColumnValue, int endRowValue, char piece, char[][] board){
		int destroyedPieces[] = new int[9];
		/*
		 * Explosions for both human and computer. Returns an array of the pieces destroyed.
		 */
		
		if(startColumnValue == endColumnValue && startRowValue == endRowValue && (piece != 'k' || piece != 'K')){
			destroyedPieces[0] = board[startColumnValue][startRowValue] = '-';
			if(startColumnValue == 0 && startRowValue != 8 && startRowValue != 0){
				destroyedPieces[1] = board[startColumnValue][startRowValue+1];
				destroyedPieces[2] = board[startColumnValue][startRowValue-1];
				destroyedPieces[3] = board[startColumnValue+1][startRowValue+1];
				destroyedPieces[4] = board[startColumnValue+1][startRowValue-1];
				destroyedPieces[5] = board[startColumnValue+1][startRowValue]; 

				board[startColumnValue][startRowValue+1]   = '-';
				board[startColumnValue][startRowValue-1]   = '-';
				board[startColumnValue+1][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue]   = '-';

			}
			else if(startColumnValue == 6 && startRowValue != 8 && startRowValue != 0){
				destroyedPieces[1] = board[startColumnValue][startRowValue+1];
				destroyedPieces[2] = board[startColumnValue][startRowValue-1];
				destroyedPieces[3] = board[startColumnValue-1][startRowValue+1];
				destroyedPieces[4] = board[startColumnValue-1][startRowValue-1];
				destroyedPieces[5] = board[startColumnValue-1][startRowValue]; 

				board[startColumnValue][startRowValue+1] = '-';
				board[startColumnValue][startRowValue-1] = '-';
				board[startColumnValue-1][startRowValue+1] = '-';
				board[startColumnValue-1][startRowValue-1] = '-';
				board[startColumnValue-1][startRowValue] = '-';

			}
			else if(startRowValue == 0 && startColumnValue != 0 && startColumnValue != 6){
				destroyedPieces[1] = board[startColumnValue][startRowValue+1];
				destroyedPieces[2] = board[startColumnValue+1][startRowValue+1];
				destroyedPieces[3] = board[startColumnValue-1][startRowValue+1];
				destroyedPieces[4] = board[startColumnValue+1][startRowValue];
				destroyedPieces[5] = board[startColumnValue-1][startRowValue]; 

				board[startColumnValue][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue+1] = '-';
				board[startColumnValue-1][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue] = '-';
				board[startColumnValue-1][startRowValue] = '-';
			}
			else if(startRowValue == 0 && startColumnValue == 0){
				destroyedPieces[1] = board[startColumnValue][startRowValue+1];
				destroyedPieces[2] = board[startColumnValue+1][startRowValue+1];
				destroyedPieces[3] = board[startColumnValue+1][startRowValue];

				board[startColumnValue][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue] = '-';
			}
			else if(startRowValue == 8 && startColumnValue == 6){
				destroyedPieces[1] = board[startColumnValue][startRowValue+1];
				destroyedPieces[2] = board[startColumnValue-1][startRowValue+1];
				destroyedPieces[3] = board[startColumnValue-1][startRowValue];

				board[startColumnValue][startRowValue+1] = '-';
				board[startColumnValue-1][startRowValue+1] = '-';
				board[startColumnValue-1][startRowValue] = '-';
			}
			else if(startRowValue == 8 && startColumnValue != 0 && startColumnValue != 6){
				destroyedPieces[1] = board[startColumnValue][startRowValue-1];
				destroyedPieces[2] = board[startColumnValue+1][startRowValue-1];
				destroyedPieces[3] = board[startColumnValue-1][startRowValue-1];
				destroyedPieces[4] = board[startColumnValue+1][startRowValue];
				destroyedPieces[5] = board[startColumnValue-1][startRowValue]; 

				board[startColumnValue][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue-1] = '-';
				board[startColumnValue-1][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue] = '-';
				board[startColumnValue-1][startRowValue] = '-';
			}
			else if(startRowValue == 8 && startColumnValue == 0){
				destroyedPieces[1] = board[startColumnValue][startRowValue-1];
				destroyedPieces[2] = board[startColumnValue+1][startRowValue-1];
				destroyedPieces[3] = board[startColumnValue+1][startRowValue];

				board[startColumnValue][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue] = '-';
			}
			else if(startRowValue == 8 && startColumnValue == 6){
				destroyedPieces[1] = board[startColumnValue][startRowValue-1];
				destroyedPieces[2] = board[startColumnValue-1][startRowValue-1];
				destroyedPieces[3] = board[startColumnValue-1][startRowValue];

				board[startColumnValue][startRowValue-1] = '-';
				board[startColumnValue-1][startRowValue-1] = '-';
				board[startColumnValue-1][startRowValue] = '-';
			}
			else{
				destroyedPieces[1]= board[startColumnValue][startRowValue+1]; 
				destroyedPieces[2]=board[startColumnValue][startRowValue-1];
				destroyedPieces[3]=board[startColumnValue+1][startRowValue+1];
				destroyedPieces[4]=board[startColumnValue+1][startRowValue-1];
				destroyedPieces[5]=board[startColumnValue+1][startRowValue]; 
				destroyedPieces[6]=board[startColumnValue-1][startRowValue];
				destroyedPieces[7]=board[startColumnValue-1][startRowValue+1];
				destroyedPieces[8]=board[startColumnValue-1][startRowValue-1];

				board[startColumnValue][startRowValue+1] = '-';
				board[startColumnValue][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue+1] = '-';
				board[startColumnValue+1][startRowValue-1] = '-';
				board[startColumnValue+1][startRowValue] = '-';
				board[startColumnValue-1][startRowValue] = '-';
				board[startColumnValue-1][startRowValue+1] = '-';
				board[startColumnValue-1][startRowValue-1] = '-';
			}
		}
		return destroyedPieces;

	}
	
	
	public void movePiece(char[] move, char[][] board, String player){		
		int startColumnValue = move[0] - 'A'; //A-G
		int startRowValue = move[1] - '1'; //0-9
		int endColumnValue = move[2] - 'A'; //A-G
		int endRowValue = move[3] - '1'; //0-9
		if(player == "human"){
			startRowValue = 8 - startRowValue;
			endRowValue = 8 - endRowValue;
		}		
		
		char piece = board[startColumnValue][startRowValue];

		if(startColumnValue == endColumnValue && startRowValue == endRowValue && (piece != 'k' || piece != 'K')){
			explode(startColumnValue, startRowValue, endColumnValue, endRowValue, piece, board);
		}	
		/*
		 * Individual Piece check
		 */
		
		//Pawn check
		if(piece == 'p'){
			if(player == "human"){
				board[endColumnValue][endRowValue] = 'p';
				board[startColumnValue][startRowValue] = '-';
			}

		}
		
		//Knight check
		if(piece == 'n'){
			//Check if knight is moving forward, then it checks if it is not capturing any friendly pieces
			if(player == "human"){
				board[endColumnValue][endRowValue] = 'n';
				board[startColumnValue][startRowValue] = '-';
			}

		}
		
		//Bishop check
		if(piece == 'b'){
			if(player == "human"){
				board[endColumnValue][endRowValue] = 'b';
				board[startColumnValue][startRowValue] = '-';
			}

				
		}
			
		//Rook Check
		if(piece == 'r'){
			//Rook moving forward. Checks if there are any pieces in path. If so, it is an invalid move.
			if(player == "human"){
				board[endColumnValue][endRowValue] = 'r';
				board[startColumnValue][startRowValue] = '-';
			}
		}
		
		//King check
		if(piece == 'k'){
			if(player == "human"){
				board[endColumnValue][endRowValue] = 'k';
				board[startColumnValue][startRowValue] = '-';
			}
		}		
	}
}

