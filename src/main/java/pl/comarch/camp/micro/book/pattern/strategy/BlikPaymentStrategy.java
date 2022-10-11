package pl.comarch.camp.micro.book.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class BlikPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean canUse(String key) {
        return "BLIK".equals(key);
    }

    @Override
    public void pay(BigDecimal paymentValue) {
        log.info("Blik strategy");
    }
}
