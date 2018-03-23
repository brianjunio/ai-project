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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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

							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
							}
						}
						if(startColumnValue + 1 <= 6 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue + 1;

							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
							}
						}
						
					}

					if(piece == 'N'){

						if(startColumnValue + 1 <= 6 && startRowValue + 2 <= 8){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue + 2;
							
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);
	
							}
						}
						
						if(startColumnValue - 1 >= 0 && startRowValue + 2 <= 8)
						{
							endColumnValue = startColumnValue - 1; 
							endRowValue = startRowValue + 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 2 <= 6 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue + 2; 
							endRowValue = startRowValue + 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 2 >= 0 && startRowValue + 1 <= 8){
							endColumnValue = startColumnValue - 2; 
							endRowValue = startRowValue + 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 2 <= 6 && startRowValue - 1 >= 0){
							endColumnValue = startColumnValue + 2; 
							endRowValue = startRowValue - 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 2 >= 0 && startRowValue - 1 >= 0){
							endColumnValue = startColumnValue - 2; 
							endRowValue = startRowValue - 1;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue + 1 <= 6 && startRowValue - 2 >= 0){
							endColumnValue = startColumnValue + 1; 
							endRowValue = startRowValue - 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
								value += g.rankPiece(board[endColumnValue][endRowValue]);

							}
						}

						if(startColumnValue - 1 >= 0 && startRowValue - 2 >= 0){
							endColumnValue = startColumnValue - 1; 
							endRowValue = startRowValue - 2;
							if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
								if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
						if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if(board[col][row] == 'p' || board[col][row] == 'k' || board[col][row] == 'n' || board[col][row] == 'b' || board[col][row] == 'r' )
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
		}
		System.out.println("Computer moves " +columnNames[move[0]]+ (move[1] + 1) + columnNames[newColumn] + (newRow+1));
		board[newColumn][newRow] = bestPiece;
		board[move[0]][move[1]] = '-';
	}

	public int[] assignBestMove(int[] bestMove, int startColumnValue, int startRowValue, int endColumnValue, int endRowValue){
		bestMove[0]= startColumnValue;
		bestMove[1]= startRowValue;
		bestMove[2]= endColumnValue;
		bestMove[3]= endRowValue;
		return bestMove;
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
	
	public int[] moveGenerator (char piece, char[][] board, int startColumnValue, int startRowValue){
		int[] move = new int[15];
		int[] bestMove = new int[4];
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

		if(piece == 'n'){

			if(startColumnValue + 1 <= 6 && startRowValue + 2 <= 8){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 2;
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			if(startColumnValue - 1 >= 0 && startRowValue + 2 <= 8)
			{
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 2;
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'R' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				endRowValue = startRowValue - 2;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue - 2;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue + 2; 
				endRowValue = startRowValue - 1;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue - 2; 
				endRowValue = startRowValue - 1;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}	
			}
		}

		if(piece == 'N'){

			if(startColumnValue + 1 <= 6 && startRowValue + 2 <= 8){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 2;
				
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			if(startColumnValue - 1 >= 0 && startRowValue + 2 <= 8)
			{
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 2;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 2;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue + 2; 
				endRowValue = startRowValue + 1;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}
				endColumnValue = startColumnValue - 2; 
				endRowValue = startRowValue + 1;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
					return move;
				}	
			}
			
			
			
			
		}

		if(piece == 'p'){
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue + 1;
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N')){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			endColumnValue = startColumnValue - 1; 
			endRowValue = startRowValue + 1;
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
				if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'R' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'N')){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			endColumnValue = startColumnValue; 
			endRowValue = startRowValue;
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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

			if(explosionNet <= -2 && explosionNet < g.rankPiece(capturedPiece)){
				destroyedPieces = g.explode(startColumnValue, startRowValue, endColumnValue, endRowValue, piece, board);
				move[13] = 'e';
				exploded = true;
			}
			else if(capturedPiece < 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
				move[13]='c';
				move[14]=capturedPiece;
				return move;
			}
			else{
				if(startRowValue + 1 <= 8){
					endColumnValue = startColumnValue; 
					endRowValue = startRowValue - 1;
					if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue) && board[endColumnValue][endRowValue] == '-'){
						move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
						return move;
					}
				}	
			}		
		}
		
		if(piece == 'P'){

			if(startColumnValue - 1 >= 0 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue - 1; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'r' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n') && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
				}
			}
			
			if(startColumnValue + 1 <= 6 && startRowValue + 1 <= 8){
				endColumnValue = startColumnValue + 1; 
				endRowValue = startRowValue + 1;
				if((board[endColumnValue][endRowValue] == 'p' || board[endColumnValue][endRowValue] == 'b' || board[endColumnValue][endRowValue] == 'n' || board[endColumnValue][endRowValue] == 'k' || board[endColumnValue][endRowValue] == 'r' ) && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
				}
			}
			
			endColumnValue = startColumnValue; 
			endRowValue = startRowValue;
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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
					if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue) && board[endColumnValue][endRowValue] == '-'){
						move = executeMove(move, piece, startColumnValue, startRowValue, endColumnValue, endRowValue, board);
						return move;
					}
				}	
			}		
		}
		
		if(piece == 'R'){
			//Look forward, check until encounter first piece
			for(int i = 1; i<= 8-startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + i;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
					break;	
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			
			//Look behind, check until encounter first piece
			for(int i = 1; i<= startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue - i;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}

			//Look to left, check until encounter with first piece
			for(int i = 1; i<= startColumnValue; i++){
				endColumnValue = startColumnValue - i; 
				endRowValue = startRowValue;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			//Look to right, check until encounter with first piece
			for(int i = 1; i<= 6-startColumnValue; i++){
				endColumnValue = startColumnValue + i; 
				endRowValue = startRowValue;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, startColumnValue, startRowValue)){
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
					if(board[endColumnValue][endRowValue] == '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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

		if(piece == 'r'){
			//Look behind until encounter first piece
			for(int i = 1; i<= 8-startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue - i;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					capturedPiece = board[endColumnValue][endRowValue];
					newEndColumnValue = endColumnValue;
					newEndRowValue = endRowValue;
					break;	
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			
			//Look forward until encounter first piece
			for(int i = 1; i<= startRowValue; i++){
				endColumnValue = startColumnValue; 
				endRowValue = startRowValue + i;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}

			//Look to left until encounter with first piece
			for(int i = 1; i<= startColumnValue; i++){
				endColumnValue = startColumnValue - i; 
				endRowValue = startRowValue;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			//Look to right until encounter with first piece
			for(int i = 1; i<= 6-startColumnValue; i++){
				endColumnValue = startColumnValue + i; 
				endRowValue = startRowValue;
				if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
					if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
					}
					break;
					
				}
				else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
					break;
				}
				else{
					continue;
				}
				
			}
			
			if(g.legalMove(piece, board, startColumnValue, startRowValue, startColumnValue, startRowValue)){
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

			//If a piece was captured, execute the move.
			if(explosionNet < -5 && explosionNet < g.rankPiece(capturedPiece)){
				destroyedPieces = g.explode(startColumnValue, startRowValue, startColumnValue, startRowValue, piece, board);
				exploded = true;
				move[13] = 'e';
			}
			else if(capturedPiece < 0){
				move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
				move[14] = capturedPiece;
				move[13] = 'c';
				return move;
			}
			else{
				//Move forward and check many pieces you threaten from each position. Position with the highest rank is chosen.
				for(int i = 1; i<= startRowValue; i++){
					endColumnValue = startColumnValue; 
					endRowValue = startRowValue - i;
					int j1 = endColumnValue;
					int j2 = endRowValue;

					if(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
						if(board[j1][endRowValue] == '-'){
							//check left side of row
							for(int j = 1; j<= startColumnValue; j++){
								j1 -= j; 
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
								
							}
							//check right side of row
							for(int j = 1; j<= startColumnValue; j++){
								j1 += j; 
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
								
							}	
						}
					}

					if(currentMoveScore < highestMoveScore){
						highestMoveScore = currentMoveScore;
						bestMove = assignBestMove(bestMove, startColumnValue, startRowValue, endColumnValue, endRowValue);
					}	
				}

				if(currentMoveScore != 0){
					move = executeMove(move, piece, bestMove[0], bestMove[1], bestMove[2], bestMove[3], board);
					return move;
				}
			}
		}

		if(piece == 'b' || piece == 'B'){
			
			//Checks explosion value
			if(g.legalMove(piece, board, startColumnValue, startRowValue, startColumnValue, startRowValue)){
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

			//bottom right piece capture
			for(int i = 1; i <= 6-startColumnValue; i++)
			{
				if(startColumnValue + i <= 6 && startRowValue +i <= 8){
					endColumnValue = startColumnValue + i;
					endRowValue = startRowValue + i;
					if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						capturedPiece = board[endColumnValue][endRowValue];
						newEndColumnValue = endColumnValue;
						newEndRowValue = endRowValue;
						break;	
					}
					else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
						break;
					}
					else{
						continue;
					}
				}
			}

			//bottom left piece capture
			for(int i = 1; i<= startColumnValue; i++){
				if(startColumnValue - i >= 0 && startRowValue +i <= 8){
					endColumnValue = startColumnValue - i; 
					endRowValue = startRowValue + i;
					if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;
						}
						break;
						
					}
					else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
						break;
					}
					else{
						continue;
					}
				}
			}

			//top right piece capture
			for(int i = 1; i<= 6-startColumnValue; i++){
				if(startColumnValue + i <= 6 && startRowValue -i >= 0){
					endColumnValue = startColumnValue + i; 
					endRowValue = startRowValue - i;
					if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;
						}
						break;
						
					}
					else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
						break;
					}
					else{
						continue;
					}
				}
			}

			//top left piece capture
			for(int i = 1; i<= startColumnValue; i++){
				if(startColumnValue + i <= 6 && startRowValue +i <= 8){
					endColumnValue = startColumnValue - i; 
					endRowValue = startRowValue + i;
					if(board[endColumnValue][endRowValue] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
						if(g.rankPiece(board[endColumnValue][endRowValue]) > g.rankPiece(capturedPiece)){
							capturedPiece = board[endColumnValue][endRowValue];
							newEndColumnValue = endColumnValue;
							newEndRowValue = endRowValue;
						}
						break;
						
					}
					else if(board[endColumnValue][endRowValue] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue))){
						break;
					}
					else{
						continue;
					}
				}
			}


			/*
			*  Looks at non-capture moves, checks value of each empty spot the piece can legally move to. Highest value 
			*  move is saved.
			*/

			for(int i = 1; i <= 6-startColumnValue; i++)
			{
				if(startColumnValue +i <= 6 && startRowValue +i <= 8){
					int j1 = startColumnValue + i;
					int j2 = startRowValue + i;
					endColumnValue = j1;
					endRowValue = j2;

					if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'R'))
					{
						break;
					}

					if(board[endColumnValue][endRowValue] == '-')
					{
						for(int j = 1; j <= 6-endColumnValue; j++)
						{
							if(j1 + j <= 6 && j2 +j <= 8){
								j1 += j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}

						for(int j = 1; j <= 6-startColumnValue; j++)
						{
							if(j1 - j >= 0 && j2 +j <= 8){
								j1 -= j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}

						//look bottom right
						for(int j = 1; j<= startColumnValue; j++){
							if(j1 + j <= 6 && j2 -j >= 0){
								j1 += j;
								j2 -= j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}
			
						//look bottom left
						for(int j = 1; j<= startColumnValue; j++){
							if(j1 - j >= 0 && j2 +j <= 8){
								j1 -= j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}
					}

					if(currentMoveScore > highestMoveScore){
						highestMoveScore = currentMoveScore;
						bestMove = assignBestMove(bestMove, startColumnValue, startRowValue, endColumnValue, endRowValue);
					}
				}

			}

			for(int i = 1; i <= startColumnValue; i++)
			{
				if(startColumnValue -i >= 0 && startRowValue +i <= 8){
					int j1 = startColumnValue - i;
					int j2 = startRowValue + i;
					endColumnValue = j1;
					endRowValue = j2;

					if((board[endColumnValue][endRowValue] == 'P' || board[endColumnValue][endRowValue] == 'K' || board[endColumnValue][endRowValue] == 'N' || board[endColumnValue][endRowValue] == 'B' || board[endColumnValue][endRowValue] == 'R'))
					{
						break;
					}

					if(board[endColumnValue][endRowValue] == '-')
					{
						for(int j = 1; j <= 6-endColumnValue; j++)
						{
							if(j1 + j <= 6 && j2 +j <= 8){
								j1 += j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;	
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}

						for(int j = 1; j <= 6-startColumnValue; j++)
						{
							if(j1 - j >= 0 && j2 +j <= 8){
								j1 -= j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;										
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}

						for(int j = 1; j<= startColumnValue; j++){
							if(j1 + j <= 6 && j2 -j >= 0){
								j1 += j;
								j2 -= j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;										
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}
				
						//top left
						for(int j = 1; j<= startColumnValue; j++){
							if(j1 - j >= 0 && j2 +j <= 8){
								j1 -= j;
								j2 += j;
								if(board[j1][j2] != '-' && g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2)){
									currentMoveScore += g.rankPiece(board[j1][j2]);
									break;
									
								}
								else if(board[j1][j2] != '-' && !(g.legalMove(piece, board, startColumnValue, startRowValue, j1, j2))){
									break;
								}
								else{
									continue;
								}
							}
							else
								break;
						}
					}
					
					if(piece == 'b'){
						if(currentMoveScore < highestMoveScore){
							highestMoveScore = currentMoveScore;
							bestMove = assignBestMove(bestMove, startColumnValue, startRowValue, endColumnValue, endRowValue);
						}
					}
					else{
						if(currentMoveScore > highestMoveScore){
							highestMoveScore = currentMoveScore;
							bestMove = assignBestMove(bestMove, startColumnValue, startRowValue, endColumnValue, endRowValue);
						}
					}
				}
				
			}

			//Decide which move to play based on the greatest value between the three different types of move.
			//If all values equal, play capture move.
			if(piece == 'b'){
				if(explosionNet < g.rankPiece(capturedPiece) && explosionNet < highestMoveScore && explosionNet < -4){
					destroyedPieces = g.explode(startColumnValue, startRowValue, startColumnValue, startRowValue, piece, board);
					move[13] = 'e';
					exploded = true;
				}
				else if((g.rankPiece(capturedPiece) < 0 && g.rankPiece(capturedPiece) < highestMoveScore) || (explosionNet == g.rankPiece(capturedPiece) && explosionNet == highestMoveScore && g.rankPiece(capturedPiece) == highestMoveScore)){
					move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
					move[14] = capturedPiece;				
					move[13] = 'c';
					return move;
				}
				else{
					move = executeMove(move, piece, bestMove[0], bestMove[1], bestMove[2], bestMove[3], board);
				}
			}
			else{
				if(explosionNet > g.rankPiece(capturedPiece) && explosionNet > highestMoveScore && explosionNet >= 4){
					destroyedPieces = g.explode(startColumnValue, startRowValue, startColumnValue, startRowValue, piece, board);
					move[13] = 'e';
					exploded = true;
				}
				else if((g.rankPiece(capturedPiece) > 0 && g.rankPiece(capturedPiece) > highestMoveScore) || (explosionNet == g.rankPiece(capturedPiece) && explosionNet == highestMoveScore && g.rankPiece(capturedPiece) == highestMoveScore)){
					move = executeMove(move, piece, startColumnValue, startRowValue, newEndColumnValue, newEndRowValue, board);
					move[14] = capturedPiece;				
					move[13] = 'c';
					return move;
				}
				else{
					move = executeMove(move, piece, bestMove[0], bestMove[1], bestMove[2], bestMove[3], board);
				}
			}
		}

		if(piece == 'K'){
			//finish this stuff
			if(startRowValue +1 <= 8){
				endColumnValue = startColumnValue;
				endRowValue = startRowValue + 1;
				if(g.legalMove(piece, board, startColumnValue, startRowValue, endColumnValue, endRowValue)){
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

	
}
	

	
	


