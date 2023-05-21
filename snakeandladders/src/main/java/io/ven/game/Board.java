package io.ven.game;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;

    public Board(int boardSize, int snakes, int ladders) {
        initializeBoard(boardSize);
        addSnakesAndLadders(snakes, ladders);
    }

    private void initializeBoard(int boardSize) {
        cells = new Cell[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakesAndLadders(int snakes, int ladders) {
        while (snakes > 0) {
            snakes--;
            int snakeHead = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            Jump jump = new Jump();
            jump.start = snakeHead;
            jump.end = snakeTail;
            Cell cell = getCell(snakeHead);
            cell.jump = jump;
        }

        while (ladders > 0) {
            ladders--;
            int ladderStart = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            Jump jump = new Jump();
            jump.start = ladderStart;
            jump.end = ladderEnd;
            Cell cell = getCell(ladderStart);
            cell.jump = jump;
        }
    }

    protected Cell getCell(int position) {
        int row = position / cells.length;
        int column = position % cells.length;
        return cells[row][column];
    }
}
