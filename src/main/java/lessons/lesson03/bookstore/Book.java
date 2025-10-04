package lessons.lesson03.bookstore;

public class Book {
    private final Integer isbn;
    private final String name;
    private final String author;
    private final Integer year;
    private BookStatus status;

    public Book(Integer isbn, String name, String author, Integer year, BookStatus status) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.year = year;
        this.status = status;
    }


    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getBookInfo() {
        return String.format("Isbn: %s\nName: %s\nAuthor: %s\nYear: %d\nStatus: %s", isbn, name, author, year, status);
    }
}
