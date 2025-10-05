package lessons.lesson03.bookstore;

import java.util.Scanner;

public class AddBookService {
    private final BookRepository bookRepository;
    private final Scanner scanner;

    public AddBookService(BookRepository bookRepository, Scanner scanner) {
        this.bookRepository = bookRepository;
        this.scanner = scanner;
    }

    public Integer promptIsbn() {
        System.out.print("Enter unique book isbn: ");

        Integer isbn = scanner.nextInt();

        if (bookRepository.existsByKey(isbn)) {
            return promptIsbn();
        }

        scanner.nextLine();

        return isbn;
    }

    public String promptName() {
        System.out.print("Enter book name: ");

        return scanner.nextLine();
    }

    public String promptAuthor() {
        System.out.print("Enter book author:");

        return scanner.nextLine();
    }

    public Integer promptYear() {
        System.out.print("Enter book's year: ");

        Integer year = scanner.nextInt();

        scanner.nextLine();

        return year;
    }
}
