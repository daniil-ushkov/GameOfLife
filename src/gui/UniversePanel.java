package gui;

import universe.Universe;
import java.awt.*;
import java.awt.event.ActionListener;

public class UniversePanel extends GOLPanel {

    private final Universe universe;
    private final CellButton[][] field;

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

    public void next() {
        universe.nextStep();
        showUniverse();
    }

    public void clear() {
        universe.clear();
        showUniverse();
    }
}
