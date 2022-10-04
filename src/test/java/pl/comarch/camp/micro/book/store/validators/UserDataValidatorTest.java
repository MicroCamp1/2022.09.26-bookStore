package pl.comarch.camp.micro.book.store.validators;

import org.junit.Test;
import pl.comarch.camp.micro.book.store.exceptions.ValidationException;

public class UserDataValidatorTest {

    @Test
    public void validateCorrectLoginTest() {
        String login = "admin";

        UserDataValidator.validateLogin(login);
    }

    @Test(expected = ValidationException.class)
    public void validateIncorrectLoginTest() {
        String login = "asd";

        UserDataValidator.validateLogin(login);
    }

    @Test
    public void validateCorrectPasswordTest() {
        String password = "password";

        UserDataValidator.validatePassword(password);
    }

    @Test(expected = ValidationException.class)
    public void validateIncorrectPasswordTest() {
        String password = "pass";

        UserDataValidator.validatePassword(password);
    }
}
