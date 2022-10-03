package pl.comarch.camp.micro.book.store.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String login;
    private String password;
}
