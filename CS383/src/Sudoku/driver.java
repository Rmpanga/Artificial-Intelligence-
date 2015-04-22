package Sudoku;

public class driver {

	
	static SudokuSolver2 ss = new SudokuSolver2();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ss.initialize();
		int count = 0;
		
		
		//System.out.println(count);
		//System.out.println("Orginal Board:  ");
		//ss.printBoard(s;);
		System.out.println();
		System.out.println();
		long startTime = System.currentTimeMillis();
		ss.solve();
		long endTime = System.currentTimeMillis();
		
		float runtime = (endTime - startTime );
		System.out.println("Took: " + runtime + " milli secs ");
		//System.out.println("x");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		
		
		//ss.solve(ss.gameBoard, ss.findPossibleValuesOfBoard(ss.gameBoard), 0, 0);
		
		
		//for (int j =0; j<=9; j++){
			
		//ss.findPossibleValuesOfBoard(ss.gameBoard);
		//System.out.println("Row 2, Column 5 : Value " + j  + " is : " + ss.isValueValid(j, 2, 5, ss.gameBoard));	
		//System.out.println(ss.checkSubSection(7,6 , 8, ss.gameBoard)); //(8, 5, ss.gameBoard));
		//System.out.println("Value 6,6 is " + ss.gameBoard[6][6]);
		//System.out.println(ss.checkValidColumn(9,0, ss.gameBoard));
	   // }
	}

}
