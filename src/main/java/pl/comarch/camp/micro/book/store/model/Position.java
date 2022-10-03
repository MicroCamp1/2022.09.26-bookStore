package pl.comarch.camp.micro.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private Book book;
    private int quantity;

    public void incrementQuantity() {
        this.quantity++;
    }
}
