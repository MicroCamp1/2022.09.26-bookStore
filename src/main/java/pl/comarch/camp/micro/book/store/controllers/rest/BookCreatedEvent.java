package pl.comarch.camp.micro.book.store.controllers.rest;

import lombok.Value;
import pl.comarch.camp.micro.book.store.model.Book;

@Value
public class BookCreatedEvent {
    Object source;
    Book createdBook;
}
