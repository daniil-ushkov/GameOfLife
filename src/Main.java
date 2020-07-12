import java.util.Random;

public class Main {
    public static void main(String[] args) {
        boolean[][] data = new boolean[20][20];
        Random random = new Random(1);
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[0].length; ++j) {
                data[i][j] = random.nextBoolean();
            }
        }
        Universe universe = new Universe(data);
        universe.setShape(10, 10, Universe.SNAKE);
        universe.setShape(5,5, Universe.rotate(Universe.GLIDER, -100));
        try {
            universe.printLife(1000, 300);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
