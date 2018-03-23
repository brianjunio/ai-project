package ai;

import java.util.Scanner;

public class Player {

	public Player(){
		
	}
	
	public int[] RequestMove(Scanner sc){
		int moveInt[] = new int[4];
		System.out.print("Please enter a move: ");
		String move = sc.nextLine();
		char moveArray[] = move.toCharArray();

		moveInt[0] = moveArray[0] - 'A'; //A-G
		moveInt[1] = 8-(moveArray[1] - '1'); //0-9
		moveInt[2] = moveArray[2] - 'A'; //A-G
		moveInt[3] = 8-(moveArray[3] - '1'); //0-9
		return moveInt;
	}
	
	
}
