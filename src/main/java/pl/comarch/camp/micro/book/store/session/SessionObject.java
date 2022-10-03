package pl.comarch.camp.micro.book.store.session;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.model.User;

import java.util.HashSet;
import java.util.Set;

@Component
@SessionScope
@Getter
@Setter
public class SessionObject {
    private User user = null;
    Set<Position> basket = new HashSet<>();

    public boolean isLogged() {
        return this.user != null;
    }
}
