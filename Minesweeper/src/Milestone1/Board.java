package Milestone1;

import java.util.Random;

public class Board {
    private Cell[][] grid;
    private int size;
    private int numMines;

    private static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] dy = {-1,  0,  1,-1, 1,-1, 0, 1};

    public Board(int size, int numMines) {
        if (size <= 0)
            throw new IllegalArgumentException("Size must be positive.");
        if (numMines < 0 || numMines >= size * size)
            throw new IllegalArgumentException("numMines must be non-negative and strictly less than size*size.");

        this.size = size;
        this.numMines = numMines;
        this.grid = new Cell[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Cell();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }

    private void placeMines() {
        Random random = new Random();
        int placed = 0;
        while (placed < numMines) {
            int r = random.nextInt(size);
            int c = random.nextInt(size);
            if (!grid[r][c].isMine()) {
                grid[r][c].setMine(true);
                placed++;
            }
        }
    }

    private void calculateAdjacentMines() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c].isMine()) continue;
                int count = 0;
                for (int d = 0; d < 8; d++) {
                    int nr = r + dx[d];
                    int nc = c + dy[d];
                    if (nr >= 0 && nr < size && nc >= 0 && nc < size && grid[nr][nc].isMine())
                        count++;
                }
                grid[r][c].setAdjacentMines(count);
            }
        }
    }

    public void revealCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
        	return;
        }
        if (grid[row][col].getState() != CellState.HIDDEN) {
        	return;
        }

        grid[row][col].setState(CellState.REVEALED);

        if (grid[row][col].isMine() || grid[row][col].getAdjacentMines() > 0) {
        	return;
        }

        CoordinateQueue queue = new CoordinateQueue();
        queue.enqueue(row, col);

        while (!queue.isEmpty()) {
            int[] coords = queue.dequeue();
            int r = coords[0];
            int c = coords[1];

            for (int d = 0; d < 8; d++) {
                int nr = r + dx[d];
                int nc = c + dy[d];
                if (nr >= 0 && nr < size && nc >= 0 && nc < size
                        && grid[nr][nc].getState() == CellState.HIDDEN
                        && !grid[nr][nc].isMine()) {
                    grid[nr][nc].setState(CellState.REVEALED);
                    if (grid[nr][nc].getAdjacentMines() == 0)
                        queue.enqueue(nr, nc);
                }
            }
        }
    }

    public GameOutcome getGameState() {
        boolean allSafeRevealed = true;
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Cell cell = grid[r][c];
                if (cell.isMine() && cell.getState() == CellState.REVEALED)
                    return GameOutcome.DEFEAT;
                if (!cell.isMine() && cell.getState() != CellState.REVEALED)
                    allSafeRevealed = false;
            }
        }
        return allSafeRevealed ? GameOutcome.VICTORY : GameOutcome.IN_PROGRESS;
    }

    public int getSize() { return size; }
    
    public Cell getCell(int row, int col) { return grid[row][col]; }
}