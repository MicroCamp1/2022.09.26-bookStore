package pl.comarch.camp.micro.book.store.services;

public interface IBasketService {
    void addBookToBasket(int id);
    double calculateBasketSum();
}
