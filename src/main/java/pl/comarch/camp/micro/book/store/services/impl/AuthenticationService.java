package pl.comarch.camp.micro.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.User;
import pl.comarch.camp.micro.book.store.services.IAuthenticationService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.userDAO.getUserByLogin(login);
        if(user.isPresent() &&
                user.get().getPassword()
                        .equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(user.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.getBasket().clear();
        this.sessionObject.setUser(null);
    }
}
