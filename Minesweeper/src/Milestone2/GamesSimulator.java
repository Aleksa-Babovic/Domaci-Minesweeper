package Milestone2;

import Milestone1.Board;
import Milestone1.GameOutcome;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GamesSimulator {

    public static Player createBot(Board board) {
        return new Player(board);
    }

    public static void main(String[] args) {
        int totalMatches = 1000;
        int boardSize = 8;
        int numMines = 6;
        String csvFile = "matches.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("MatchId,BotType,Result,TimeMs,TotalClicks");

            for (int matchId = 1; matchId <= totalMatches; matchId++) {
                Board board = new Board(boardSize, numMines);
                Player bot = createBot(board);

                long startTime = System.currentTimeMillis();
                GameOutcome outcome = GameOutcome.IN_PROGRESS;

                while (outcome == GameOutcome.IN_PROGRESS) {
                    outcome = bot.playTurn();
                }

                long timeMs = System.currentTimeMillis() - startTime;
                String result = (outcome == GameOutcome.VICTORY) ? "VICTORY" : "DEFEAT";
                int totalClicks = bot.getMoveHistory().size();

                writer.printf("%d,RandomBot,%s,%d,%d%n", matchId, result, timeMs, totalClicks);
            }

            System.out.println("Simulacija zavrsena! " + totalMatches + " partija zapisano u " + csvFile);

        } catch (IOException e) {
            System.err.println("Greska pri pisanju CSV fajla: " + e.getMessage());
        }
    }
}