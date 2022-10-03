package pl.comarch.camp.micro.book.store;

import pl.comarch.camp.micro.book.store.model.Book;

public class Main {
    public static void main(String[] args) {
        Book book = new Book();

        book.id(5).author("abc").title("jakis");
        System.out.println(book.author());

        Book book2 = Book.builder().id(5).author("jakis").build();
    }
}
