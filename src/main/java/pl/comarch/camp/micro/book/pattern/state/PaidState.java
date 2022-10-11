package pl.comarch.camp.micro.book.pattern.state;

import pl.comarch.camp.micro.book.pattern.Order;

public class PaidState extends AState {
    public PaidState(Order order) {
        super(order);
    }
}
