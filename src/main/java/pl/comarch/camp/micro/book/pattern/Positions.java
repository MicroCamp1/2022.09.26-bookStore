package pl.comarch.camp.micro.book.pattern;

import lombok.Value;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Value
public class Positions {
    List<Position> positionList = new ArrayList<>();
    public BigDecimal sumPositions() {
        return positionList.stream()
                .map(p -> p.getQuantity() * p.getQuantity())
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal.ZERO, (a, b) -> b.add(a));
    }

    public void add(Position position) {
        positionList.add(position);
    }
}
