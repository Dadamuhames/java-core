package lessons.lesson03.bookstore;

import java.util.Scanner;

public class BookStoreApplication {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BookRepository bookRepository = new BookRepository();
            AddBookService addBookService = new AddBookService(bookRepository, scanner);

            BookStore bookStore = new BookStore(bookRepository, addBookService);

            printOptions();
            String option = scanner.nextLine();


            while (true) {
                switch (option) {
                    case "L" -> bookStore.getBooks();

                    case "A" -> bookStore.addNewBook();

                    case "R" -> {
                        System.out.println("Reserving book");
                        System.out.print("Enter book isbn: ");

                        Integer bookIsbn = scanner.nextInt();

                        scanner.nextLine();

                        try {
                            bookStore.reserveBook(bookIsbn);
                        } catch (Exception e) {
                            System.out.printf("404 - %s\n", e.getMessage());
                        }
                    }

                    case "Q" -> {
                        clearTerminal();
                        System.exit(0);
                    }
                }

                printOptions();
                option = scanner.nextLine();
                clearTerminal();
            }
        }
    }


    public static void printOptions() {
        System.out.println("Choose section:\n[L] - Books list\n[A] - Add book\n[R] - Reserve book\n[Q] - Quit");
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
