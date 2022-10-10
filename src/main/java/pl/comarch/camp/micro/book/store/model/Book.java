package pl.comarch.camp.micro.book.store.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@Accessors(fluent = true)
@Builder
//@Log4j2
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @NotEmpty
    private String title;

    @OneToMany(mappedBy =  "book")
    @JsonIgnoreProperties(value = {"book"}, allowSetters = true)
    private Set<Author> authors;

    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String isbn;

    @NotNull
    private double price;

    /*public void cos() {
        log.info("kjasgdkjhfas");
    }*/
}
