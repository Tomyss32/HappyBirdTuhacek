import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class LeaderboardTest {
    private final String testFilename = "test.txt";

    @BeforeEach
    public void setUp() {
        clearFile(testFilename);
    }

    @org.junit.jupiter.api.Test
    public void testLoadScoresFromFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write("11\n7\n6\n5\n3");
        }
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.loadScoresFromFile();
        assertEquals(5, leaderboard.getScores().size());
        assertEquals(11, leaderboard.getScores().get(0));
        assertEquals(7, leaderboard.getScores().get(1));
        assertEquals(6, leaderboard.getScores().get(2));
        assertEquals(5, leaderboard.getScores().get(3));
        assertEquals(3, leaderboard.getScores().get(4));
    }

    private void clearFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
