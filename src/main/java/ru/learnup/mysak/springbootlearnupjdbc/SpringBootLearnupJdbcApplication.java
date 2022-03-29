package ru.learnup.mysak.springbootlearnupjdbc;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.mysak.springbootlearnupjdbc.dao.Book;
import org.slf4j.Logger;

import java.util.List;

@SpringBootApplication
public class SpringBootLearnupJdbcApplication {
    private static final Logger log = LoggerFactory.getLogger(Book.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLearnupJdbcApplication.class, args);

        BookShop bookShop = context.getBean(BookShop.class);

        // Get list of books in DataBase
        List<Book> books = bookShop.listBooks();
        log.info("List of books in book shop:");
        for (Book book : books) {
            log.info("{}", book);
        }

        // Choose and buy book by id
        Book book = bookShop.buyBook(1);
        log.info("You bought book: {}", book.getTitle());


//        bookShop.addBook(Book.builder()
//                .title("Java")
//                .author("Robert Loffore")
//                .order(3)
//                .build());


    }
}
