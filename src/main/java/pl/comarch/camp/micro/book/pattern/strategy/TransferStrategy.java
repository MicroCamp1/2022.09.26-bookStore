package pl.comarch.camp.micro.book.pattern.strategy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class TransferStrategy implements PaymentStrategy {
    @Override
    public boolean canUse(String key) {
        return "TRANSFER".equals(key);
    }

    @Override
    public void pay(BigDecimal paymentValue) {
        log.info("Transfer strategy");
    }
}
