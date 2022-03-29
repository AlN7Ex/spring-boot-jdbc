package ru.learnup.mysak.springbootlearnupjdbc;

import org.springframework.stereotype.Component;
import ru.learnup.mysak.springbootlearnupjdbc.dao.Book;
import ru.learnup.mysak.springbootlearnupjdbc.dao.BookShopDAO;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookShop {

    BookShopDAO bookShopDAO;

    private final List<Book> bookList = new ArrayList<>();

    public BookShop(BookShopDAO bookShopDAO) {
        this.bookShopDAO = bookShopDAO;
    }

    public void addBook(Book book) {
        bookShopDAO.save(book);
    }

    public Book buyBook(long id) {

        return bookShopDAO.findById(id);
    }

    public List<Book> listBooks() {
        return bookShopDAO.show();
    }
}
