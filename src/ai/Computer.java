package ai;

public class Computer {
	Game g;
	public Computer(Game game){
		g = game;
	}
	
	public int evaluateHeuristic(){
		
		return 0;
	}
	
	public int min(int depth, char[][] board){
		int bestScore = 9999;
		int score = 0;
		if(g.checkForWinner(board) == -1){
			return -9999;
		}
		if(depth == 3){
			evaluateHeuristic();
		}
		for(int col=6; col>=0; col--){
			for(int row=8; row>=0; row--){
				if(board[col][row] == 'p' || board[col][row] == 'k' || board[col][row] == 'n' || board[col][row] == 'b' || board[col][row] == 'r' ){
					moveGenerator (board[col][row], board, col, row);
				}
				score = max(depth + 1, board);
				if(score < bestScore){ 
					bestScore = score;
				}
				//undo move after score reassign
			}
		}
		return bestScore;
	}
	
	public int max(int depth, char[][] board){
		int bestScore = -9999;
		int score = 0;
		if(g.checkForWinner(board) == 1)
			return 9999;
		if(depth == 3)
			return evaluateHeuristic();
		for(int col=0; col<7; col++){
			for(int row=0; row<9; row++){
				if(board[col][row] == 'P' || board[col][row] == 'K' || board[col][row] == 'N' || board[col][row] == 'B' || board[col][row] == 'R' ){
					moveGenerator(board[col][row], board, col, row);
				}
				score = min(depth + 1, board);
				if(score > bestScore){ 
					bestScore = score;
				}
				//undo move after score reassign
			}
		}
		return bestScore;
	}
	
	
	public void makeAMove(char[][] board){
		char[] columnNames = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char bestPiece = ' ';
		int bestScore = -9999;
		int move[] = null;
		int newColumn = 0;
		int newRow = 0;
		int depth = 0;
		int score = -9999;
		for(int col=0; col<7; col++){
			for(int row=0; row<9; row++){
				if(board[col][row] == 'P' || board[col][row] == 'K' || board[col][row] == 'N' || board[col][row] == 'B' || board[col][row] == 'R' ){
					move = moveGenerator(board[col][row], board, col, row);
				}
				score = min(depth + 1, board);
				if(score > bestScore){ 
					newColumn = col;
					newRow = row;
					bestScore = score;
					bestPiece = board[col][row];
				}
				
			}
		}
		System.out.println("Computer moves " +columnNames[move[0]]+ move[1] + columnNames[newColumn] + newRow);
		board[newColumn][newRow] = bestPiece;
		board[move[0]][move[1]] = '-';
	}
	
	public int[] moveGenerator (char piece, char[][] board, int startColumnValue, int startRowValue){
		//first to return is the move that is generated. first version.
		int explosionNet = 0;
		boolean exploded = false;
		int endColumnValue =0;
		int endRowValue =0;
		char capturedPiece = 0;
		int[] move = new int[4];
		int highestMoveScore = 0;
		int currentMoveScore = 0;


		if(piece == 'N'){
			endColumnValue = startColumnValue + 1; 
			endRowValue = startRowValue + 2;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue + 2;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue + 2; 
			endRowValue = startRowValue + 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue - 2; 
			endRowValue = startRowValue + 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue + 2; 
			endRowValue = startRowValue - 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue - 2; 
			endRowValue = startRowValue - 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue + 1; 
			endRowValue = startRowValue - 2;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue - 2;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			
			//explosion net value check
			endColumnValue = startColumnValue; 
			endRowValue = startRowValue;
			
			if(legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				char expPiece = ' ';
				for(int i = -1; i <= 1; i++){
					for(int j = -1; j <= 1; j++){
						if(startColumnValue + i >= 0 && startColumnValue + i <7 && startRowValue + j >= 0 && startRowValue + j < 9){
							expPiece = board[startColumnValue + i][startRowValue + j];
							explosionNet += g.rankPiece(expPiece);
						}
					
					}
				}
				if(explosionNet >= 3){
					g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, expPiece, board);
					exploded = true;
				}
			}
			
			endColumnValue = startColumnValue + 1; 
			endRowValue = startRowValue + 2;
			if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue + 2;
			if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue + 2; 
			endRowValue = startRowValue + 1;
			if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			endColumnValue = startColumnValue - 2; 
			endRowValue = startRowValue + 1;
			if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'N';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			
			
		}
		if(piece == 'P'){
			
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue + 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'P';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			
			endColumnValue = startColumnValue + 1; 
			endRowValue = startRowValue + 1;
			if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				board[endColumnValue][endRowValue] = 'P';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}
			
			endColumnValue = startColumnValue; 
			endRowValue = startRowValue;
			
			if(legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				char expPiece = ' ';
				for(int i = -1; i <= 1; i++){
					for(int j = -1; j <= 1; j++){
						if(startColumnValue + i >= 0 && startColumnValue + i <7 && startRowValue + j >= 0 && startRowValue + j < 9){
							expPiece = board[startColumnValue + i][startRowValue + j];
							explosionNet += g.rankPiece(expPiece);
						}
					
					}
				}
				if(explosionNet >= 2){
					g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, expPiece, board);
					exploded = true;
				}
				
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + 1;
				if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue) && board[endColumnValue][endRowValue] == '-'){
					board[endColumnValue][endRowValue] = 'P';
					board[startColumnValue][startRowValue] = '-';
					move[0] = startColumnValue;
					move[1] = startRowValue;
					move[2] = endColumnValue;
					move[3] = endRowValue;
					return move;
				}
			}
		}
		
		if(piece == 'R'){
			//Look forward, check until encounter first enemy piece
			//for later optimization, choose select piece with higher rank.
			for(int i = 1; i<= 8-startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + i;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					break;	
				}
				
			}
			
			//Look behind, check until encounter first enemy piece
			for(int i = 1; i<= startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue - i;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						break;	
					}
				}
				
			}

			//Look to left, check until encounter with first enemy piece
			for(int i = 1; i<= startColumnValue; i++){
				endColumnValue = startColumnValue - i; 
				endRowValue = startRowValue;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						break;	
					}
				}
				
			}
			//Look to right, check until encounter with first enemy piece
			for(int i = 1; i<= 6-startColumnValue; i++){
				endColumnValue = startColumnValue + i; 
				endRowValue = startRowValue;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						break;	
					}
				}
				
			}

			//If a piece was captured, execute the move.
			if(capturedPiece != 0){
				board[endColumnValue][endRowValue] = 'R';
				board[startColumnValue][startRowValue] = '-';
				move[0] = startColumnValue;
				move[1] = startRowValue;
				move[2] = endColumnValue;
				move[3] = endRowValue;
				return move;
			}

			//Move forward and check many pieces you threaten on each spot. Spot with the highest rank is chosen.
			for(int i = 1; i<= 8-startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + i;
				if(board[endColumnValue][endRowValue] == '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					//check left side of row
					for(int j = 1; j<= 6-startColumnValue; j++){
						endColumnValue = startColumnValue - i; 
						endRowValue = startRowValue;
						if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n')){
							currentMoveScore += g.rankPiece(board[endColumnValue][endRowValue]);
							break;		
						}
						if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N')){
							break;
						}
						
					}
					//check right side of row
					for(int j = 1; j<= startColumnValue; j++){
						endColumnValue = startColumnValue - i; 
						endRowValue = startRowValue;
						if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n')){
							currentMoveScore += g.rankPiece(board[endColumnValue][endRowValue]);
							break;		
						}
						if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N')){
							break;
						}
						
					}	
				}
				if(currentMoveScore > highestMoveScore){
					highestMoveScore = currentMoveScore;
					move[0] = startColumnValue;
					move[1] = startRowValue;
					move[2] = endColumnValue;
					move[3] = endRowValue;
				}
				
			}
			

		}

		if(piece == 'B'){
			
		}

		
		return move;
	}
	

	
	public boolean legalMove(char piece, char[][] board, int startColumnValue, int startRowValue, int endColumnValue, int endRowValue){
		boolean isMoveInBoard = false;
		boolean isLegalStartPos = false;
		boolean isLegalEndPos = false;
		boolean isLegalPieceMove = false;
		boolean explosion = false;
					
			//If the loop resets, reset the flags//
			isMoveInBoard = false;
			isLegalStartPos = false;
			isLegalEndPos = false;
			isLegalPieceMove = false;
			explosion = false;
			
			
			/*
			 *  Determine if the move is within the board's range and using valid characters using ASCII arithmetic. Works for UPPER CASE ONLY.
			 */
			
			if(startColumnValue >= 0 && startColumnValue < 7 && startRowValue >= 0 && startRowValue < 9){
				isMoveInBoard = true;	
			}



			//Start position check.
			if(board[startColumnValue][startRowValue] != '-' && board[startColumnValue][startRowValue] == 'N' && board[startColumnValue][startRowValue] != 'P' && board[startColumnValue][startRowValue] != 'B' && board[startColumnValue][startRowValue] != 'K' && board[startColumnValue][startRowValue] != 'R'){
				isLegalStartPos = true;
			}
			//End position check.
			if((board[endColumnValue][endRowValue] != 'P' && board[endColumnValue][endRowValue] != 'R' && board[endColumnValue][endRowValue] != 'K' && board[endColumnValue][endRowValue] != 'B' && board[endColumnValue][endRowValue] != 'N') || (startColumnValue == endColumnValue && startRowValue == endRowValue)){
				isLegalEndPos = true;
			}
			
			//Explosion check
			if(startColumnValue == endColumnValue && startRowValue == endRowValue && piece != 'K'){
				explosion = true;
				return explosion;
			}
			
			/*Individual Piece check*/
			
			//Pawn check
			if(isMoveInBoard && isLegalStartPos && isLegalEndPos){
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

							/*
							 *  Computer moves made from the human perspective.
							 */
							
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
				System.out.println(isLegalPieceMove);
				return isLegalPieceMove;
		}	
			
	}

