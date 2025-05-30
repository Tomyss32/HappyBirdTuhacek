import java.io.*;
import java.util.*;

public class Leaderboard {
    private Set<Integer> scores;



    /**
     * Creates a new leaderboard instance and loads the scores from the file
     */
    public Leaderboard() {
        scores = new HashSet<>();
        loadScoresFromFile();
    }

    /**
     * Adds the score only if its new.
     *
     * @param score New score.
     */
    public void addScore(int score) {
        if (scores.add(score)) {
            saveScoreToFile();
        }
    }

    /**
     * Loads scores from a file
     */
    public void loadScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\HappyBirdTuhacek\\src\\src\\leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the score to a file
     */
    public void saveScoreToFile() {
        List<Integer> sortedScores = new ArrayList<>(scores);
        Collections.sort(sortedScores, Collections.reverseOrder());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\HappyBirdTuhacek\\src\\src\\leaderboard.txt"))) {
            for (int i = 0; i < sortedScores.size(); i++) {
                if (i >= 5) break;
                writer.write(Integer.toString(sortedScores.get(i)));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of top scores
     *
     * @return List of best 5 scores
     */
    public List<Integer> getScores() {
        List<Integer> sortedScores = new ArrayList<>(scores);
        Collections.sort(sortedScores, Collections.reverseOrder());
        return sortedScores;
    }
}
