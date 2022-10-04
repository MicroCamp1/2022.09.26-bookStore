package pl.comarch.camp.micro.book.store.database.impl;

import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAOStub implements IBookDAO {
    @Override
    public List<Book> getBookList() {
        return null;
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.empty();
    }
}
