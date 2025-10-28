package homework.day10;

public class Chair {
    private int height;
    private int width;

    public Chair(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "Chair{" + "height=" + height + ", width=" + width + '}';
    }
}