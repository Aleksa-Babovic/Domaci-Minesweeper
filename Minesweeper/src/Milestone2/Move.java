package Milestone2;

public class Move {
    private int row;
    private int col;
    private boolean wasSafe;

    public Move(int row, int col, boolean wasSafe) {
        this.row = row;
        this.col = col;
        this.wasSafe = wasSafe;
    }

    public int getRow() { 
    	return row; 
    }
    public int getCol() { 
    	return col; 
    }
    public boolean isWasSafe() { 
    	return wasSafe; 
    }
}