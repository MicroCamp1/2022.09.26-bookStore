package pl.comarch.camp.micro.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.services.IBasketService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class BasketServiceTest extends GenericTest {

    @Autowired
    IBasketService basketService;
    @Resource
    SessionObject sessionObject;

    @Test
    public void addExistingBookToBasketTest() {
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(1).title("Tytul1")
                .author("Author1").isbn("13-123-123")
                .price(33.33).build(), 2));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(2).title("Tytul2")
                .author("Author2").isbn("13-123-1234")
                .price(323.33).build(), 1));
        int bookId = 1;
        int expectedBasketPositionsCount = 2;
        int expectedBookWithId1Quantity = 3;

        this.basketService.addBookToBasket(bookId);

        Assert.assertEquals(expectedBasketPositionsCount,
                this.sessionObject.getBasket().size());
        for(Position position : this.sessionObject.getBasket()) {
            if(position.getBook().getId() == bookId) {
                Assert.assertEquals(expectedBookWithId1Quantity,
                        position.getQuantity());
                return;
            }
        }

        Assert.fail();
    }

    @Test
    public void addNotExistingBookInDBToBasketTest() {
        Mockito.when(this.bookDAO.getBookById(15))
                .thenReturn(Optional.empty());
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(1).title("Tytul1")
                .author("Author1").isbn("13-123-123")
                .price(33.33).build(), 2));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(2).title("Tytul2")
                .author("Author2").isbn("13-123-1234")
                .price(323.33).build(), 1));

        int bookId = 15;
        int expectedBookWithId1Quantity = 2;
        int expectedBookWithId2Quantity = 1;
        int expectedBasketSize = 2;

        this.basketService.addBookToBasket(bookId);
        Assert.assertEquals(expectedBasketSize,
                this.sessionObject.getBasket().size());
        for(Position position : this.sessionObject.getBasket()) {
            if(position.getBook().getId() == 1) {
                Assert.assertEquals(expectedBookWithId1Quantity,
                        position.getQuantity());
            } else if(position.getBook().getId() == 2) {
                Assert.assertEquals(expectedBookWithId2Quantity,
                        position.getQuantity());
            }
        }
    }

    @Test
    public void addNotExistingBookToEmptyBasketTest() {
        this.sessionObject.getBasket().clear();
        Mockito.when(this.bookDAO.getBookById(1))
                .thenReturn(Optional.of(
                        Book.builder().id(1).title("Tytul1").author("Author1")
                                .isbn("13-123-123").price(33.33).build()));
        int expectedPositionCount = 1;
        int expectedPositionQuantity = 1;
        int expectedBookId = 1;

        this.basketService.addBookToBasket(1);

        Assert.assertEquals(expectedPositionCount,
                this.sessionObject.getBasket().size());
        Assert.assertEquals(expectedPositionQuantity,
                this.sessionObject.getBasket().stream()
                        .findFirst()
                        .get()
                        .getQuantity());
        Assert.assertEquals(expectedBookId,
                this.sessionObject.getBasket().stream()
                        .findFirst()
                        .get()
                        .getBook()
                        .getId());
    }

    @Test
    public void calculateBasketSumTest() {
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(1).title("Tytul1")
                .author("Author1").isbn("13-123-123")
                .price(20.20).build(), 2));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(2).title("Tytul2")
                .author("Author2").isbn("13-123-1234")
                .price(30.30).build(), 1));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(3).title("Tytul3")
                .author("Author3").isbn("13-123-23423")
                .price(40.40).build(), 3));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(4).title("Tytul4")
                .author("Author4").isbn("13-345543-76678")
                .price(50.99).build(), 1));
        double expectedSum = 242.89;

        double actual = this.basketService.calculateBasketSum();

        Assert.assertEquals(expectedSum, actual, 0.001);
    }

    @Test
    public void calculateEmptyBasketSumTest() {
        this.sessionObject.getBasket().clear();
        double expectedSum = 0.0;

        double actual = this.basketService.calculateBasketSum();

        Assert.assertEquals(expectedSum, actual, 0.001);
    }
}
