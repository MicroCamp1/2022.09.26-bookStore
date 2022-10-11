package pl.comarch.camp.micro.book.pattern.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean canUse(String key);
    void pay(BigDecimal paymentValue);
}
