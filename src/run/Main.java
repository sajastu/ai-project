package run;


import hex.exceptions.BadMoveException;
import players.AbstractPlayer;
import players.MinimaxAgent;
import players.RandomPlayer;

public class Main
{
	public static void main(String[] args) throws BadMoveException, InterruptedException
	{
		AbstractPlayer p2 = new MinimaxAgent();
		AbstractPlayer p1 = new RandomPlayer();
		Game g = new Game(p1, p2);
		g.start();
	}
}
