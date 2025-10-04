package lessons.lesson03.fighting.utils;

public class Utils {
    public static final String RESET_COLOR = "\033[0m";

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
