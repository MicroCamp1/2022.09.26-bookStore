package pl.comarch.camp.micro.book.pattern;

import lombok.Builder;
import lombok.Data;
import pl.comarch.camp.micro.book.store.model.Book;


@Data
@Builder
public class Position {
    private Book book;
    private int quantity;
}
