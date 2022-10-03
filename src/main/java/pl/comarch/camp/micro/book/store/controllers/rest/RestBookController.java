package pl.comarch.camp.micro.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.dto.BooksResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class RestBookController {

    @Autowired
    IBookDAO bookDAO;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        Optional<Book> bookBox = this.bookDAO.getBookById(id);
        if(bookBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BooksResponse getAllBooks() {
        BooksResponse response = new BooksResponse();
        response.setBooks(this.bookDAO.getBookList());
        return response;
    }
}
