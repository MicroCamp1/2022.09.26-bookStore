package pl.comarch.camp.micro.book.pattern.state;

import lombok.extern.slf4j.Slf4j;
import pl.comarch.camp.micro.book.pattern.Order;
import pl.comarch.camp.micro.book.pattern.Position;

@Slf4j
public class NewOrderState extends AState {
    public NewOrderState(Order order) {
        super(order);
    }

    @Override
    public void addPosition(Position position) {
        log.info("ADD position {}", position);
        order.getPositions().add(position);
    }

    @Override
    public void acceptOrder() {
        log.info("accept order {}", order);
        order.changeState(new AcceptedState(order));
    }
}
