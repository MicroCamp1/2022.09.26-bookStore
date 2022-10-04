package pl.comarch.camp.micro.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.comarch.camp.micro.book.store.configuration.TestConfiguration;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.model.Position;
import pl.comarch.camp.micro.book.store.model.User;
import pl.comarch.camp.micro.book.store.services.IAuthenticationService;
import pl.comarch.camp.micro.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class AuthenticationServiceTest extends GenericTest {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @Before
    public void configureMocks() {
        Mockito.when(this.userDAO.getUserByLogin("admin"))
                .thenReturn(
                        Optional.of(
                                new User(
                                        "admin",
                                        DigestUtils.md5Hex("admin")
                                )));

        Mockito.when(this.userDAO.getUserByLogin("zlylogin"))
                .thenReturn(Optional.empty());
    }

    @Test
    public void correctAuthenticationTest() {
        String login = "admin";
        String password = "admin";
        this.sessionObject.setUser(null);

        this.authenticationService.authenticate(login, password);

        Assert.assertNotNull(this.sessionObject.getUser());
        Assert.assertEquals(login, this.sessionObject.getUser().getLogin());
    }

    @Test
    public void incorrectLoginAuthenticationTest() {
        String login = "zlylogin";
        String password = "asdhkgfkajhsdg";
        this.sessionObject.setUser(null);

        this.authenticationService.authenticate(login, password);

        Assert.assertNull(this.sessionObject.getUser());
    }

    @Test
    public void incorrectPasswordAuthenticationTest() {
        String login = "admin";
        String password = "admin123";
        this.sessionObject.setUser(null);

        this.authenticationService.authenticate(login, password);

        Assert.assertNull(this.sessionObject.getUser());
    }

    @Test
    public void logoutTest() {
        this.sessionObject.setUser(new User("asdfas", "dfghfdf"));
        this.sessionObject.getBasket().add(new Position());
        int expectedBasketSize = 0;

        this.authenticationService.logout();

        Assert.assertNull(this.sessionObject.getUser());
        Assert.assertEquals(expectedBasketSize,
                this.sessionObject.getBasket().size());
    }
}
