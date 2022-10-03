package pl.comarch.camp.micro.book.store.database;

import pl.comarch.camp.micro.book.store.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void persistUser(User user);
}
