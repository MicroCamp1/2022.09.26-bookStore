package pl.comarch.camp.micro.book.store.services.impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.comarch.camp.micro.book.store.App;
import pl.comarch.camp.micro.book.store.configuration.DatabaseConfiguration;
import pl.comarch.camp.micro.book.store.configuration.TestConfiguration;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles(value = {"test"})
@ContextConfiguration(classes = {TestConfiguration.class, App.class})
public abstract class GenericTest {
    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    BookRepository bookRepository;
}
