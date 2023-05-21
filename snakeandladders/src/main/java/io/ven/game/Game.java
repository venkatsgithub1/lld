package io.ven.game;

import java.util.Deque;
import java.util.LinkedList;
import java.util.UUID;

public class Game {
    private Board board;
    private Dice dice;
    private Deque<Player> playersList;
    private Player winner;

    public Game() {
        playersList = new LinkedList<>();
        dice = new Dice(1);
        addPlayers();
    }

    public void startGame() {
        initializeGame();
    }

    private void addPlayers() {
        playersList.addLast(new Player("p1", UUID.randomUUID().toString()));
        playersList.addLast(new Player("p2", UUID.randomUUID().toString()));
    }

    private void initializeGame() {
        board = new Board(10, 5, 5);
        while (winner == null) {
            Player playerTurn = playersList.removeFirst();
            playersList.addLast(playerTurn);
            int rolled = dice.rollDice();
            playerTurn.currentPosition += rolled;
            if (playerTurn.currentPosition >= (board.cells.length * board.cells.length) - 1) {
                winner = playerTurn;
                System.out.printf("io.ven.game.Player %s won the game.", winner.getName());
                continue;
            }
            Cell cell = board.getCell(playerTurn.currentPosition);
            Jump jump = cell.jump;
            if(jump != null && jump.start == playerTurn.currentPosition) {
                if (jump.end > jump.start) {
                    System.out.println("on a ladder");
                } else {
                    System.out.println("on a snake");
                }
                playerTurn.currentPosition = jump.end;
            }
        }
    }
}
