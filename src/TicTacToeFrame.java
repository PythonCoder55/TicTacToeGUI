import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private JButton[][] buttons;
    private TicTacToe game;

    public TicTacToeFrame() {
        game = new TicTacToe();
        buttons = new JButton[3][3];

        JPanel boardPanel = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(row, col);
                    }
                });
                boardPanel.add(buttons[i][j]);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(TicTacToeFrame.this,
                        "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(quitButton, BorderLayout.SOUTH);

        add(mainPanel);
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void handleButtonClick(int row, int col) {
        if (!game.isGameOver() && game.makeMove(row, col)) {
            buttons[row][col].setText(game.getCurrentPlayer());
            if (game.isWin()) {
                JOptionPane.showMessageDialog(this, "Player " + game.getCurrentPlayer() + " wins!");
                int playAgain = JOptionPane.showConfirmDialog(TicTacToeFrame.this,
                        "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                else{
                    game.reset();
                    resetButtons();
                }
            } else if (game.isTie()) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                int playAgain = JOptionPane.showConfirmDialog(TicTacToeFrame.this,
                        "Would you like to play again?", "Play again?", JOptionPane.YES_NO_OPTION);
                if (playAgain == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                else{
                    game.reset();
                    resetButtons();
                }
            }
            else{
                game.switchPlayer();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Try again.");
        }
    }

    private void resetButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }
}
