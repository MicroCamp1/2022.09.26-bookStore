package pl.comarch.camp.micro.book.store.model.dto;

import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;

public class BooksResponse {
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
