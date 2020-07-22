package gui;


import universe.Universe;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;

public class UniversePanel extends GOLPanel {

    private final Universe universe;
    private final CellButton[][] field;

    private final Stack<boolean[][]> memoryStack = new Stack<>();

//    private final static int AUTO_SAVE_LATENCY = 10;
//    private int changesCounter = 0;

//    private void updateCounter() {
//        ++changesCounter;
//        if (changesCounter == AUTO_SAVE_LATENCY) {
//            memoryStack.push(universe.getFieldCopy());
//            changesCounter = 0;
//        }
//    }

    public UniversePanel(int rows, int columns) {
        super();
        universe = new Universe(rows, columns);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        field = new CellButton[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                CellButton cell = new CellButton();
                field[i][j] = cell;
                if (j + 1 == columns) {
                    constraints.gridwidth = GridBagConstraints.REMAINDER;
                } else {
                    constraints.gridwidth = 1;
                }
                layout.setConstraints(cell, constraints);
                add(cell);
                cell.addActionListener(cellButtonListener(i, j));
            }
        }
    }

    private ActionListener cellButtonListener(int i, int j) {
        return e -> toggle(i, j);
    }

    private void toggle(int i, int j) {
//        updateCounter();
        universe.toggle(i, j);
        if (field[i][j].getBackground() == Color.BLACK) {
            field[i][j].setBackground(Color.GREEN);
        } else {
            field[i][j].setBackground(Color.BLACK);
        }
    }

    private void setGreen(int i, int j) {
        field[i][j].setBackground(Color.GREEN);
    }

    private void setBlack(int i, int j) {
        field[i][j].setBackground(Color.BLACK);
    }

    private void showUniverse() {
        for (int i = 0; i < universe.getRows(); ++i) {
            for (int j = 0; j < universe.getColumns(); ++j) {
                if (universe.aliveCell(i, j)) {
                    setGreen(i, j);
                } else {
                    setBlack(i, j);
                }
            }
        }
    }

    public void start() {
        //todo
    }

    public void stop() {
        //todo
    }

    public void next() {
        Universe oldUniverse = universe.nextStep();
        if (!Arrays.deepEquals(oldUniverse.getField(), universe.getField())) {
            memoryStack.push(oldUniverse.getField());
            showUniverse();
        }
    }

    public void prev() {
        if (!memoryStack.empty()) {
            universe.setField(memoryStack.pop());
            showUniverse();
        }
    }

    public void clear() {
        memoryStack.push(universe.getFieldCopy());
        universe.clear();
        showUniverse();
    }
}
