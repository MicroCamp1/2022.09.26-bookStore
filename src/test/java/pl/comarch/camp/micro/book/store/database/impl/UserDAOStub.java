package pl.comarch.camp.micro.book.store.database.impl;

import org.apache.commons.codec.digest.DigestUtils;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.User;

import java.util.Optional;

public class UserDAOStub implements IUserDAO {
    @Override
    public Optional<User> getUserByLogin(String login) {
        if(login.equals("admin")) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword(DigestUtils.md5Hex("admin"));

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void persistUser(User user) {

    }
}
