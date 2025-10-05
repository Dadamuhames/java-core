package lessons.lesson03.ractangle;

import java.util.Scanner;

public class RectangleApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int length = scanner.nextInt();
            int width = scanner.nextInt();

            Rectangle rectangle = new Rectangle(length, width);

            System.out.printf("Area: %s\n", rectangle.calculateSquare());
            System.out.printf("Perimeter: %s\n", rectangle.calculatePerimeter());
        }
    }
}
