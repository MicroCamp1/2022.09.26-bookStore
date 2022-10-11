package pl.comarch.camp.micro.book.store.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.database.IUserDAO;
import pl.comarch.camp.micro.book.store.database.impl.BookDAOStub;
import pl.comarch.camp.micro.book.store.database.impl.UserDAOStub;

@Configuration
//@ComponentScan(basePackages = {
//        "pl.comarch.camp.micro.book.store.controllers",
//        "pl.comarch.camp.micro.book.store.services",
//        "pl.comarch.camp.micro.book.store.session"
//})
public class TestConfiguration {
    /*@Bean
    public IUserDAO userDAO() {
        return new UserDAOStub();
    }

    @Bean
    public IBookDAO bookDAO() {
        return new BookDAOStub();
    }*/

    /*@Bean
    public IUserDAO userDAO() {
        return Mockito.mock(IUserDAO.class);
    }

    @Bean
    public IBookDAO bookDAO() {
        return Mockito.mock(IBookDAO.class);
    }*/
}
