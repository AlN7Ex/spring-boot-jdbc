package ru.learnup.mysak.springbootlearnupjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup.mysak.springbootlearnupjdbc.dao.BookShopDAO;

@SpringBootApplication
public class SpringBootLearnupJdbcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootLearnupJdbcApplication.class, args);

        BookShopDAO bookShopDao = context.getBean(BookShopDAO.class);

        bookShopDao.findById(1);
    }

}
