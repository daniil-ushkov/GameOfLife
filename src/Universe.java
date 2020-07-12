import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Universe {
    private final boolean[][] field;

    public static final boolean[][] GLIDER = {
            {false, false, true},
            {true, false, true},
            {false, true, true}
    };

    public static final boolean[][] SNAKE = {
            {true, true, false, true},
            {true, false, true, true},
    };

    public Universe(boolean[][] field) throws IllegalArgumentException {
        checkData(field);
        this.field = field;
    }

    private static void checkData(boolean[][] field) {
        if (Arrays.stream(field)
                .mapToInt(booleans -> booleans.length)
                .distinct()
                .count() != 1) {
            throw new IllegalArgumentException("illegal structure of field");
        }
    }

    public int getFirstDim() {
        return field.length;
    }

    public int getSecondDim() {
        return field[0].length;
    }

    private void set(int i, int j, boolean condition) {
        field[i][j] = condition;
    }

    public boolean aliveCell(Cell<Integer, Integer> cell) {
        if (cell.getFirst() < 0 || cell.getSecond() < 0 ||
                cell.getFirst() >= field.length ||
                cell.getSecond() >= field[0].length) {
            return false;
        }
        return field[cell.getFirst()][cell.getSecond()];
    }

    private List<Cell<Integer, Integer>> getNeighbours(int i, int j) {
        List<Cell<Integer, Integer>> result = new ArrayList<>();
        for (int di = -1; di <= 1; ++di) {
            for (int dj = -1; dj <= 1; ++dj) {
                if (di != 0 || dj != 0) {
                    result.add(new Cell<>(i + di, j + dj));
                }
            }
        }
        return result;
    }

    public int countAliveNeighbours(int i, int j) {
        return (int) getNeighbours(i, j).stream().filter(this::aliveCell).count();
    }

//    Checks if this cell will be alive after next step
    private boolean willBeAlive(int aliveNeighbours, boolean wasAlive) {
        return aliveNeighbours == 3 || (aliveNeighbours == 2 && wasAlive);
    }

//    Changes universe according to rules written in method `willBeAlive`
//    Return true if nothing changed
    public boolean nextStep() {
        Universe oldUniverse = new Universe(Arrays.stream(field)
                .map(boolean[]::clone)
                .toArray(boolean[][]::new));
        for (int i = 0; i < oldUniverse.getFirstDim(); ++i) {
            for (int j = 0; j < oldUniverse.getSecondDim(); ++j) {
                set(i, j, willBeAlive(oldUniverse.countAliveNeighbours(i, j),
                        oldUniverse.aliveCell(new Cell<>(i, j))));
            }
        }
        return Arrays.deepEquals(oldUniverse.field, field);
    }

    public void setShape(int i, int j, boolean[][] shape) {
        checkData(shape);
        for (int di = 0; di < shape.length; ++di) {
            System.arraycopy(shape[di], 0, field[i + di], j, shape[0].length);
        }
    }

    public static boolean[][] flipVertically(boolean[][] shape) {
        checkData(shape);
        boolean[][] flippedShape = new boolean[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[0].length; ++j) {
                flippedShape[i][j] = shape[i][shape[0].length - j - 1];
            }
        }
        return flippedShape;
    }

    public static boolean[][] flipHorizontally(boolean[][] shape) {
        checkData(shape);
        boolean[][] flippedShape = new boolean[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; ++i) {
            System.arraycopy(shape[shape.length - i - 1], 0, flippedShape[i], 0, shape[0].length);
        }
        return flippedShape;
    }

    public static boolean[][] rotateOnce(boolean[][] shape) {
        checkData(shape);
        boolean[][] rotatedShape = new boolean[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[0].length; ++j) {
                rotatedShape[j][shape.length - i - 1] = shape[i][j];
            }
        }
        return rotatedShape;
    }

    public static boolean[][] rotate(boolean[][] shape, int number) {
        number = ((number % 4) + 4) % 4;
        for (int i = 0; i < number; ++i) {
            shape = Universe.rotateOnce(shape);
        }
        return shape;
    }

    public void print() {
        for (int i = 0; i < getFirstDim(); ++i) {
            for (int j = 0; j < getSecondDim(); ++j) {
                System.out.print(field[i][j] ? "O " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printLife(int step, long latencyMillis) throws InterruptedException {
        for (int i = 0; i < step; ++i) {
            System.out.println("Epoch: " + i);
            print();
            if (nextStep()) {
                System.out.println("Universe got stable condition");
                return;
            }
            Thread.sleep(latencyMillis);
        }
    }

}
