package Sudoku;

import java.util.HashMap;
import java.util.LinkedList;

public class SudokuSolver2 {
	
	LinkedList<Board> gameStack = new LinkedList<Board> ();
	
	Board intialBoard = new Board();

	boolean solution = false;
	int valueChanges = 0;
	
	
	public void solve(){
		Board currentBoard = gameStack.peek();
		
		printBoard(currentBoard.getBoard());
		int s = 0;
		int count = 0;
		
	main:	while (!solution){
		s++;
			Board alteredBoard  = solver2helper(currentBoard);
			
			if (alteredBoard == null){ //i tried all the moves
				
				System.out.println("Backtracking.....");
				System.out.println("Stack size is .." + gameStack.size());
				
			   currentBoard = gameStack.pop();
			   if (currentBoard.hasMovesRemaining())
				   gameStack.push(currentBoard);
			   
			   	continue main; 
			}
			if (alteredBoard != null){
			 HashMap<String , LinkedList<Integer>> alteredPVM = findPossibleValuesOfBoard(alteredBoard.getBoard());//(HashMap<String , LinkedList<Integer>>) temp[1];
			 
			 if (alteredPVM == null){  //The move you made is incorrect try another one
					System.out.println("Forward Checking / BackTracking.....");
					 continue main; 
			 }
			 
			 currentBoard = alteredBoard;
			 alteredBoard.pvm = alteredPVM;
			 testing(currentBoard.board);
			 gameStack.push(alteredBoard);
			}
		}
		
		System.out.println(" Value Changes: " + valueChanges);
		System.out.println("While loop call " + s);
	}
	
	
	
	
	public Board solver2helper(Board gameBoard){
		
		int [][] board = new int[9][9];
		           board = copyBoard( board , gameBoard.getBoard());
		           
		           
		           
		           
		  // alteredBoard = setBoard(alteredBoard, board1);
			for (int r = 0; r < 9; r++)
		 		for (int c = 0; c < 9; c++){
					if (board[r][c] == 0){
						 String valuePair = Integer.toString(r) +"," +Integer.toString(c);
						 int move = gameBoard.getMove(valuePair);
						 System.out.println("          " + valuePair + "  has gotten move " + move);
						 gameBoard.printRemainingMoves(valuePair);
						 System.out.println();
						 System.out.println( "         Altering value: " + valuePair);
						 if (move != -1){
							 System.out.println("            ^^ to " + move);
							 Board b = new Board();
							 if(isValueValid(move,r,c, board)){
							 board[r][c] = move;  //Should be valid move
							 valueChanges++;
							 b.setBoard(board);
							 b.setValuePair(valuePair);
							 return b;
							 }else System.out.println("      VERY BAD LOGIC!!!!");
							 	 
						 }
						 else if (move == -1){
							System.out.println("     Error Can't Alter no more moves available");
							return null;
						 }
						 
					}
				}
			
			 //System.out.println("      Do you ever get here !!!!");
			 solution = true;
			return null;
		}
	
	
	
	

	
	
	
	
	public void initialize(){
		
		int[][] gameBoard = new int[9][9];
		  gameBoard[0][1] = 9;
		  gameBoard[0][7] = 2;
		  gameBoard[0][8] = 5;
		
		  gameBoard[1][0] = 4;
		  gameBoard[1][2] = 5;
		  gameBoard[1][3] = 2;
		  
		  gameBoard[2][1] = 6;
		  gameBoard[2][4] = 3;
		  
		  gameBoard[3][0] = 2;
		  gameBoard[3][1] = 8;
		  gameBoard[3][3] = 3;
		  gameBoard[3][6] = 1;
		  gameBoard[3][8] = 9;
		  
		  gameBoard[4][3] = 8;
		  gameBoard[4][5] = 1;
		  
		  gameBoard[5][0] = 3;
		  gameBoard[5][2] = 7;
		  gameBoard[5][5] = 6;
		  gameBoard[5][7] = 4;
		  gameBoard[5][8] = 8;
		  
		  gameBoard[6][4] = 1;
		  gameBoard[6][7] = 8;
		  
		  gameBoard[7][5] = 3;
		  gameBoard[7][6] = 7;
		  gameBoard[7][8] = 2;
		  
		  gameBoard[8][0] = 6;
		  gameBoard[8][1] = 3;
		  gameBoard[8][7] = 9;
		  
		  
		  intialBoard.setBoard(gameBoard);
		  intialBoard.pvm = findPossibleValuesOfBoard(gameBoard);
		  intialBoard.setValuePair("0,0");
		  
		  gameStack.push(intialBoard);
		  
		}
	
	public HashMap<String , LinkedList<Integer>> findPossibleValuesOfBoard(int [][] board1){
		HashMap<String , LinkedList<Integer>> pVM = new HashMap<String , LinkedList<Integer>>();
		
		for (int r =0; r< 9; r++)
			for (int c =0; c < 9; c++){
				if (board1[r][c] == 0){
					String valuePair = Integer.toString(r) +"," + Integer.toString(c);
					for (int x =1; x<=9; x++){
						if (checkValidColumn(x, c, board1) && checkValidRow(x, r, board1 ) && checkSubSection( x, r, c, board1)){
							//System.out.println("                ValuePair :" + valuePair + " has possible values...... " + x);
							//System.out.print("  " + x);
								//System.out.print(" " + x); 
							//Adding x to the pvM
							if (!pVM.containsKey(valuePair)){
								LinkedList<Integer> pValues = new LinkedList<Integer>();
								pValues.push(x);
								pVM.put(valuePair, pValues);
							} else {
								LinkedList<Integer> values = pVM.get(valuePair);
								if (!values.contains(x)){ //Being overly cautious
									values.push(x);
									pVM.put(valuePair, values);
								}
							} 
							 
							//possibleValueMappings.put(va, value)
						} 
					if (x == 9){
						//System.out.println();
						if (!pVM.containsKey(valuePair)){
							System.out.println( "     ERROR ValuePair:  " + valuePair + " has no possible values"  );
							return null;
						}
						
					} 
						//System.out.println();
					}
				
				}
			} 
		
		return pVM;
	}
	
	
	
	
	public boolean isValueValid(int ps , int row, int column , int[][] board){
		
		return (checkValidColumn(ps, column, board) && checkValidRow(ps, row, board ) && checkSubSection( ps, row, column, board));
	}
	
	
	
	/** Checking column for duplicates **/
	
	public boolean checkValidColumn(int ps , int column , int[][] board){
		
		for (int r = 0; r < 9; r++){
			
			if (board[r][column] == ps)
				return false;	
		}
		
		return true; 
	}
	
	/** Checking row for duplicates **/
	public boolean checkValidRow(int ps , int row , int[][] board){
		
		for (int c = 0; c < 9; c++){
			if (board[row][c] == ps){
				//System.out.println(board[row][c] + " vs " + ps + " at position " +"(" + row +"," + c + ")");
				return false;	
			}
		}
		return true; 
	}
	
	
	
	
	
	
	
	/**SubSection Checker Methods **/
	
	public boolean checkSubSection ( int pv, int row , int column  , int[][] board){
		//row = row +1;
		//column = column +1;
		if (row%3 == 0 ){
			//System.out.println("row % 3 = 0");
			return (checker (row , column - getColumnLocalLocation(column) , pv , board));
		}
		
		if (row%3 == 1 ){
		//	System.out.println("row % 3 = 1");
			return (checker (row-1 , column - getColumnLocalLocation(column) , pv , board));
			
		}
		
		else{  //if (row%3 == 2 )
			//System.out.println("row % 3 = 2");
			return (checker (row-2 , column - getColumnLocalLocation(column) , pv , board));
		//}
		}
		
	}
	
	private int getColumnLocalLocation(int column){
		
		if (column % 3 == 0){
			//System.out.println("c % 3 = 0");
			return 0;
		}
		if (column % 3 == 1){
		//	System.out.println("c % 3 = 1");
			return 1;
		}
		else {  // column %3 == 2
		//	System.out.println("c % 3 = 2");
			return 2;
		}
	}
		
	
	private boolean checker(int row , int column , int pv , int[][] board){
		//System.out.println("Starting at " + row  +"," + column);
		
		
		
		for (int r = row; r < row +3; r++)
			for (int c = column; c < column +3; c++){
				if (board[r][c] == pv ){
					//System.out.println("Failed at "+ r  +"," + c + " whose value is " + board[r][c]);
					return false;
				}
				
			}
		return true;
	}
	
	
   public int[][] copyBoard(int[][] board1 , int [][] board2){
		
		for (int r =0; r< 9; r++)
			for (int c =0; c < 9; c++){
				 board1[r][c] = board2[r][c];
			}	
		
		return board1;
	}
   
	public void printBoard(int[][] board){
		for (int r = 0; r < 9; r++){
			if (r%3 == 0 && r!=0){
				System.out.println();
			}
			for(int c = 0; c < 9; c++){
				if (c == 0)
					System.out.println();
				
				
				if (c!=0 && c%3 == 0){
					System.out.print(" | ");
				}
				System.out.print("[" + board[r][c] +"] ");
				
			}
		}
		
	}
	
	private void testing(int[][] board){
		 System.out.println();
		 System.out.println("        Altered Board: ");
		 printBoard(board);
		 System.out.println();
	}
		
}
