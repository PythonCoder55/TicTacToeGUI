public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private String[][] board;
    private String currentPlayer;
    private int moveCount;

    public TicTacToe() {
        board = new String[ROW][COL];
        currentPlayer = "X";
        moveCount = 0;
        clearBoard();
    }

    private void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col].equals(" ")) {
            board[row][col] = currentPlayer;
            moveCount++;
            return true;
        }
        return false;
    }

    public boolean isWin() {
        return (isRowWin(currentPlayer) || isColWin(currentPlayer) || isDiagonalWin(currentPlayer));
    }

    public boolean isTie() {
        return moveCount == ROW * COL;
    }

    public boolean isGameOver() {
        return isWin() || isTie();
    }

    public void reset() {
        clearBoard();
        currentPlayer = "X";
        moveCount = 0;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    private boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin(String player) {
        for (int j = 0; j < COL; j++) {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin(String player) {
        if (((board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)))) {
            return true;
        }
        return false;
    }
}
