package pl.comarch.camp.micro.book.pattern.state;

import pl.comarch.camp.micro.book.pattern.Order;
import pl.comarch.camp.micro.book.pattern.Transactions;
import pl.comarch.camp.micro.book.pattern.strategy.PaymentStrategy;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AcceptedState extends AState {


    public AcceptedState(Order order) {
        super(order);
    }

    @Override
    public void pay(PaymentStrategy paymentStrategy) {
        BigDecimal paymentValue = order.getPositions().sumPositions();
        paymentStrategy.pay(paymentValue);
        order.getTransactions().add(new Transactions.Transaction(paymentValue, LocalDateTime.now()));
        order.changeState(new PaidState(order));
    }

}
