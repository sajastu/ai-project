package run;


import hex.exceptions.BadMoveException;
import players.AbstractPlayer;
import players.MinimaxAgent;
import players.RandomPlayer;

public class Main
{
	public static void main(String[] args) throws BadMoveException, InterruptedException
	{
		AbstractPlayer p1 = new MinimaxAgent();
		AbstractPlayer p2 = new RandomPlayer();
		Game g = new Game(p2, p1);
		g.start();
	}
}
