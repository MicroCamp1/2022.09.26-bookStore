package pl.comarch.camp.micro.book.store.services;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void logout();
}
