package Library;

import Utils.Service;

import java.util.*;

public class Borrow {

    private static int id;

    private  int borrow_id;

    private int reader_id;

    private  int librarian_id;

    private int book_id;

    private int library_id;

    public int getLibrary_id() {
        return library_id;
    }

    public void setLibrary_id(int library_id) {
        this.library_id = library_id;
    }

    private Date borrow_date;

    public Borrow(){
        this.borrow_id = ++id;
    }
    public Borrow(int reader_id, int librarian_id, int book_id, Date borrow_date) {
        this.borrow_id = ++id;
        this.reader_id = reader_id;
        this.librarian_id = librarian_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
    }

    public void read_borrow(Scanner scanner){
        this.reader_id = Service.get_int(scanner, "Reader's id: ", "This is not a valid id!");
        this.librarian_id = Service.get_int(scanner, "Librarian's id: ", "This is not a valid id!");
        this.book_id = Service.get_int(scanner, "Book's id: ", "This is not a valid id!");
        this.borrow_date = Service.get_date(scanner, "Borrow date: ", "This is not a valid date!");
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public int getReader_id() {
        return reader_id;
    }

    public int getLibrarian_id() {
        return librarian_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrow_id=" + borrow_id +
                ", reader_id=" + reader_id +
                ", librarian_id=" + librarian_id +
                ", book_id=" + book_id +
                ", borrow_date=" + borrow_date +
                '}';
    }
}
