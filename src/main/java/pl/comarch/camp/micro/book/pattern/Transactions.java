package pl.comarch.camp.micro.book.pattern;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    List<Transaction> transactionList = new ArrayList<>();

    public Transactions add(Transaction transaction) {
        transactionList.add(transaction);
        return this;
    }

    @Value
    public static class Transaction {
        BigDecimal value;
        LocalDateTime dateTime;
    }
}
