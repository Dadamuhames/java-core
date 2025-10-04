package lessons.lesson03.ractangle;


public class Rectangle {
    private final int length;
    private final int width;

    public Rectangle(int length, int width) {
        this.width = width;
        this.length = length;
    }

    public int calculateSquare() {
        return this.length * this.width;
    }

    public int calculatePerimeter() {
        int PERIMETER_MULTIPLIER = 2;
        return (this.length + this.width) * PERIMETER_MULTIPLIER;
    }
}

