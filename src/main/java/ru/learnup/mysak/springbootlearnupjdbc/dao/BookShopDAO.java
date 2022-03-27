package ru.learnup.mysak.springbootlearnupjdbc.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.learnup.mysak.springbootlearnupjdbc.BookShop;

@Repository
public class BookShopDAO {

    private static final String FIND_BY_ID = "select * from learnup.BookShop where 'id' = :id";
    private static final String SAVE = "" +
            "INSERT INTO learnup.BookShop (bookName, author, orderNumber) " +
            "VALUES (:bookName, :author, :orderNumber)";

    private final NamedParameterJdbcTemplate template;

    public BookShopDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void save(BookShop bookShop) {
        template.update(SAVE, new MapSqlParameterSource()
                .addValue("bookName", bookShop.getBook())
                .addValue("author", bookShop.getAuthor())
                .addValue("orderNumber", bookShop.getOrder())
        );
    }

    public BookShop findById(long id) {
        return template.query(
                FIND_BY_ID,
                new MapSqlParameterSource("id", id),
                (rs, rn) ->
                        BookShop.builder()
                                .id(rs.getLong("id"))
                                .book(rs.getString("bookName"))
                                .author(rs.getString("author"))
                                .order(rs.getInt("orderNumber"))
                                .build()
        ).stream()
                .findAny()
                .orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found"));
    }
}
