package pl.comarch.camp.micro.book.store.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    private int id;
    private User user;
    private List<Position> positions = new ArrayList<>();
}
