package pl.comarch.camp.micro.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.services.IBasketService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class BasketServiceTest extends GenericTest {

    @Autowired
    IBasketService basketService;

    @Autowired
    BookRepository mockedRepository;

    @Resource
    SessionObject sessionObject;

    private Book.BookBuilder bookFixture() {
        return Book.builder()
                .id(1).title("Tytul1")
//                .author("Author1")
                .isbn("13-123-123")
                .price(33.33);
    }

    @Test
    public void addExistingBookToBasketTest() {
        this.sessionObject.getBasket().add(new Position(bookFixture().build(), 2));
        this.sessionObject.getBasket().add(new Position(Book.builder()
                .id(2).title("Tytul2")
//                .author("Author2")
                .isbn("13-123-1234")
                .price(323.33).build(), 1));
        int bookId = 1;
        int expectedBasketPositionsCount = 2;
        int expectedBookWithId1Quantity = 3;

        this.basketService.addBookToBasket(bookId);

        Assert.assertEquals(expectedBasketPositionsCount,
                this.sessionObject.getBasket().size());
        for (Position position : this.sessionObject.getBasket()) {
            if (position.getBook().getId() == bookId) {
                Assert.assertEquals(expectedBookWithId1Quantity,
                        position.getQuantity());
                return;
            }
        }

        Assert.fail();
    }

    @Test
    public void addNotExistingBookInDBToBasketTest() {
//        GIVEN
        int bookId = 15;
        Mockito.when(this.mockedRepository.findById(bookId))
                .thenReturn(Optional.empty());

        this.sessionObject.getBasket().add(new Position(bookFixture().id(1).build(), 2));
        this.sessionObject.getBasket().add(new Position(bookFixture().id(2).build(), 1));

        int expectedBookWithId1Quantity = 2;
        int expectedBookWithId2Quantity = 1;
        int expectedBasketSize = 2;

//        WHEN
        this.basketService.addBookToBasket(bookId);

//        THEN
        assertThat(this.sessionObject.getBasket())
                .hasSize(expectedBasketSize)
                .extracting(p -> p.getBook().getId(), Position::getQuantity)
                .containsOnly(
                        tuple(1, expectedBookWithId1Quantity),
                        tuple(2, expectedBookWithId2Quantity));

    }

    @Test
    public void addNotExistingBookToEmptyBasketTest() {
        this.sessionObject.getBasket().clear();
        Mockito.when(this.bookRepository.findById(1))
                .thenReturn(Optional.of(
                        bookFixture().build()));
        int expectedPositionCount = 1;
        int expectedPositionQuantity = 1;
        Integer expectedBookId = 1;

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
        this.sessionObject.getBasket().add(new Position(bookFixture()
                .price(20.20).build(), 2));
        this.sessionObject.getBasket().add(new Position(bookFixture()
                .price(30.30).build(), 1));
        this.sessionObject.getBasket().add(new Position(bookFixture()
                .price(40.40).build(), 3));
        this.sessionObject.getBasket().add(new Position(bookFixture()
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
