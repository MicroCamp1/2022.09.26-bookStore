package pl.comarch.camp.micro.book.store.database;

import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBookList();
    Optional<Book> getBookById(int id);
}
