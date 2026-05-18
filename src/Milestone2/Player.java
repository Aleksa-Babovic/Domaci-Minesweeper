package Milestone2;

import Milestone1.Board;
import Milestone1.CellState;
import Milestone1.GameOutcome;

import java.util.Random;

public class Player {
    private Board board;
    private MyLinkedList moveHistory;

    public Player(Board board) {
        this.board = board;
        this.moveHistory = new MyLinkedList();
    }

    public GameOutcome playTurn() {
        int size = board.getSize();
        Random random = new Random();

        int row, col;
        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (board.getCell(row, col).getState() != CellState.HIDDEN);

        boolean wasMine = board.getCell(row, col).isMine();
        board.revealCell(row, col);

        Move move = new Move(row, col, !wasMine);
        moveHistory.insert(move);

        return board.getGameState();
    }

    public MyLinkedList getMoveHistory() { 
    	return moveHistory; 
    }
}
