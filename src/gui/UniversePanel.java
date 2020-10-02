package gui;

import universe.Universe;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.function.IntSupplier;

public class UniversePanel extends GOLPanel {

    private final Universe universe;
    private final CellButton[][] field;

    private Thread thread;

    private final Memory memory = new Memory();

    public final UniverseScrollPane scrollPane = new UniverseScrollPane(this);

    @FunctionalInterface
    private interface IntIntConsumer {
        void apply(int i, int j);
    }

    private void forEachCell(IntIntConsumer consumer) {
        for (int i = 0; i < universe.getRows(); ++i) {
            for (int j = 0; j < universe.getColumns(); ++j) {
                consumer.apply(i, j);
            }
        }
    }

    public UniversePanel(int rows, int columns) {
        super();
        universe = new Universe(rows, columns);
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        field = new CellButton[rows][columns];
        forEachCell((i, j) -> {
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
        });
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
        forEachCell((i, j) -> {
            if (universe.aliveCell(i, j)) {
                setGreen(i, j);
            } else {
                setBlack(i, j);
            }
        });

    }

    private void setButtonsEnabled(boolean enable) {
        forEachCell((i, j) -> field[i][j].setEnabled(enable));
    }

    private boolean isAlive() {
        return thread != null && thread.isAlive();
    }

    public void start(IntSupplier latencySupplier) {
        if (isAlive()) {
            return;
        }
        thread = new Thread(() -> {
            setButtonsEnabled(false);
            while (!thread.isInterrupted()) {
                doNext();
                try {
                    Thread.sleep(latencySupplier.getAsInt());
                } catch (InterruptedException e) {
                    break;
                }
            }
            setButtonsEnabled(true);
        });
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    private void doNext() {
        Universe oldUniverse = universe.nextStep();
        if (!Arrays.deepEquals(oldUniverse.getField(), universe.getField())) {
            memory.push(oldUniverse.getField());
            showUniverse();
        } else {
            stop();
        }
    }

    public void next() {
        if (isAlive()) {
            return;
        }
        doNext();
    }

    public void prev() {
        if (isAlive()) {
            return;
        }
        if (!memory.empty()) {
            universe.setField(memory.pop());
            showUniverse();
        }
    }

    public void clear() {
        if (isAlive()) {
            stop();
        }
        memory.push(universe.getFieldCopy());
        universe.clear();
        showUniverse();
    }

    public void bigger() {
        forEachCell((i, j) -> field[i][j].bigger());
        scrollPane.setViewportView(this);
    }

    public void smaller() {
        forEachCell((i, j) -> field[i][j].smaller());
        scrollPane.setViewportView(this);
    }
}
