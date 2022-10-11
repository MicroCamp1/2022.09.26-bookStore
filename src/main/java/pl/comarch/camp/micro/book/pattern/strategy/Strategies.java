package pl.comarch.camp.micro.book.pattern.strategy;

import java.util.List;
import java.util.Optional;

public class Strategies {
    private final List<PaymentStrategy> listStrategies;

    public Strategies(List<PaymentStrategy> listStrategies) {
        this.listStrategies = listStrategies;
    }

    public Optional<PaymentStrategy> choose(String strategyType) {
        return listStrategies.stream()
                .filter(paymentStrategy -> paymentStrategy.canUse(strategyType))
                .findFirst();
    }
}
