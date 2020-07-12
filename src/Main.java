public class Main {
    public static void main(String[] args) {
        boolean[][] data = new boolean[20][20];
        Universe universe = new Universe(data);
        universe.setShape(10, 10, Universe.SNAKE);
        universe.setShape(5,5, Universe.rotate(Universe.GLIDER, 1));
        try {
            universe.printLife(1000, 300);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
