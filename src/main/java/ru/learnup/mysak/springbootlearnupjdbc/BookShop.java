package ru.learnup.mysak.springbootlearnupjdbc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookShop {
    private long id;
    private String book;
    private String author;
    private int order;
}
