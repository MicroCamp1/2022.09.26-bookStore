package pl.comarch.camp.micro.book.store.model;


import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Accessors(fluent = true)
@Builder
//@Log4j2
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;

    /*public void cos() {
        log.info("kjasgdkjhfas");
    }*/
}
