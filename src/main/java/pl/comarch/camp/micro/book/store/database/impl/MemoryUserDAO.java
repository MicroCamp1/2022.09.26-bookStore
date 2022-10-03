package pl.comarch.camp.micro.book.store.database.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryUserDAO implements IUserDAO {
    private final List<User> users = new ArrayList<>();

    public MemoryUserDAO() {
        this.users.add(new User("admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User("mateusz", DigestUtils.md5Hex("mateusz")));

    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
