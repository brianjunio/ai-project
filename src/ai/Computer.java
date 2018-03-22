package ai;

public class Computer {
	Game g;
	
	public Computer(Game game){
		g = game;
	}
	
	public int evaluateHeuristic(char[][] board){
		int value = 0;
		int endColumnValue = 0;
		int endRowValue = 0;
		
		for(int startColumnValue = 0; startColumnValue <= 6; startColumnValue++){
			for(int startRowValue = 0; startRowValue <= 8; startRowValue++){
				if(board[startColumnValue][startRowValue] == 'P' || board[startColumnValue][startRowValue] == 'N' || board[startColumnValue][startRowValue] == 'B' || board[startColumnValue][startRowValue] == 'R' ){
					char piece = board[startColumnValue][startRowValue];
					char expPiece;

					/* Checks the value of doing an explosion on each piece. Adds all those values together. */

					for(int i = -1; i <= 1; i++){
						for(int j = -1; j <= 1; j++){
							if(startColumnValue + i >= 0 && startColumnValue + i <7 && startRowValue + j >= 0 && startRowValue + j < 9 && board[startColumnValue + i][startRowValue + j] != '-'){
								expPiece = board[startColumnValue + i][startRowValue + j];
								value += g.rankPiece(expPiece);
							}
						}
					}

					/* If a bishop is currently threatening an enemy piece, add that to the total value. */
					if(piece == 'B')
					{
						//moving bottom right
						for(int i = 1; i <= 6-startColumnValue; i++)
						{
							if(startColumnValue + i <= 6 && startRowValue + i <= 8){
								endColumnValue = startColumnValue + i;
								endRowValue = startRowValue + i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									piece = board[endColumnValue][endRowValue];
									value += g.rankPiece(piece);
									break;	
								}
							}
						}

						//bottom left
						for(int i = 1; i<= startColumnValue; i++){
							if(startColumnValue - i >= 0 && startRowValue + i <= 8){
								endColumnValue = startColumnValue - i; 
								endRowValue = startRowValue + i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(piece)){
										piece = board[endColumnValue][endRowValue];
										value += g.rankPiece(piece);
										break;	
									}
								}
							}
							
						}

						//top right
						for(int i = 1; i<= 6-startColumnValue; i++){
							if(startColumnValue + i <= 8 && startRowValue - i >= 0){
								endColumnValue = startColumnValue + i; 
								endRowValue = startRowValue - i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(piece)){
										piece = board[endColumnValue][endRowValue];
										value += g.rankPiece(piece);

										break;	
									}
								}
							}
						}

						//top left
						for(int i = 1; i<= startColumnValue; i++){
							if(startColumnValue - i >= 0 && startRowValue - i >= 0){
								endColumnValue = startColumnValue - i; 
								endRowValue = startRowValue - i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(piece)){
										piece = board[endColumnValue][endRowValue];
										value += g.rankPiece(piece);

										break;	
									}
								}
							}
						}
					}

					if(piece == 'P'){
						if(startColumnValue - 1 >= 0 && startRowValue + 1 <= 8)
						{
							endColumnValue = startColumnValue - 1; 
							endRowValue = startRowValue + 1;

							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
							}
						}
						if(startColumnValue + 1 <= 6 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue + 1;

							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
							}
						}
						
					}

					if(piece == 'N'){

						if(startColumnValue + 1 <= 6 && startRowValue + 2 <= 8){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue + 2;
							
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
	
							}
						}
						
						if(startColumnValue - 1 >= 0 && startRowValue + 2 <= 8)
						{
							endColumnValue = startColumnValue - 1; 
							endRowValue = startRowValue + 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 2 <= 6 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue + 2; 
							endRowValue = startRowValue + 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 2 >= 0 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue - 2; 
							endRowValue = startRowValue + 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 2 <= 6 && startRowValue - 1 >= 0){
							endColumnValue = startColumnValue + 2; 
							endRowValue = startRowValue - 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 2 >= 0 && startRowValue - 1 >= 0){
							endColumnValue = startColumnValue - 2; 
							endRowValue = startRowValue - 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 1 <= 6 && startRowValue - 2 >= 0){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue - 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 1 >= 0 && startRowValue - 2 >= 0){
							endColumnValue = startColumnValue - 1; 
							endRowValue = startRowValue - 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}
					}

					if(piece == 'R'){
						//Look forward, check until encounter first enemy piece
						for(int i = 1; i<= 8-startRowValue; i++){
							if(startRowValue + i <= 8){
								endColumnValue = startColumnValue; 
								endRowValue = startRowValue + i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									value += g.rankPiece(board[endColumnValue][endRowValue]);
									break;
								}
							}
							
						}
						
						//Look behind, check until encounter first enemy piece
						for(int i = 1; i<= startRowValue; i++){
							if(startRowValue - i >= 0){
								endColumnValue = startColumnValue; 
								endRowValue = startRowValue - i;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									value += g.rankPiece(board[endColumnValue][endRowValue]);
									break;
								}
							}
							
						}
			
						//Look to left, check until encounter with first enemy piece
						for(int i = 1; i<= startColumnValue; i++){
							if(startColumnValue - i >= 0){
								endColumnValue = startColumnValue - i; 
								endRowValue = startRowValue;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									value += g.rankPiece(board[endColumnValue][endRowValue]);
									break;
								}
							}
						}
						//Look to right, check until encounter with first enemy piece
						for(int i = 1; i<= 6-startColumnValue; i++){
							if(startColumnValue + i <= 6){
								endColumnValue = startColumnValue + i; 
								endRowValue = startRowValue;
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									value += g.rankPiece(board[endColumnValue][endRowValue]);
									break;
								}
							}
						}
					}

					if(piece == 'K')
					{
						endColumnValue = startColumnValue; 
						endRowValue = startRowValue + 1;
						if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
							value += g.rankPiece(board[endColumnValue][endRowValue]);
						}
					}
				}
			}
		}
		return value;
	}
	
	public int min(int depth, char[][] board){
		int bestScore = 9999;
		int score = 0;
		int move[] = new int[15];
		char tranPiece[];
		char dPieces[] = new char[9];
		if(g.checkForWinner(board) == -1){
			return -9999;
		}
		if(depth == 3){
			return evaluateHeuristic(board);
		}
		for(int col=6; col>=0; col--){
			for(int row=8; row>=0; row--){
				char piece = board[col][row];
				if(board[col][row] == 'P' || board[col][row] == 'K' || board[col][row] == 'N' || board[col][row] == 'B' || board[col][row] == 'R' )
				{
					move = moveGenerator(board[col][row], board, col, row);
				}
				score = max(depth + 1, board);
				if(score < bestScore){ 
					bestScore = score;
				}
				
				tranPiece = Character.toChars(move[13]);
				//undo move after score reassign
				if(tranPiece[0] == 'e'){
					for(int i = 0; i< dPieces.length; i++){
						tranPiece = Character.toChars(move[i+4]);
						dPieces[i] = tranPiece[0];
					}
					g.restoreExplode(col, row, piece, dPieces, board);
				}
				else if(tranPiece[0] == 'c'){
					tranPiece = Character.toChars(move[14]);
					board[move[0]][move[1]] = piece;
					board[move[2]][move[3]] = tranPiece[0];
				}
				else{
					board[move[0]][move[1]] = piece;
					board[move[2]][move[3]] = '-';
				}
			}
		}
		return bestScore;
	}
	
	public int max(int depth, char[][] board){
		int bestScore = -9999;
		int score = 0;
		int move[] = new int[15];
		char tranPiece[];
		char dPieces[] = new char[9];
		if(g.checkForWinner(board) == 1)
			return 9999;
		if(depth == 3)
			return evaluateHeuristic(board);
		for(int col=0; col<7; col++){
			for(int row=0; row<9; row++){
				char piece = board[col][row];
				if(board[col][row] == 'P' || board[col][row] == 'K' || board[col][row] == 'N' || board[col][row] == 'B' || board[col][row] == 'R' )
				{
					move = moveGenerator(board[col][row], board, col, row);
				}
				score = min(depth + 1, board);
				if(score > bestScore)
				{ 
					bestScore = score;
				}
				//undo move after score reassign
				tranPiece = Character.toChars(move[13]);

				if(tranPiece[0] == 'e'){
					for(int i = 0; i< dPieces.length; i++){
						tranPiece = Character.toChars(move[i+4]);
						dPieces[i] = tranPiece[0];
					}
					g.restoreExplode(col, row, piece, dPieces, board);
				}
				else if(tranPiece[0] == 'c'){
					tranPiece = Character.toChars(move[14]);
					board[move[0]][move[1]] = piece;
					board[move[2]][move[3]] = tranPiece[0];
				}
				else{
					board[move[0]][move[1]] = piece;
					board[move[2]][move[3]] = '-';
				}

			}
		}
		return bestScore;
	}
	
	
	public void makeAMove(char[][] board){
		char[] columnNames = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char bestPiece = ' ';
		int bestScore = -9999;
		int move[] = new int[15];
		int newColumn = 0;
		int newRow = 0;
		int depth = 0;
		int score = -9999;
		for(int col=0; col<7; col++){
			for(int row=0; row<9; row++){
				if(board[col][row] == 'P' || board[col][row] == 'K' || board[col][row] == 'N' || board[col][row] == 'B' || board[col][row] == 'R' )
				{
					move = moveGenerator(board[col][row], board, col, row);
				}
				score = min(depth + 1, board);
				if(score > bestScore)
				{ 
					newColumn = col;
					newRow = row;
					bestScore = score;
					bestPiece = board[col][row];
				}
				
			}
		}
		System.out.println("Computer moves " +columnNames[move[0]]+ (move[1] + 1) + columnNames[newColumn] + (newRow+1);
		board[newColumn][newRow] = bestPiece;
		board[move[0]][move[1]] = '-';
	}
	
	public int[] moveGenerator (char piece, char[][] board, int startColumnValue, int startRowValue){
		//first to return is the move that is generated. first version.
		int[] move = new int[15];
		int destroyedPieces[] = new int[8];
		int highestMoveScore = 0;
		int currentMoveScore = 0;
		int explosionNet = 0;
		int endColumnValue =0;
		int endRowValue =0;
		int newEndColumnValue = 0;
		int newEndRowValue = 0;
		boolean exploded = false;
		char capturedPiece = ' ';



		if(piece == 'N'){

			if(startColumnValue + 1 <= 6 && startRowValue + 2 <= 8){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 2;
				
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			if(startColumnValue - 1 >= 0 && startRowValue + 2 <= 8)
			{
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 2;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue + 2 <= 6 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue + 2; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue - 2 >= 0 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue - 2; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue + 2 <= 6 && startRowValue - 1 >= 0){
				endColumnValue = startColumnValue + 2; 
				endRowValue = startRowValue - 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue - 2 >= 0 && startRowValue - 1 >= 0){
				endColumnValue = startColumnValue - 2; 
				endRowValue = startRowValue - 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue + 1 <= 6 && startRowValue - 2 >= 0){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue - 2;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}

			if(startColumnValue - 1 >= 0 && startRowValue - 2 >= 0){
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue - 2;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
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
			}

			//chooses what move to make
			if(explosionNet >= 3 && explosionNet >= g.rankPiece(capturedPiece)){
				destroyedPieces = g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, piece, board);
				move[13] = 'e';
				exploded = true;
			}
			else if(capturedPiece != 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
				move[13] = 'c';
				move[14] = capturedPiece;
				return move;
			}
			else{
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 2;
				if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 2;
				if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue + 2; 
				endRowValue = startRowValue + 1;
				if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue - 2; 
				endRowValue = startRowValue + 1;
				if(!exploded && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}	
			}
			
			
			
			
		}
		
		if(piece == 'P'){

			if(startColumnValue - 1 >= 0 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			if(startColumnValue + 1 <= 6 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
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
			}

			if(explosionNet >= 2 && explosionNet >= g.rankPiece(capturedPiece)){
				destroyedPieces = g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, piece, board);
				move[13] = 'e';
				exploded = true;
			}
			else if(capturedPiece != 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
				move[13]='c';
				move[14]=capturedPiece;
				return move;
			}
			else{
				if(startRowValue + 1 <= 8){
					endColumnValue = startColumnValue; 
					endRowValue = startRowValue + 1;
					if(legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue) && board[endColumnValue][endRowValue] == '-'){
						move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
						return move;
					}
				}	
			}		
		}
		
		if(piece == 'R'){
			//Look forward, check until encounter first enemy piece
			for(int i = 1; i<= 8-startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + i;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
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
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
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
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
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
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
						break;	
					}
				}
				
			}

			//If a piece was captured, execute the move.
			if(explosionNet >= 5 && explosionNet >= g.rankPiece(capturedPiece)){
				destroyedPieces = g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, piece, board);
				exploded = true;
				move[13] = 'e';
			}
			else if(capturedPiece != 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
				move[14] = capturedPiece;
				move[13] = 'c';
				return move;
			}
			else{
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
				if(currentMoveScore != 0){
					move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
					return move;
				}
			}
		}

		if(piece == 'B'){

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
			}

			//moving bottom right
			for(int i = 1; i <= 6-startColumnValue; i++)
			{
				if(startColumnValue + i <= 6 && startRowValue +i <= 8){
					endColumnValue = startColumnValue + i;
					endRowValue = startRowValue + i;
					if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
						break;	
					}
				}
			}

			//bottom left
			for(int i = 1; i<= startColumnValue; i++){
				if(startColumnValue - i >= 0 && startRowValue +i <= 8){
					endColumnValue = startColumnValue - i; 
					endRowValue = startRowValue + i;
					if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;	
							break;	
						}
					}
				}
			}

			//top right
			for(int i = 1; i<= 6-startColumnValue; i++){
				if(startColumnValue + i <= 6 && startRowValue -i >= 0){
					endColumnValue = startColumnValue + i; 
					endRowValue = startRowValue - i;
					if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;
							break;	
						}
					}
				}
			}

			//top left
			for(int i = 1; i<= startColumnValue; i++){
				if(startColumnValue + i <= 6 && startRowValue +i <= 8){
					endColumnValue = startColumnValue - i; 
					endRowValue = startRowValue + i;
					if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') &&legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;
							break;	
						}
					}
				}
			}

			if(explosionNet > g.rankPiece(capturedPiece) && explosionNet >= 4){
				destroyedPieces = g.explode(startColumnValue, startRowValue, startColumnValue, startRowValue, piece, board);
				move[13] = 'e';
				exploded = true;
			}
			else if(g.rankPiece(capturedPiece) != 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
				move[14] = capturedPiece;				
				move[13] = 'c';
				return move;
			}

			//make a non-capture move move
			else{

				for(int i = 1; i <= 6-startColumnValue; i++)
				{
					if(startColumnValue +i <= 6 && startRowValue +i <= 8){
						int j1 = startColumnValue + i;
						int j2 = startRowValue + i;
						endColumnValue = j1;
						endRowValue = j2;
						if(board[endColumnValue][endRowValue] == '-')
						{
							for(int j = 1; j <= 6-endColumnValue; j++)
							{
								if(j1 + j <= 6 && j2 +j <= 8){
									endColumnValue = j1 + j;
									endRowValue = j2 + j;
									if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
										capturedPiece = board[endColumnValue][endRowValue];
										newEndColumnValue = endColumnValue;
										newEndRowValue = endRowValue;
										break;	
									}
									
									if(board[endColumnValue][endRowValue] != '-')
										break;
								}
							}

							for(int j = 1; j <= 6-startColumnValue; j++)
							{
								if(j1 - j >= 0 && j2 +j <= 8){
									endColumnValue = j1 - j;
									endRowValue = j2 + j;
									if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
										if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
											capturedPiece = board[endColumnValue][endRowValue];
											newEndColumnValue = endColumnValue;
											newEndRowValue = endRowValue;
											break;	
										}
									}

									if(board[endColumnValue][endRowValue] != '-')
										break;
								}
							}

							for(int j = 1; j<= startColumnValue; j++){
								if(j1 + j <= 6 && j2 -j >= 0){
									endColumnValue = j1 + j;
									endRowValue = j2 - j;
									if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
										if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
											capturedPiece = board[endColumnValue][endRowValue];
											newEndColumnValue = endColumnValue;
											newEndRowValue = endRowValue;
											break;	
										}	
									}

									if(board[endColumnValue][endRowValue] != '-')
										break;
								}
							}
				
							//top left
							for(int j = 1; j<= startColumnValue; j++){
								if(j1 - j >= 0 && j2 +j <= 8){
									endColumnValue = j1 - j;
									endRowValue = j2 + j;
									if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
										if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
											capturedPiece = board[endColumnValue][endRowValue];
											newEndColumnValue = endColumnValue;
											newEndRowValue = endRowValue;
											break;	
										}
									}

									if(board[endColumnValue][endRowValue] != '-')
										break;
								}
							}
						}
						if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'R'))
						{
							break;
						}
					}
				}

				for(int i = 1; i <= startColumnValue; i++)
				{
					int j1 = startColumnValue - i;
					int j2 = startRowValue + i;
					endColumnValue = j1;
					endRowValue = j2;
					if(board[endColumnValue][endRowValue] == '-')
					{
						for(int j = 1; j <= 6-endColumnValue; j++)
							{
								if(j1 + j <= 6 && j2 +j <= 8){
									endColumnValue = j1 + j;
									endRowValue = j2 + j;
									if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
										capturedPiece = board[endColumnValue][endRowValue];
										newEndColumnValue = endColumnValue;
										newEndRowValue = endRowValue;
										break;	
									}
									
									if(board[endColumnValue][endRowValue] != '-')
										break;
								}
							}

						for(int j = 1; j <= 6-startColumnValue; j++)
						{
							if(j1 - j >= 0 && j2 +j <= 8){
								endColumnValue = j1 - j;
								endRowValue = j2 + j;
								if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
										capturedPiece = board[endColumnValue][endRowValue];
										newEndColumnValue = endColumnValue;
										newEndRowValue = endRowValue;
										break;	
									}
								}

								if(board[endColumnValue][endRowValue] != '-')
									break;
							}
						}

						for(int j = 1; j<= startColumnValue; j++){
							if(j1 + j <= 6 && j2 -j >= 0){
								endColumnValue = j1 + j;
								endRowValue = j2 - j;
								if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
										capturedPiece = board[endColumnValue][endRowValue];
										newEndColumnValue = endColumnValue;
										newEndRowValue = endRowValue;
										break;	
									}	
								}

								if(board[endColumnValue][endRowValue] != '-')
									break;
							}
						}
				
						//top left
						for(int j = 1; j<= startColumnValue; j++){
							if(j1 - j >= 0 && j2 +j <= 8){
								endColumnValue = j1 - j;
								endRowValue = j2 + j;
								if(board[endColumnValue][endRowValue] != '-' && legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
									if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
										capturedPiece = board[endColumnValue][endRowValue];
										newEndColumnValue = endColumnValue;
										newEndRowValue = endRowValue;
										break;	
									}
								}

								if(board[endColumnValue][endRowValue] != '-')
									break;
							}
						}
					}
				}
				move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
			}
		}

		if(piece == 'K'){
			//finish this stuff
			if(startRowValue +1 <= 8){
				endColumnValue = startColumnValue;
				endRowValue = startRowValue + 1;
				if(legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
				}
			}
			
		}

		if(exploded == true){
			for(int i = 0; i<destroyedPieces.length; i++){
				move[i+4] = destroyedPieces[i];
			}
		}

		return move;
	}

	public int[] executeMove(int[] move, char piece, int startColumnValue, int startRowValue, int endColumnValue, int endRowValue, char[][] board)
	{
		board[endColumnValue][endRowValue] = piece;
		board[startColumnValue][startRowValue] = '-';
		move[0] = startColumnValue;
		move[1] = startRowValue;
		move[2] = endColumnValue;
		move[3] = endRowValue;
		return move;

	}
	

	
	public boolean legalMove(char piece, char[][] board, int startColumnValue, int startRowValue, int endColumnValue, int endRowValue)
	{
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
			
			if(startColumnValue >= 0 && startColumnValue < 7 && startRowValue >= 0 && startRowValue < 9 && endColumnValue >= 0 && endColumnValue < 7 && endRowValue >= 0 && endRowValue < 9){
				isMoveInBoard = true;	
			}
			else{
				return false;
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
				System.out.println(isLegalPieceMove);
				return isLegalPieceMove;
			}
			return isLegalPieceMove;
		}	
			
	}


