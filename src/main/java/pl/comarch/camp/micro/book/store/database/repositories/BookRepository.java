package pl.comarch.camp.micro.book.store.database.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.comarch.camp.micro.book.store.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    Page<Book> findByIsbn(String isbn, Pageable pageable);
    boolean existsByIsbn(String isbn);

    @Query("SELECT distinct b FROM Book b join fetch b.authors a ")
    List<Book> findBooksAndAuthors();
}
