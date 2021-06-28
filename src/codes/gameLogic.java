package codes;

import javax.swing.*;

public class gameLogic {
    private states playTurn = states.PLAYER1;
    private final states[][] boardStates = new states[6][7];
    private final int ROWS = 6;
    private final int COLUMNS = 7;

    public gameLogic() {
        initializeState();
    }

    private void initializeState() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                this.boardStates[i][j] = states.EMPTY;
            }
        }
    }

    public void updateState(int row, int column, states player) {
        boardStates[row][column] = player;
    }

    public void changePlayerTurn() {
        this.playTurn = whoseTurn() == states.PLAYER1 ? states.PLAYER2 : states.PLAYER1;
    }

    public states whoseTurn() {
        return playTurn;
    }


    public boolean isValidMove(int column) {
        return findLowestRow(column) != -1;
    }

    public int findLowestRow(int column) {
        for (int i = 5; i >= 0; i--)
            if (isItEmpty(i, column))
                return i;
        return -1;
    }

    private boolean isItEmpty(int row, int column) {
        return boardStates[row][column].equals(states.EMPTY);
    }

    public void checkScores() {
        if (checkWinner() || checkTie())
            finishTheGame();
    }

    public boolean checkWinner() {
        return verticalWinner() || horizontalWinner() || diagonalsWinner();
    }

    private boolean verticalWinner() {
        for (int i = 0; i < ROWS - 3; i++)
            for (int j = 0; j < COLUMNS; j++) {
                states player = boardStates[i][j];
                if (!player.equals(states.EMPTY))
                    if (boardStates[i + 1][j] == player && boardStates[i + 2][j] == player && boardStates[i + 3][j] == player)
                        return true;
            }
        return false;
    }

    private boolean horizontalWinner() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS - 3; j++) {
                states player = boardStates[i][j];
                if (!player.equals(states.EMPTY))
                    if (boardStates[i][j + 1] == player && boardStates[i][j + 2] == player && boardStates[i][j + 3] == player)
                        return true;
            }
        return false;
    }

    private boolean diagonalsWinner() {
        return ascendingDiagonal() || descendingDiagonal();
    }

    private boolean ascendingDiagonal() {
        for (int i = 3; i < ROWS; i++)
            for (int j = 0; j < COLUMNS - 3; j++) {
                states player = this.boardStates[i][j];
                if (!player.equals(states.EMPTY))
                    if (boardStates[i - 1][j + 1] == player && boardStates[i - 2][j + 2] == player && boardStates[i - 3][j + 3] == player)
                        return true;
            }

        return false;
    }

    private boolean descendingDiagonal() {
        for (int i = 3; i < ROWS; i++)
            for (int j = 3; j < COLUMNS; j++) {
                states player = this.boardStates[i][j];
                if (!player.equals(states.EMPTY))
                    if (boardStates[i - 1][j - 1] == player && boardStates[i - 2][j - 2] == player && boardStates[i - 3][j - 3] == player)
                        return true;
            }
        return false;
    }

    public boolean checkTie() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLUMNS; j++)
                if (this.boardStates[i][j].equals(states.EMPTY))
                    return false;
        return true;
    }

    public void finishTheGame() {
        String message = checkWinner() ? whoseTurn() + " won the game :) \n" : "The game end in Tie :| \n";
        int response = JOptionPane.showConfirmDialog(null, message + "Do you want to play again? ",
                "Message", JOptionPane.YES_NO_CANCEL_OPTION);
        finishActions(response);
    }

    private void finishActions(int responseCode) {
        gamePlay gamePlay = new gamePlay();
        switch (responseCode) {
            case 0:
                gamePlay.restartGame();
                break;
            case 1:
                gamePlay.justCloseWindow();
                break;
        }
    }
    public int getLastFilledRow(int column){
        for (int i = ROWS-1; i >=0 ; i--) {
            if (this.boardStates[i][column] == states.EMPTY)
                return i;
        }
        return -1;
    }
}