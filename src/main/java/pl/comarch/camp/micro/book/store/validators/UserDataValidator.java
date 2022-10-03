package pl.comarch.camp.micro.book.store.validators;

import pl.comarch.camp.micro.book.store.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {
    public static void validateLogin(String login) {
        String regex = "^.{5,}$";
        if(!login.matches(regex)) {
            throw new ValidationException();
        }
    }

    public static void validatePassword(String password) {
        String regex = "^.{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(!matcher.matches()) {
            throw new ValidationException();
        }
    }
}
