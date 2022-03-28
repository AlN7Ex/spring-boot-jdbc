package ru.learnup.mysak.springbootlearnupjdbc.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnup.mysak.springbootlearnupjdbc.Book;

import java.util.List;

@Repository
public class BookShopDAO {

    private static final String FIND_BY_ID = "select * from shop.book_table where id = :id ";
    private static final String SHOW_ALL = "select * from shop.book_table";
    private static final String SAVE = "" +
            "insert into shop.book_table (bookTitle, bookAuthor, orderNumber)" +
            " values (:bookTitle, :bookAuthor, :orderNumber)";

    private final NamedParameterJdbcTemplate template;

    public BookShopDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Book book) {
        template.update(SAVE, new MapSqlParameterSource()
                .addValue("bookTitle", book.getTitle())
                .addValue("bookAuthor", book.getAuthor())
                .addValue("orderNumber", book.getOrder())
        );
    }

    public Book findById(long id) {
        return template.query(
                FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                (rs, rn) ->
                        Book.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("bookTitle"))
                                .author(rs.getString("bookAuthor"))
                                .order(rs.getInt("orderNumber"))
                                .build()
        ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));
    }

    public List<Book> showBooks() {
        return template.queryForList(SHOW_ALL, new MapSqlParameterSource(), Book.class);
    }

}
