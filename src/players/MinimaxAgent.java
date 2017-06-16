package players;

import hex.*;
import hex.exceptions.BadMoveException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sajad on 6/5/2017.
 */
public class MinimaxAgent extends AbstractPlayer {
    private ArrayList<Advanced_Node> finalScore;
    private Integer[] currentBoard;
    private Board currentBoardObj;
    private ArrayList<Integer> availableMoves;
    private final int N = 7;
    private final int MYTURN = 1;
    private final int OPPTURN = 2;

    @Override
    public Move getMove(Board board) throws BadMoveException {

        callMinimax(board, MYTURN);

        int minimaxMove = bestMove();

        return new Move(new Cell(minimaxMove/N, minimaxMove%N));
    }

    private void callMinimax(Board board, int turn) throws BadMoveException {
        this.currentBoardObj = board;
        finalScore = new ArrayList<>();

        //Conversion to an integer array for facilitate calculation
        board2Array(board);

        //Calling minimax function to construct the best move - Starting at depth 0
        minimax(0, turn, Integer.MIN_VALUE, Integer.MAX_VALUE);

        //print the score of the each move - (Test)
        for (Advanced_Node score : finalScore) {
            System.err.println("Point:" + (score.point + 1) + " -> Score: " + score.score);
        }

        System.err.println();

    }

    //Minimax algorithm
    private int minimax(int depth, int turn, int alpha, int beta) throws BadMoveException {

        List<Integer> availableMove = getAvailableMoves();

        //base case recursive - if there is whether a winner or a draw is captured
        if (currentBoardObj.win() == MYTURN)    return 1;
        if (currentBoardObj.win() == OPPTURN)   return -1;
        if (availableMove.isEmpty())    return 0;

        //If we reach the cutoff depth, so call the heuristic function to evaluate the score!
        if(depth == 3){
            int totalScore = 0;
            totalScore = totalScore + Heuristic.heuristic_score(currentBoard);

            //Maximizer should return positive value of total score i.e. totalScore
            if(turn == 1)   return totalScore;
            else    return -totalScore;
        }

        //Maximizer
        if (turn == 1) {
            //take alpha as a bound
            int newScoreBound = alpha;

            //For each possible move in the current state
            for (Integer anAvailableMove : availableMove) {
                int point = anAvailableMove;

                //Make the move simultaneously, both on the board array and board object
                makeMove(point, 1);
                makeMoveObj(point, 1);

                //Call the function recursively
                int currentScore = minimax(depth + 1, 2, alpha, beta);

                //If it comes back to the top, add the score to the final list
                if (depth == 0)
                    finalScore.add(new Advanced_Node(currentScore, point));

                //Update the alpha(lower bound) if currentScore is bigger
                newScoreBound = Math.max(currentScore, newScoreBound);

                //Reset the board array and object
                currentBoard[point] = 0;
                resetBoardObj(point);

                //If the calculated score is bigger than beta, so prune the game tree
                if (newScoreBound > beta)
                    return beta;
            }
            return newScoreBound;
        }

        //Minimizer
        else{
            int newBound = beta;

            //for each possible move
            for (Integer anAvailableMove : availableMove) {
                int point = anAvailableMove;
                //make the move
                makeMove(point, 2);
                makeMoveObj(point, 2);

                //recursive call
                int currentScore = minimax(depth + 1, 1, alpha, beta);

                //update the upper bound if it's lower
                newBound = Math.min(currentScore, newBound);

                //reset the board
                currentBoard[point] = 0;
                resetBoardObj(point);

                //if the score is bigger than alpha, prune tree
                if (newBound < alpha)
                    return alpha;
            }
            return newBound;
        }
    }

    private int bestMove() {
        int max = Integer.MIN_VALUE;
        int best = 0;

        //get the highest score
        for (int i = 0; i < finalScore.size(); i++) {
            if (finalScore.get(i).score > max) {
                max = finalScore.get(i).score;
                best = i;
            }
        }

        //check if there are moves with identical highest scores;
        List<Integer> index = new ArrayList<>();

        for(int i=0;i<finalScore.size();i++){
            if(finalScore.get(i).score == finalScore.get(best).score)
                index.add(i);
        }

        //if there are several moves with identical highest score, randomly pick one
        Random ran = new Random();
        best = ran.nextInt(index.size());

        return finalScore.get(index.get(best)).point;
    }

    private List<Integer> getAvailableMoves() {
        availableMoves = new ArrayList<>();
        for (int i = 0; i < N*N; ++i) {
            if (currentBoard[i] == 0)
                availableMoves.add(i);
        }
        return availableMoves;
    }

    private void resetBoardObj(int point) throws BadMoveException {
//        currentBoardObj.move(new Move(), 0);
        currentBoardObj.undo(new Cell(point/N,point%N));
    }

    private void makeMoveObj(int point, int turn) throws BadMoveException {
        currentBoardObj.move(new Move(new Cell(point/N,point%N)), turn);
    }

    private void makeMove(int point, int player) {
        currentBoard[point] = player;
    }

    private void board2Array(Board board) {
        currentBoard = new Integer[N*N];
        int k=-1;
        for (int i = 0; i < N; ++i) {
            for (int j=0; j<N; ++j){
                k++;
                currentBoard[k] = board.get(new Cell(i,j));
            }
        }
    }
}