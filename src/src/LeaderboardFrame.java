import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.WebSocket;

public class LeaderboardFrame extends JFrame {
    private JTextArea leaderboardTextArea;

    /**
     * Leaderboard construction
     */
    public LeaderboardFrame() {
        setTitle("Flappy Bird");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel recordsLabel = new JLabel("RECORDS", SwingConstants.CENTER);
        recordsLabel.setForeground(Color.BLACK);
        recordsLabel.setFont(new Font("Arial", Font.BOLD, 60));
        add(recordsLabel, BorderLayout.NORTH);

        leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        leaderboardTextArea.setFont(new Font("Arial", Font.PLAIN, 55));
        leaderboardTextArea.setBackground(Color.WHITE);
        leaderboardTextArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(leaderboardTextArea);
        scrollPane.getViewport().setBackground(Color.WHITE);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);

        JButton exitButton = createButton("EXIT");
        JButton backButton = createButton("BACK TO MENU");

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(LeaderboardFrame.this, "Do you really wish to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    LeaderboardFrame.this.dispose();
                    System.exit(0);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Menu();
            }
        });

        buttonPanel.add(backButton);
        buttonPanel.add(exitButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        Listener windowListener = new Listener(this);
        addWindowListener(windowListener);

        loadLeaderboardFromFile();
        setVisible(true);
        this.setResizable(false);
    }

    /**
     * Creation of a button
     *
     * @param text Button text
     * @return New button
     */
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(200, 75));
        return button;
    }

    /**
     * Retrieves scores from a file and displays them
     */
    public void loadLeaderboardFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\HappyBirdTuhacek\\src\\src\\leaderboard.txt"))) {
            String line;
            StringBuilder leaderboardText = new StringBuilder();
            int count = 0;
            while ((line = reader.readLine()) != null && count < 5) {
                leaderboardText.append(getPlace(count + 1)).append(": ").append(line).append("\n");
                count++;
            }
            leaderboardTextArea.setText(leaderboardText.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string to make the leaderboard
     *
     * @param number Rank number
     * @return Ranking designation
     */
    public String getPlace(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return number + "th";
        }
        switch (number % 10) {
            case 1:
                return number + "st";
            case 2:
                return number + "nd";
            case 3:
                return number + "rd";
            default:
                return number + "th";
        }
    }
}
