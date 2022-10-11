package pl.comarch.camp.micro.book.pattern.state;

import pl.comarch.camp.micro.book.pattern.Order;

public abstract class AState implements State {
    protected final Order order;

    protected AState(Order order) {
        this.order = order;
    }

}
