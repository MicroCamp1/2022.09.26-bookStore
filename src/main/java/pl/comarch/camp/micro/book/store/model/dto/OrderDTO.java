package pl.comarch.camp.micro.book.store.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {
    private int id;
    private String user;
    private List<String> positions = new ArrayList<>();
}
