package universe;

import java.util.Arrays;

// Will be used in future
public class Shape {
    public static final boolean[][] GLIDER = {
            {false, false, true},
            {true, false, true},
            {false, true, true}
    };

    public static final boolean[][] SNAKE = {
            {true, true, false, true},
            {true, false, true, true},
    };

    public static void checkStructure(boolean[][] field) {
        if (Arrays.stream(field)
                .mapToInt(booleans -> booleans.length)
                .distinct()
                .count() != 1) {
            throw new IllegalArgumentException("illegal structure of shape's field");
        }
    }

    public static boolean[][] flipVertically(boolean[][] shape) {
        checkStructure(shape);
        boolean[][] flippedShape = new boolean[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; ++i) {
            for (int j = 0; j < shape[0].length; ++j) {
                flippedShape[i][j] = shape[i][shape[0].length - j - 1];
            }
        }
        return flippedShape;
    }

    public static boolean[][] flipHorizontally(boolean[][] shape) {
        checkStructure(shape);
        boolean[][] flippedShape = new boolean[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; ++i) {
            System.arraycopy(shape[shape.length - i - 1], 0, flippedShape[i], 0, shape[0].length);
        }
        return flippedShape;
    }

    public static boolean[][] rotateOnce(boolean[][] shape) {
        checkStructure(shape);
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
            shape = rotateOnce(shape);
        }
        return shape;
    }
}
