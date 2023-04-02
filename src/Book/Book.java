package Book;

import Utils.Service;

import java.util.*;

public abstract class Book {
    private static int id = 0;

    private final int book_id;

    private String title;
    private int page_count;

    private Date release_date;

    private List<Integer> authors_ids;
    protected abstract void method();

    public Book(){
        this.book_id = id++;
        this.authors_ids = new ArrayList<>();
    }

    public Book(String title, int page_count, Date release_date, List<Integer> authors_ids) {
        this.book_id = id++;
        this.title = title;
        this.page_count = page_count;
        this.release_date = release_date;
        this.authors_ids = authors_ids;
    }

    public void read_book(Scanner scanner){
        System.out.print("Title: ");
        this.title = scanner.nextLine();
        this.page_count = Service.get_int(scanner, "Page count: ", "This is not a valid page count!");
        this.release_date = Service.get_date(scanner, "Release date: ", "This is not a valid date!");
        int authors_count = Service.get_int(scanner, "How many authors does the book have: ", "This is not a valid number of authors!");
        for(int i = 1; i <= authors_count; ++i)
            this.authors_ids.add(Service.get_int(scanner, "Author " + i + ": ", "This is not a valid author id!"));
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public List<Integer> getAuthors() {
        return authors_ids;
    }

    public void setAuthors(List<Integer> authors_ids) {
        this.authors_ids = authors_ids;
    }

    @Override
    public String toString() {
        return
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", page_count=" + page_count +
                ", release_date=" + release_date +
                ", authors=" + authors_ids;
    }
}
