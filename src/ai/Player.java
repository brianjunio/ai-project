package ai;

import java.util.Scanner;

public class Player {

	public Player(){
		
	}
	
	public char[] RequestMove(Scanner sc){
		System.out.print("Please enter a move: ");
		String move = sc.nextLine();
		char moveArray[] = move.toCharArray();
		return moveArray;
	}
	
	
}
