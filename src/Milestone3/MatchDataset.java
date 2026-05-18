package Milestone3;

import java.util.Comparator;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

public class MatchDataset {
    private SortedSet<MatchSummary> matches;

    public MatchDataset() {
        this.matches = new TreeSet<>();
    }

    public void add(MatchSummary match) {
        matches.add(match);
    }

    public SortedSet<MatchSummary> getMatches() { return matches; }

    public Double getAverageClicksByResult(String result) {
        return matches.stream()
                .filter(m -> m.getResult().equalsIgnoreCase(result))
                .mapToInt(MatchSummary::getTotalClicks)
                .average()
                .orElse(0.0);
    }

    public MatchSummary getMatchWithHighestClickRate() {
        Optional<MatchSummary> result = matches.stream()
                .max(Comparator.comparing(m -> {
                    long safeTime = m.getTimeMs() == 0 ? 1 : m.getTimeMs();
                    return (double) m.getTotalClicks() / safeTime;
                }));
        return result.orElse(null);
    }

    public long countByResult(String result) {
        return matches.stream()
                .filter(m -> m.getResult().equalsIgnoreCase(result))
                .count();
    }

    public MatchSummary getFastestVictory() {
        return matches.stream()
                .filter(m -> m.getResult().equalsIgnoreCase("VICTORY"))
                .findFirst()
                .orElse(null);
    }
}