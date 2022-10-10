package pl.comarch.camp.micro.book.store.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.comarch.camp.micro.book.store.model.Author;
import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
