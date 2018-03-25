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
		
		while(!(moveInt[0] >= 0) || !(moveInt[0] < 7) || !(moveInt[1] >= 0) || !(moveInt[1] < 9) || !(moveInt[2] >= 0) || !(moveInt[2] < 7) || !(moveInt[3] >= 0) || !(moveInt[3] < 9)){
			System.out.println("Invalid Move.");
			System.out.print("Please enter a move: ");
			move = sc.nextLine();
			moveArray = move.toCharArray();
			moveInt[0] = moveArray[0] - 'A'; //A-G
			moveInt[1] = 8-(moveArray[1] - '1'); //0-9
			moveInt[2] = moveArray[2] - 'A'; //A-G
			moveInt[3] = 8-(moveArray[3] - '1'); //0-9
		}
		return moveInt;
	}
	
	
}
