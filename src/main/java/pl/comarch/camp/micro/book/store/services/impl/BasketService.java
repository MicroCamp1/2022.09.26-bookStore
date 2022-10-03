package pl.comarch.camp.micro.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.services.IBasketService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class BasketService implements IBasketService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IBookDAO bookDAO;

    @Override
    public void addBookToBasket(int id) {
        for(Position position : this.sessionObject.getBasket()) {
            if(position.getBook().id() == id) {
                position.incrementQuantity();
                return;
            }
        }

        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if(bookBox.isPresent()) {
            Position position = new Position();
            position.setQuantity(1);
            position.setBook(bookBox.get());

            this.sessionObject.getBasket().add(position);
        }
    }

    @Override
    public double calculateBasketSum() {
        double sum = 0.0;
        for(Position position : this.sessionObject.getBasket()) {
            sum += position.getQuantity() * position.getBook().price();
        }
        return sum;
    }
}
