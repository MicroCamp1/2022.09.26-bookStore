package pl.comarch.camp.micro.book.store.database.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryBookDAO implements IBookDAO {
    private final List<Book> bookList = new ArrayList<>();

    public MemoryBookDAO() {
        this.bookList.add(
                Book.builder()
                        .id(1).title("Tytul1")
                        .author("Author1").isbn("13-123-123")
                        .price(33.33).build());

        this.bookList.add(
                Book.builder()
                        .id(2).title("Tytul2")
                        .author("Author2").isbn("13-123-1234")
                        .price(323.33).build());

        this.bookList.add(
                Book.builder()
                        .id(3).title("Tytul3")
                        .author("Author3").isbn("13-123-4756")
                        .price(54.33).build());

        this.bookList.add(
                Book.builder()
                        .id(4).title("Tytul4")
                        .author("Author4").isbn("13-435-123")
                        .price(67.66).build());
    }

    public List<Book> getBookList() {
        return bookList;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        for(Book book : this.bookList) {
            if (book.id() == id) {
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }
}
