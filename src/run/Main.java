package run;


import hex.exceptions.BadMoveException;
import players.AbstractPlayer;
import players.MinimaxAgent;
import players.RandomPlayer;

public class Main
{
	public static void main(String[] args) throws BadMoveException, InterruptedException
	{
		AbstractPlayer p1 = new MinimaxAgent();	//RED
		AbstractPlayer p2 = new RandomPlayer();	//BLUE
		Game g = new Game(p1, p2);
		g.start();
	}
}
