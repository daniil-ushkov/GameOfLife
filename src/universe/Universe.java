package universe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Cell {
    private final int first;
    private final int second;

    Cell(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}

public class Universe {
    private boolean[][] field;

    public Universe(int rows, int columns) throws IllegalArgumentException {
        field = new boolean[rows][columns];
    }

    private Universe(boolean[][] field) throws IllegalArgumentException {
        this.field = field;
    }

    public int getRows() {
        return field.length;
    }

    public int getColumns() {
        return field[0].length;
    }

    public void set(int i, int j, boolean condition) {
        field[i][j] = condition;
    }

    public void toggle(int i, int j) {
        set(i, j, !field[i][j]);
    }

    public void clear() {
        for (int i = 0; i < getRows(); ++i) {
            for (int j = 0; j < getColumns(); ++j) {
                field[i][j] = false;
            }
        }
    }

    public boolean[][] getField() {
        return field;
    }

    public void setField(boolean[][] field) {
        this.field = field;
    }

    public void setShape(int i, int j, boolean[][] shape) {
        Shape.checkStructure(shape);
        for (int di = 0; di < shape.length; ++di) {
            System.arraycopy(shape[di], 0, field[i + di], j, shape[0].length);
        }
    }

    private boolean aliveCell(Cell cell) {
        if (cell.getFirst() < 0 || cell.getSecond() < 0 ||
                cell.getFirst() >= field.length ||
                cell.getSecond() >= field[0].length) {
            return false;
        }
        return field[cell.getFirst()][cell.getSecond()];
    }

    public boolean aliveCell(int i, int j) {
        if (i < 0 || j < 0 || i >= field.length || j >= field[0].length) {
            return false;
        }
        return field[i][j];
    }

    private List<Cell> getNeighbours(int i, int j) {
        List<Cell> result = new ArrayList<>();
        for (int di = -1; di <= 1; ++di) {
            for (int dj = -1; dj <= 1; ++dj) {
                if (di != 0 || dj != 0) {
                    result.add(new Cell(i + di, j + dj));
                }
            }
        }
        return result;
    }

    private int countAliveNeighbours(int i, int j) {
        return (int) getNeighbours(i, j).stream().filter(this::aliveCell).count();
    }

//    Checks if this cell will be alive after next step
    private boolean willBeAlive(int aliveNeighbours, boolean wasAlive) {
        return aliveNeighbours == 3 || (aliveNeighbours == 2 && wasAlive);
    }

//    Changes universe according to rules written in method `willBeAlive`
//    Returns old universe condition
    public Universe nextStep() {
        Universe oldUniverse = new Universe(Arrays.stream(field)
                .map(boolean[]::clone)
                .toArray(boolean[][]::new));
        for (int i = 0; i < oldUniverse.getRows(); ++i) {
            for (int j = 0; j < oldUniverse.getColumns(); ++j) {
                set(i, j, willBeAlive(oldUniverse.countAliveNeighbours(i, j),
                        oldUniverse.aliveCell(new Cell(i, j))));
            }
        }
        return oldUniverse;
    }
}
