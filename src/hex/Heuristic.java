package hex;/*
 * Author: Sajastu
 * Date: 2017-02-15
 */

public class Heuristic {

	public static int heuristic_score(Integer[] a){
		int total = 0;
		//row & col wins
		for (int i=0; i<7; i++){
			total = total + seventhValue(a[i*7],a[i*7+1],a[i*7+2],a[i*7+3],a[i*7+4],a[i*7+5],a[i*7+6]);
			total = total + seventhValue(a[i],a[i+7],a[i+14],a[i+21],a[i+28],a[i+35],a[i+42]);
		}
		//diagonal wins
		total = total + seventhValue(a[0],a[8],a[16],a[24],a[32],a[40],a[48]);
		total = total + seventhValue(a[6],a[12],a[18],a[24],a[30],a[36],a[32]);

		return total; 
	}
	
	//calculate number of X,Y in a given row/column/diagonal 
	public static int seventhValue(int a, int b, int c, int d, int e, int f, int g){
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

		if(d== 1)
			sumX = sumX+1;

		else if(d == 2)
			sumY = sumY+1;

		if(e == 1)
			sumX = sumX+1;

		else if(e == 2)
			sumY = sumY+1;

		if(f == 1)
			sumX = sumX+1;

		else if(f == 2)
			sumY = sumY+1;

		if(g == 1)
			sumX = sumX+1;

		else if(g == 2)
			sumY = sumY+1;


		//if there are 7 Y, Y wins
		if(sumX == 0 && sumY == 7)
			return -100; 
		//if there are 2 Y and no X, Y can win
		else if(sumX == 0 && sumY == 6)
			return -90;
		//if there is 1 Y and no X, Y can win
		else if(sumX == 0 && sumY == 5)
			return -70;
		else if(sumX == 0 && sumY == 4)
			return -55;
		else if(sumX == 0 && sumY == 3)
			return -40;
		else if(sumX == 0 && sumY == 2)
			return -25;
		else if(sumX == 0 && sumY == 1)
			return -5;



			//if there is 1 X and no Y, X can win
		else if(sumX == 1 && sumY == 0)
			return 5;
		//if there are 2 X and no Y, X can win
		else if(sumX == 2 && sumY == 0)
			return 25;
		else if(sumX == 3 && sumY == 0)
			return 40;
		else if(sumX == 4 && sumY == 0)
			return 55;
		else if(sumX == 5 && sumY == 0)
			return 70;
		else if(sumX == 6 && sumY == 0)
			return 90;
			//if there are 3 X, X wins
		else if(sumX == 7 && sumY == 0)
			return 100;

		//else(either empty or full), no one has advantages. 
		else 
			return 0; 
	}	
}
