package Milestone3;

public class TestMatchesAnalysis {

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   MINESWEEPER - TELEMETRY ANALYSIS REPORT   ");
        System.out.println("==============================================\n");

        MatchDataset dataset = MatchFactory.fromCsvFile("matches.csv");

        if (dataset.getMatches().isEmpty()) {
            System.out.println("Nema podataka. Pokrenite prvo GamesSimulator.");
            return;
        }

        int total = dataset.getMatches().size();
        long victories = dataset.countByResult("VICTORY");
        long defeats = dataset.countByResult("DEFEAT");

        System.out.println(">>> OPSTE STATISTIKE <<<");
        System.out.printf("  Ukupno odigranih partija : %d%n", total);
        System.out.printf("  Pobjede                  : %d (%.1f%%)%n", victories, (victories * 100.0 / total));
        System.out.printf("  Porazi                   : %d (%.1f%%)%n", defeats, (defeats * 100.0 / total));
        System.out.println();

        System.out.println(">>> PROSJECAN BROJ KLIKOVA <<<");
        System.out.printf("  Prosjecno klikova u pobjedi : %.2f%n", dataset.getAverageClicksByResult("VICTORY"));
        System.out.printf("  Prosjecno klikova u porazu  : %.2f%n", dataset.getAverageClicksByResult("DEFEAT"));
        System.out.println();

        System.out.println(">>> NAJBRZA POBJEDA <<<");
        MatchSummary fastest = dataset.getFastestVictory();
        if (fastest != null) {
            System.out.printf("  Match ID : %d%n", fastest.getMatchId());
            System.out.printf("  Trajanje : %d ms%n", fastest.getTimeMs());
            System.out.printf("  Klikovi  : %d%n", fastest.getTotalClicks());
        } else {
            System.out.println("  Nema zabiljezene pobjede.");
        }
        System.out.println();

        System.out.println(">>> PARTIJA S NAJVISOM STOPOM KLIKOVA <<<");
        MatchSummary highestRate = dataset.getMatchWithHighestClickRate();
        if (highestRate != null) {
            long safeTime = highestRate.getTimeMs() == 0 ? 1 : highestRate.getTimeMs();
            double rate = (double) highestRate.getTotalClicks() / safeTime;
            System.out.printf("  Match ID     : %d%n", highestRate.getMatchId());
            System.out.printf("  Rezultat     : %s%n", highestRate.getResult());
            System.out.printf("  Stopa klikova: %.4f klikova/ms%n", rate);
            System.out.printf("  Klikovi      : %d | Trajanje: %d ms%n",
                    highestRate.getTotalClicks(), highestRate.getTimeMs());
        }

        System.out.println("\n==============================================");
        System.out.println("              KRAJ IZVJESTAJA                ");
        System.out.println("==============================================");
    }
}