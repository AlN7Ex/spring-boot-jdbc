package ru.learnup.mysak.springbootlearnupjdbc.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookShopDAO {

    private static final String FIND_BY_ID = "select * from shop.book_table where id = :id ";
    private static final String SHOW_ALL = "select * from shop.book_table";
    private static final String SAVE = "" +
            "insert into shop.book_table (book_title, book_author, order_number) " +
            "values (:book_title, :book_author, :order_number)";

    private final NamedParameterJdbcTemplate template;

    public BookShopDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(Book book) {
        template.update(SAVE, new MapSqlParameterSource()
                .addValue("book_title", book.getTitle())
                .addValue("book_author", book.getAuthor())
                .addValue("order_number", book.getOrder())
        );
    }

    public Book findById(long id) {
        return template.query(
                FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                (rs, rn) ->
                        Book.builder()
                                .id(rs.getLong("id"))
                                .title(rs.getString("book_title"))
                                .author(rs.getString("book_author"))
                                .order(rs.getInt("order_number"))
                                .build()
        ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));
    }

    public List<Book> show() {
        return template.query(SHOW_ALL, (rs, rn) -> new Book(
                rs.getLong("id"),
                rs.getString("book_title"),
                rs.getString("book_author"),
                rs.getInt("order_number")
                )
        );
    }

}
