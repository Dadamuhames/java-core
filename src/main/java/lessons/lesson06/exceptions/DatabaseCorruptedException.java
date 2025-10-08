package lessons.lesson06.exceptions;

public class DatabaseCorruptedException extends RuntimeException {
    public DatabaseCorruptedException(String message) {
        super(message);
    }
}
