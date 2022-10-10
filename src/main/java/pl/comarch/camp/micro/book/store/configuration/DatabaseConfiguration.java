package pl.comarch.camp.micro.book.store.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.comarch.camp.micro.book.store.database.repositories.AuthorRepository;
import pl.comarch.camp.micro.book.store.database.repositories.BookRepository;
import pl.comarch.camp.micro.book.store.model.Author;
import pl.comarch.camp.micro.book.store.model.Book;

import javax.annotation.PostConstruct;

@EnableJpaRepositories("pl.comarch.camp.micro.book.store.database.repositories")
@Configuration
public class DatabaseConfiguration {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DatabaseConfiguration(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void init() {
        if (!bookRepository.existsById(1)) {
            Book book = bookRepository.save(Book.builder()
                    .title("JPA - Title")
                    .isbn("13-123-123")
                    .price(33.33)
                    .build());

            Author author = authorRepository.save(Author.builder()
                    .firstName("Krzysztof").lastName("Szymeczek")
                    .book(book).build());
        }
    }
}
