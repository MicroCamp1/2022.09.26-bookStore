package pl.comarch.camp.micro.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.services.IBasketService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BasketService implements IBasketService {

    final BookRepository bookRepository;
    @Resource
    SessionObject sessionObject;

    public BasketService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookToBasket(int id) {
        for (Position position : this.sessionObject.getBasket()) {
            if (position.getBook().getId() == id) {
                position.incrementQuantity();
                return;
            }
        }

        Optional<Book> bookBox = this.bookRepository.findById(id);
        if (bookBox.isPresent()) {
            Position position = new Position();
            position.setQuantity(1);
            position.setBook(bookBox.get());

            this.sessionObject.getBasket().add(position);
        }
    }

    @Override
    public double calculateBasketSum() {
        double sum = 0.0;
        for (Position position : this.sessionObject.getBasket()) {
            sum += position.getQuantity() * position.getBook().getPrice();
        }
        return sum;
    }
}
