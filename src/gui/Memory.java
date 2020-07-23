package gui;

import java.util.ArrayDeque;

public class Memory extends ArrayDeque<boolean[][]> {
    public static final int MAX_SIZE = 100;

    @Override
    public void addFirst(boolean[][] item) {
        if (size() == MAX_SIZE) {
            removeLast();
        }
        super.addFirst(item);
    }

    public boolean empty() {
        return size() == 0;
    }
}
