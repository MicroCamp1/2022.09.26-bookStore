package pl.comarch.camp.micro.book.pattern.state;

import pl.comarch.camp.micro.book.pattern.strategy.PaymentStrategy;
import pl.comarch.camp.micro.book.pattern.Position;

public interface State {
    default void pay(PaymentStrategy paymentStrategy) {
    }
    default  void addPosition(Position position){}

    default  void acceptOrder(){}
}
