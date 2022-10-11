package pl.comarch.camp.micro.book.pattern;

import pl.comarch.camp.micro.book.pattern.state.NewOrderState;
import pl.comarch.camp.micro.book.pattern.state.State;
import pl.comarch.camp.micro.book.pattern.strategy.BlikPaymentStrategy;
import pl.comarch.camp.micro.book.pattern.strategy.PaymentStrategy;
import pl.comarch.camp.micro.book.pattern.strategy.Strategies;
import pl.comarch.camp.micro.book.pattern.strategy.TransferStrategy;
import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Order {
    static final List<PaymentStrategy> LIST_STRATEGIES = Stream.of(new BlikPaymentStrategy(), new TransferStrategy())
            .collect(Collectors.toList());
    private State state = new NewOrderState(this);
    private Positions positions = new Positions();

    private Transactions transactions = new Transactions();

    public static void main(String[] args) {
        Order order = new Order();

        order.addPosition(Position.builder().quantity(1)
                .book(Book.builder().title("TEST book").price(20).build()).build());

        order.acceptOrder();
        order.addPosition(Position.builder().quantity(2)
                .book(Book.builder().title("TEST book 2").price(25).build()).build());  // It has not been  add - wrong state

        Strategies strategies = new Strategies(LIST_STRATEGIES);
        strategies
                .choose("BLIK")
                .ifPresent(order::pay);

        order.pay(new TransferStrategy());  // It has not been launched  -  state paid

    }

    @Override
    public String toString() {
        return "Order{" +
                "positions=" + positions +
                '}';
    }

    public void addPosition(Position position) {
        state.addPosition(position);
    }

    public void acceptOrder() {
        state.acceptOrder();
    }

    public void pay(PaymentStrategy paymentStrategy) {
        state.pay(paymentStrategy);
    }

    public void repayment() {
    }

    public void changeState(State state) {
        this.state = state;
    }

    public Positions getPositions() {
        return positions;
    }

    public void setPositions(Positions positions) {
        this.positions = positions;
    }

    public Transactions getTransactions() {
        return transactions;
    }

}
