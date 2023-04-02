package Book;

import java.util.Comparator;

public class BookPopularityComparator implements Comparator<Book> {

    public BookPopularityComparator(){}
    public int compare(Book b1, Book b2){
        return b2.getPopularity() - b1.getPopularity();
    }
}
