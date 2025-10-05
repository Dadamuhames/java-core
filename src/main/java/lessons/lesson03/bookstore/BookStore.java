package lessons.lesson03.bookstore;

import java.util.Map;

public class BookStore {
    private final BookRepository bookRepository;
    private final AddBookService addBookService;

    public BookStore(BookRepository bookRepository, AddBookService addBookService) {
        this.bookRepository = bookRepository;
        this.addBookService = addBookService;
    }

    public void getBooks() {
        System.out.println("Books list:\n");

        Map<Integer, Book> books = bookRepository.getBooks();


        System.out.println("=".repeat(10));

        for (Map.Entry<Integer, Book> entry : books.entrySet()) {
            String bookInfo = entry.getValue().getBookInfo();
            System.out.println(bookInfo);
            System.out.println("=".repeat(10));
        }
    }


    public void addNewBook() {
        System.out.println("Adding new book");

        Integer isbn = addBookService.promptIsbn();
        String name = addBookService.promptName();
        String author = addBookService.promptAuthor();
        Integer year = addBookService.promptYear();

        Book book = new Book(isbn, name, author, year, BookStatus.AVAILABLE);

        bookRepository.save(book);

        System.out.printf("Book added:\n%s\n", book.getBookInfo());
    }


    public void reserveBook(final Integer isbn) throws Exception {
        Book book = bookRepository.findBookByIsbn(isbn).orElseThrow(() -> new Exception("Book does not exist"));

        if (book.getStatus().equals(BookStatus.RESERVED)) {
            throw new Exception("Book already reserved");
        }

        bookRepository.updateStatus(isbn, BookStatus.RESERVED);

        System.out.printf("Book '%s' reserved\n", book.getName());
    }

}
