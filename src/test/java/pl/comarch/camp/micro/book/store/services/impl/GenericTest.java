package pl.comarch.camp.micro.book.store.services.impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.comarch.camp.micro.book.store.configuration.TestConfiguration;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.database.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class GenericTest {
    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;
}
