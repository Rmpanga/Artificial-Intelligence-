package Sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Board {

	int [][] board = new int[9][9];
	String valuePair = "";
	HashMap<String , LinkedList<Integer>> pvm = new HashMap<String , LinkedList<Integer>>();
	//ArrayList<Integer> moves = ArrayList<Integer>();
	
	public void setValuePair(String vp){
		valuePair = vp;
	}
	
	
	public boolean hasMovesRemaining(){
		LinkedList<Integer> list = pvm.get(valuePair);
		if (list != null && list.size()>0){
			System.out.println( "        " + valuePair + " still has moves ... PUSH");
			return true;
		}
		System.out.println( "        " + valuePair + " no more moves ... POP");
		return false;
	}
	
	public int getMove(String value){
		
	 LinkedList<Integer> list = pvm.get(value);
	 if (list.size() > 0){
	  int move = list.pop();
	  pvm.put(value, list);
	  return move;
	 }
	  System.out.println("     No moves left for value  .. " +  "(" + value + ")");
	  return -1;
	}
	
	
	public void printRemainingMoves(String value){
		LinkedList<Integer> list = pvm.get(value);
		System.out.println("           Remaining Moves for: " + value +  " ");
		System.out.print("               ");
		if (list !=null)
		for (int x = 0; x < list.size(); x++){
			System.out.print(list.get(x) +",");
		}
	}
	
	public void addMove(String value , int move){
	
		
		if (!pvm.containsKey(value)){
			LinkedList<Integer> list = new LinkedList<Integer>();
			list.add(move);
			pvm.put(value, list);
		}
		else {
			LinkedList<Integer> list = pvm.get(value);
			list.add(move);
			pvm.put(value, list);
		}
	}
	
	public void setBoard(int[][] board2){
		
		for (int r =0; r< 9; r++)
			for (int c =0; c < 9; c++){
				 board[r][c] = board2[r][c];
			}	
	}
	
	public int[][] getBoard(){
		
		return board;
	}
	
}
