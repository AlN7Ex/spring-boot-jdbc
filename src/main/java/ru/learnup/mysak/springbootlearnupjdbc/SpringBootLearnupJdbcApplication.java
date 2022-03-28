package ru.learnup.mysak.springbootlearnupjdbc;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.mysak.springbootlearnupjdbc.dao.BookShopDAO;
import org.slf4j.Logger;

import java.util.List;

@SpringBootApplication
public class SpringBootLearnupJdbcApplication {
    private static final Logger log = LoggerFactory.getLogger(Book.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLearnupJdbcApplication.class, args);

//        BookShopDAO bookShopDao = context.getBean(BookShopDAO.class);

//        Book book = bookShopDao.findById(1);

//        log.info("Book: {}", book);

        BookShop bookShop = context.getBean(BookShop.class);

        Book book1 = bookShop.buyBook(1);
        List<Book> listBook = bookShop.showBooks();
        //bookShop.addBook(new Book(2, "Stephen Prata", "C++", 2));
//        Book book2 = bookShop.buyBook("Harry Potter");
        //Book book2 = bookShop.buyBook(2);
        log.info("Book 1: {}", book1);
        log.info("Book list: {}", listBook);

//        log.info("Book 2: {}", book2);
    }

}
