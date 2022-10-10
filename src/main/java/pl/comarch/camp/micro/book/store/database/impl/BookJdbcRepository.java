package pl.comarch.camp.micro.book.store.database.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.comarch.camp.micro.book.store.database.IBookDAO;
import pl.comarch.camp.micro.book.store.model.Book;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

public class BookJdbcRepository implements IBookDAO {

    public static final String SQL_BOOK_INSERT = "INSERT INTO BOOK (id, title, author, isbn, price) VALUES ( ?, ?, ?, ?, ? )";
    private final JdbcTemplate jdbcTemplate;

    public BookJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
    *     private int id;
        private String title;
        private String author;
        private String isbn;
        private double price;
    * */

/*    @PostConstruct
    public void init() {
        jdbcTemplate.execute("CREATE TABLE BOOK (id integer, title VARCHAR(255), " +
                "author VARCHAR(255), isbn VARCHAR(10), price NUMBER)");
        Book book = Book.builder().id(1).title("JDBC-Title")
                .author("Krzysztof Szymeczek")
                .isbn("13-123-123").price(49.99).build();

        jdbcTemplate.update(SQL_BOOK_INSERT,
                book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());

    }*/

    @Override
    public List<Book> getBookList() {
        return jdbcTemplate.query("SELECT * FROM BOOK",
                (rs, rowNum) -> new Book(rs.getInt("id"),
                rs.getString(2),
             null                        ,
                rs.getString(4),
                rs.getDouble(5))
        );
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return jdbcTemplate.query("SELECT * FROM BOOK where id = ?",
                rs -> {
                    if (!rs.next()) {
                        return Optional.empty();
                    }
                    return Optional.of(new Book(
                            rs.getInt("id"),
                            rs.getString(2),
                            null,
                            rs.getString(4),
                            rs.getDouble(5)
                    ));
                },

                id);
    }
}
