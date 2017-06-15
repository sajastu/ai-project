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
    private final int MYTURN = 2;
    private final int OPPTURN = 1;

    @Override
    public Move getMove(Board board) throws BadMoveException {
//        if (board.isSwapAvailable())
//            availables.add(new Swap());

        callMinimax(0, board, MYTURN);
        //pick and make the best move
        int comMove = bestMove();
        return new Move(new Cell(comMove/N, comMove%N));
    }

    public void callMinimax(int depth, Board board, int turn) throws BadMoveException {
        this.currentBoardObj = board;
        finalScore = new ArrayList<>();
        board2Array(board);
        minimax(depth, turn, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //print the score of the each move
        for(int i=0;i<finalScore.size();i++){
            System.err.println("Point:" + (finalScore.get(i).point+1) +" -> Score: "+finalScore.get(i).score);
        }
        System.err.println();

    }

    public int bestMove() {
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

    public List<Integer> getAvailableMoves() {
        availableMoves = new ArrayList<>();
        for (int i = 0; i < N*N; ++i) {
            if (currentBoard[i] == 0)
                availableMoves.add(i);
        }
        return availableMoves;
    }

    //Minimax algorithm
    public int minimax(int depth, int turn, int alpha, int beta) throws BadMoveException {
        List<Integer> availableMoves = getAvailableMoves();

        //if it reaches the roots
        if (currentBoardObj.win() == MYTURN)
            return Integer.MAX_VALUE;
        if (currentBoardObj.win() == OPPTURN)
            return Integer.MIN_VALUE;
        if (availableMoves.isEmpty())
            return 0;


        //if it reaches the cutoff depth, call the heuristic function to evaluate the score
        if(depth == 7){
            int totalvalue = 0;
            totalvalue = totalvalue + Heuristic.heuristic_score(currentBoard);

            if(turn == MYTURN)
                return totalvalue;
            else
                return -totalvalue;
        }

        //maximizing player
        if (turn == MYTURN) {
            int score = alpha;

            //for each possible move
            for (int i = 0; i < availableMoves.size(); ++i) {
                int point = availableMoves.get(i);
                //make the move
                makeMove(point, MYTURN);
                makeMoveObj(point, MYTURN);
                //recursive call
                int currentScore = minimax(depth + 1, OPPTURN, alpha, beta);

                //if it comes back to the top, add score to the final list
                if (depth == 0)
                    finalScore.add(new Advanced_Node(currentScore, point));

                //update the alpha(lower bound) if currentScore is bigger
                score = Math.max(currentScore, score);

                //reset the board
                currentBoard[point] = 0;
                resetBoardObj(point);

                //if the score is bigger than beta, prune tree
                if(score > beta)
                    return beta;
            }
            return score;
        }

        //Minimizing player
        else{
            int score = Integer.MAX_VALUE;

            //for each possible move
            for (int i = 0; i < availableMoves.size(); ++i) {
                int point = availableMoves.get(i);
                //make the move
                makeMove(point, OPPTURN);
                makeMoveObj(point, OPPTURN);
                //recursive call
                int currentScore = minimax(depth + 1, MYTURN, alpha, beta);

                //update the upper bound if it's lower
                score = Math.min(currentScore,score);

                //reset the board
                currentBoard[point] = 0;
                resetBoardObj(point);

                //if the score is bigger than beta, prune tree
                if(score > alpha)
                    return alpha;

            }
            return score;
        }
    }

    private void resetBoardObj(int point) throws BadMoveException {
//        currentBoardObj.move(new Move(), 0);
        currentBoardObj.undo(new Cell(point/N,point%N));
    }

    private void makeMoveObj(int point, int turn) throws BadMoveException {
        currentBoardObj.move(new Move(new Cell(point/N,point%N)), turn);
    }

    public void makeMove(int point, int player) {
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