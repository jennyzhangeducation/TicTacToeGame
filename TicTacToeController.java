package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class TicTacToeController {

    @FXML
    private Button button0_0, button0_1, button0_2, button1_0, button1_1, button1_2, button2_0, button2_1, button2_2,restartButton;

    @FXML
    private Label gameStatus;
    private char currentPlayer;
    private char[][] board = new char[3][3];
    private boolean gameOver = false;
    @FXML
    public void initialize() {
        int num = (int)(Math.random()*2);
        if(num ==1)
        {
            currentPlayer = 'X';
        }else{
            currentPlayer = 'O';
        }
        gameStatus.setText("Player " + currentPlayer + "'s turn");
    }
    public TicTacToeController() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }
        //gameStatues.setText("Player " + currentPlayer + "'s turn");
    }

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        if (gameOver) {
            gameStatus.setText("Game over! ");
            return;
        }

        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        int row = Character.getNumericValue(buttonId.charAt(6));
        int col = Character.getNumericValue(buttonId.charAt(8));

        if (board[row][col] == '_') {
            board[row][col] = currentPlayer;
            clickedButton.setText(String.valueOf(currentPlayer));
            if (checkWin(currentPlayer)) {
                gameStatus.setText("Player " + currentPlayer + " wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                gameStatus.setText("It's a tie!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                gameStatus.setText("Player " + currentPlayer + "'s turn");
            }
        } else {
            gameStatus.setText("Cell already taken. Choose another cell.");
        }
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player ||
                    board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player ||
                board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    @FXML
    private void handleRestartButtonClick() {
        // Reset the board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '_';
            }
        }

        // Clear the text on each button
        clearButton(button0_0);
        clearButton(button0_1);
        clearButton(button0_2);
        clearButton(button1_0);
        clearButton(button1_1);
        clearButton(button1_2);
        clearButton(button2_0);
        clearButton(button2_1);
        clearButton(button2_2);

        // Reset the current player and game status
        initialize();
        gameOver = false;
        /*
        currentPlayer = 'X';

        gameStatus.setText("Player " + currentPlayer + "'s turn");
        */

    }
    @FXML
    private void clearButton(Button button) {
        if (button != null) {
            button.setText("");
        }
    }
}


