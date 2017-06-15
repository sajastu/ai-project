package players;

import hex.Board;
import hex.Move;
import hex.exceptions.BadMoveException;


public abstract class AbstractPlayer
{
	private int color;
	abstract public Move getMove(Board board) throws BadMoveException;

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
	}
}
