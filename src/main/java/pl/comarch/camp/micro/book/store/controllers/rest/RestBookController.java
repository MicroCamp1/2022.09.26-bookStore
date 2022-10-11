package pl.comarch.camp.micro.book.store.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;
import pl.comarch.camp.micro.book.store.model.Book;
import pl.comarch.camp.micro.book.store.model.Book_;
import pl.comarch.camp.micro.book.store.model.dto.BooksResponse;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class RestBookController {

    private final BookRepository bookRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private static Specification<Book> createSpecificationIsbn(String isbn) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Book_.isbn), isbn);
    }

    private static Specification<Book> createPremiumPrice() {
        return (Specification<Book>) (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(Book_.price),
                        Double.valueOf(25));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
        Optional<Book> bookBox = this.bookRepository.findById(id);
        if (bookBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "")
    public BooksResponse getAllBooks(@RequestParam(value = "isbn", required = false) String isbn, Pageable pageable) {
        BooksResponse response = new BooksResponse();
        Page<Book> criteria = this.bookRepository.findAll(
                createSpecificationIsbn(isbn)
                        .and(createPremiumPrice().or(
                                (root, query, criteriaBuilder) ->
                                        criteriaBuilder.equal(root.get(Book_.TITLE), "Criteria"))

                        ), pageable);
        response.setBooks(criteria.getContent());
        return response;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new RuntimeException();
        }
        Book savedBook = bookRepository.save(book);
        applicationEventPublisher.publishEvent(new BookCreatedEvent(this, savedBook));
        return ResponseEntity.ok(savedBook);
    }
}
