package hex;/*
 * Author: Zhishen Pan
 * Date: 2017-02-15
 */

public class Heuristic {
	
	/*
	 * Heuristic_function = # of row/column/diagonal AI can win -  # of row/column/diagonal player can win 
	 */
	public static int heuristic_score(Integer[] a){
		int total = 0; 
		
		//row wins
		total = total + threeValue(a[0],a[1],a[2]);
		total = total + threeValue(a[3],a[4],a[5]);
		total = total + threeValue(a[6],a[7],a[8]);
		
		//column wins
		total = total + threeValue(a[0],a[3],a[6]);
		total = total + threeValue(a[1],a[4],a[7]);
		total = total + threeValue(a[2],a[5],a[8]);
		
		//diagonal wins 
		total = total + threeValue(a[0],a[4],a[8]);
		total = total + threeValue(a[2],a[4],a[6]); 
	
		return total; 
	}
	
	//calculate number of X,Y in a given row/column/diagonal 
	public static int threeValue(int a, int b, int c){
		int sumX = 0; 
		int sumY = 0;
		
		if(a == 1)
			sumX = sumX+1; 
		else if(a == 2)
			sumY = sumY+1;
		
		if(b == 1)
			sumX = sumX+1; 
		else if(b == 2)
			sumY = sumY+1;
		
		if(c == 1)
			sumX = sumX+1; 
		else if(c == 2)
			sumY = sumY+1;
		
		//if there are 3 Y, Y wins
		if(sumX == 0 && sumY == 3)
			return -100; 
		//if there are 2 Y and no X, Y can win
		else if(sumX == 0 && sumY == 2)
			return -1; 
		//if there is 1 Y and no X, Y can win
		else if(sumX == 0 && sumY == 1)
			return -1; 
		//if there is 1 X and no Y, X can win
		else if(sumX == 1 && sumY == 0)
			return 1; 
		//if there are 2 X and no Y, X can win
		else if(sumX == 2 && sumY == 0)
			return 1; 
		//if there are 3 X, X wins
		else if(sumX == 3 && sumY == 0)
			return 100; 
		//else(either empty or full), no one has advantages. 
		else 
			return 0; 
	}	
}
