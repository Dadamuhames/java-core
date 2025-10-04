package lessons.lesson03.bookstore;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepository {
    private Map<Integer, Book> books;

    public BookRepository() {
        this.books = new HashMap<>();
    }

    public Map<Integer, Book> getBooks() {
        return books;
    }


    public boolean existsByKey(final Integer key) {
        return books.containsKey(key);
    }


    public void save(final Book book) {
        Integer isbn = book.getIsbn();

        books.put(isbn, book);
    }

    public void updateStatus(final Integer isbn, final BookStatus status) {
        books.get(isbn).setStatus(status);
    }

    public Optional<Book> findBookByIsbn(final Integer isbn) {
        return Optional.ofNullable(books.get(isbn));
    }
}
