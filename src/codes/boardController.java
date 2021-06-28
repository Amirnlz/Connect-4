package codes;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class boardController extends gameLogic implements Initializable {

    public GridPane gridPane;
    public Label whichPlayerLabel;
    public Label explainLabel;
    private int selectedColumn;

    public void circleClick(MouseEvent mouseEvent) {
        Circle clickedSource = (Circle) mouseEvent.getSource();
        selectedColumn = GridPane.getColumnIndex(clickedSource);
        getPlay();
        if (!checkWinner())
            setWhichPlayerLabel();
        setExplainLabel();
    }

    private void getPlay() {
        int row = findLowestRow(selectedColumn);
        if (isValidMove(selectedColumn)) {
            updateState(row, selectedColumn, whoseTurn());
            setCircleColor(row, selectedColumn, whoseTurn());
            checkScores();
            changePlayerTurn();
        } else message();
    }

    public void setCircleColor(int row, int column, states player) {
        Circle circle = (Circle) getNode(row, column);
        if (!player.equals(states.EMPTY))
            circle.setFill(getPlayerColor());
    }

    public Node getNode(int row, int column) {
        ObservableList<Node> list = gridPane.getChildren();
        for (Node node : list)
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column)
                return node;
        return null;
    }

    private void message() {
        JOptionPane.showMessageDialog(null, "Column is full select other one.",
                "Alert", JOptionPane.WARNING_MESSAGE);
    }

    private void setWhichPlayerLabel() {
        whichPlayerLabel.setTextFill(getPlayerColor());
        whichPlayerLabel.setText(String.valueOf(whoseTurn()));
    }

    private void setExplainLabel() {
        String message = getExplainLabelText();
        explainLabel.setText(message);
    }

    private Color getPlayerColor() {
        Color yellow = Color.rgb(255, 183, 3);
        Color red = Color.rgb(230, 57, 70);
        return whoseTurn() == states.PLAYER1 ? yellow : red;
    }

    private String getExplainLabelText() {
        if (!checkWinner() && !checkTie())
            return "Turn";
        else if (checkWinner())
            return "won the game.";
        else
            return "game is Tie.";
    }

    public void mouseMove(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        int enteredColumn = GridPane.getColumnIndex(circle);
        hoverColumn(enteredColumn);
    }

    private void hoverColumn(int movingColumn) {
        int firstEmptyCircleRow = getLastFilledRow(movingColumn);
        if (firstEmptyCircleRow != -1)
        setHover(firstEmptyCircleRow, movingColumn);
    }

    private void setHover(int startRow, int column) {
        Color hoveringColor = Color.rgb(253, 255, 182);
        for (int i = startRow; i >= 0; i--) {
            Circle circle = (Circle) getNode(i, column);
            circle.setFill(hoveringColor);
        }
    }

    public void exitMouse(MouseEvent mouseEvent) {
        Circle circle = (Circle) mouseEvent.getSource();
        int exitedColumn = GridPane.getColumnIndex(circle);
        undoHover(exitedColumn);
    }

    private void undoHover(int column) {
        int firstEmptyCircleRow = getLastFilledRow(column);
        if (firstEmptyCircleRow != -1)
            resetColor(firstEmptyCircleRow, column);
    }

    private void resetColor(int row, int column) {
        for (int i = row; i >= 0; i--) {
            Circle circle = (Circle) getNode(i, column);
            circle.setFill(Color.WHITE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWhichPlayerLabel();
        setExplainLabel();
    }
}