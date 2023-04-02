package Book;

import java.util.*;

public class Book {
    private static int id = 0;

    private final int book_id;

    private String title;
    private int page_count;

    private Date release_date;

    public Book(){
        this.book_id = id++;
    }
}
