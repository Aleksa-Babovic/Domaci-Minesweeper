package Milestone3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MatchFactory {

    public static MatchSummary fromCsvLine(String line) {
        String[] parts = line.split(",");
        int matchId = Integer.parseInt(parts[0].trim());
        String botType = parts[1].trim();
        String result = parts[2].trim();
        long timeMs = Long.parseLong(parts[3].trim());
        int totalClicks = Integer.parseInt(parts[4].trim());
        return new MatchSummary(matchId, botType, result, timeMs, totalClicks);
    }

    public static MatchDataset fromCsvFile(String filePath) {
        MatchDataset dataset = new MatchDataset();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }
                if (!line.trim().isEmpty())
                    dataset.add(MatchFactory.fromCsvLine(line));
            }
        } catch (IOException e) {
            System.err.println("Greska pri citanju CSV fajla: " + e.getMessage());
        }
        return dataset;
    }
}