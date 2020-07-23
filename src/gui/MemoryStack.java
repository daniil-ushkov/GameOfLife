package gui;

import java.util.Stack;

public class MemoryStack extends Stack<boolean[][]> {
    public static final int MAX_SIZE = 20;

    @Override
    public boolean[][] push(boolean[][] item) {
        if (size() == MAX_SIZE) {
            clear();
        }
        return super.push(item);
    }
}
