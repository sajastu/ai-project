package hex;/*
 * Author: Sajastu
 * Date: 2017-02-15
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heuristic {

	public static ArrayList<Cell> getAdjacents(Cell cell)
	{
		ArrayList<Cell> result=new ArrayList<>();
		for (int r=cell.getR()-1;r<=cell.getR()+1;r++) if (r>=0 && r<7)
		{
			int min=(r<=cell.getR()?-1:0);
			int max=(r>=cell.getR()?+1:0);
			for (int c=cell.getC()+min;c<=cell.getC()+max;c++) if (c>=0 && c<7) if (!new Cell(r,c).equals(cell))
				result.add(new Cell(r, c));
		}
		return result;
	}

	public static int heuristic_score(Integer[] a) {
		int total = 0;
//		if (a.length < 49) {
			List<Cell> xBoeaders = new ArrayList<>();
			List<Cell> yBoeaders = new ArrayList<>();
			xBoeaders.addAll(Arrays.asList(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0), new Cell(3, 0),
					new Cell(4, 0), new Cell(5, 0), new Cell(6, 0)));
			yBoeaders.addAll(Arrays.asList(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(0, 3),
					new Cell(0, 4), new Cell(0, 5), new Cell(0, 6)));

			//row2row cells
			for (Cell c : xBoeaders) {
				ArrayList<Integer> args = new ArrayList<>();
				args.add(getIntIndex(c));
				ArrayList<Cell> adjacents_l1 = getAdjacents(c);
				for (Cell adj : adjacents_l1) {
					if (adj.getC() > c.getC()) {
						args.add(getIntIndex(adj));
						ArrayList<Cell> adjL2 = getAdjacents(adj);
						for (Cell adj2 : adjL2) {
							if (adj2.getC() > adj.getC()) {
								args.add(getIntIndex(adj2));
								ArrayList<Cell> adjL3 = getAdjacents(adj2);
								for (Cell adj3 : adjL3) {
									if (adj3.getC() > adj2.getC()) {
										args.add(getIntIndex(adj3));
										ArrayList<Cell> adjL4 = getAdjacents(adj3);
										for (Cell adj4 : adjL4) {
											if (adj4.getC() > adj3.getC()) {
												args.add(getIntIndex(adj4));
												ArrayList<Cell> adjL5 = getAdjacents(adj4);
												for (Cell adj5 : adjL5) {
													if (adj5.getC() > adj4.getC()) {
														args.add(getIntIndex(adj5));
														ArrayList<Cell> adjL6 = getAdjacents(adj5);
														for (Cell adj6 : adjL6) {
															if (adj6.getC() > adj5.getC()) {
																args.add(getIntIndex(adj6));
																total = total + seventhValue(a[args.get(0)], a[args.get(1)], a[args.get(2)]
																		, a[args.get(3)], a[args.get(4)], a[args.get(5)], a[args.get(6)]);
																args.remove(6);
															}
														}
														args.remove(5);
													}
												}
												args.remove(4);
											}
										}
										args.remove(3);
									}
								}
								args.remove(2);
							}
						}
						args.remove(1);
					}
				}
			}


			for (Cell c : yBoeaders) {
				ArrayList<Integer> args2 = new ArrayList<>();
				args2.add(getIntIndex(c));
				ArrayList<Cell> adjacents_l1 = getAdjacents(c);
				for (Cell adj : adjacents_l1) {
					if (adj.getR() > c.getR()) {
						args2.add(getIntIndex(adj));
						ArrayList<Cell> adjL2 = getAdjacents(adj);
						for (Cell adj2 : adjL2) {
							if (adj2.getR() > adj.getR()) {
								args2.add(getIntIndex(adj2));
								ArrayList<Cell> adjL3 = getAdjacents(adj2);
								for (Cell adj3 : adjL3) {
									if (adj3.getR() > adj2.getR()) {
										args2.add(getIntIndex(adj3));
										ArrayList<Cell> adjL4 = getAdjacents(adj3);
										for (Cell adj4 : adjL4) {
											if (adj4.getR() > adj3.getR()) {
												args2.add(getIntIndex(adj4));
												ArrayList<Cell> adjL5 = getAdjacents(adj4);
												for (Cell adj5 : adjL5) {
													if (adj5.getR() > adj4.getR()) {
														args2.add(getIntIndex(adj5));
														ArrayList<Cell> adjL6 = getAdjacents(adj5);
														for (Cell adj6 : adjL6) {
															if (adj6.getR() > adj5.getR()) {
																args2.add(getIntIndex(adj6));
																total = total + seventhValue(a[args2.get(0)], a[args2.get(1)], a[args2.get(2)]
																		, a[args2.get(3)], a[args2.get(4)], a[args2.get(5)], a[args2.get(6)]);
																args2.remove(6);
															}
														}
														args2.remove(5);
													}
												}
												args2.remove(4);
											}
										}
										args2.remove(3);
									}
								}
								args2.remove(2);
							}
						}
						args2.remove(1);
					}
				}
			}

//		}
		return total;
	}

	public static Integer getIntIndex(Cell c) {
		return (c.getR() * 7) + c.getC();
	}

	public static int seventhValue(int a, int b, int c, int d, int e, int f, int g) {
		int sumX = 0;
		int sumY = 0;

		if (a == 1)
			sumX = sumX + 1;
		else if (a == 2)
			sumY = sumY + 1;

		if (b == 1)
			sumX = sumX + 1;
		else if (b == 2)
			sumY = sumY + 1;

		if (c == 1)
			sumX = sumX + 1;

		else if (c == 2)
			sumY = sumY + 1;

		if (d == 1)
			sumX = sumX + 1;

		else if (d == 2)
			sumY = sumY + 1;

		if (e == 1)
			sumX = sumX + 1;

		else if (e == 2)
			sumY = sumY + 1;

		if (f == 1)
			sumX = sumX + 1;

		else if (f == 2)
			sumY = sumY + 1;

		if (g == 1)
			sumX = sumX + 1;

		else if (g == 2)
			sumY = sumY + 1;


		//if there are 7 Y, Y wins
		if (sumX == 0 && sumY == 7)
			return -100;
			//if there are 2 Y and no X, Y can win
		else if (sumX == 0 && sumY == 6)
			return -90;
			//if there is 1 Y and no X, Y can win
		else if (sumX == 0 && sumY == 5)
			return -70;
		else if (sumX == 0 && sumY == 4)
			return -55;
		else if (sumX == 0 && sumY == 3)
			return -40;
		else if (sumX == 0 && sumY == 2)
			return -25;
		else if (sumX == 0 && sumY == 1)
			return -5;


			//if there is 1 X and no Y, X can win
		else if (sumX == 1 && sumY == 0)
			return 5;
			//if there are 2 X and no Y, X can win
		else if (sumX == 2 && sumY == 0)
			return 25;
		else if (sumX == 3 && sumY == 0)
			return 40;
		else if (sumX == 4 && sumY == 0)
			return 55;
		else if (sumX == 5 && sumY == 0)
			return 70;
		else if (sumX == 6 && sumY == 0)
			return 90;
			//if there are 7 X, X wins
		else if (sumX == 7 && sumY == 0)
			return 100;

			//else(either empty or full), no one has advantages.
		else
			return 0;
	}
}
